package minhaturma.ufrpe.br.minhaturma.quizzes;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import minhaturma.ufrpe.br.minhaturma.R;
import minhaturma.ufrpe.br.minhaturma.commons.EntityObserver;
import minhaturma.ufrpe.br.minhaturma.commons.MTFragment;
import minhaturma.ufrpe.br.minhaturma.network.services.QuizService;
import minhaturma.ufrpe.br.minhaturma.network.services.AnswerService;

public class QuizFragment extends Fragment implements MTFragment, QuizAdapter.OnQuizClickListener, DialogInterface.OnDismissListener {

    @BindView(R.id.open_quizzes)
    RecyclerView mOpenQuizzesView;

    @BindView(R.id.closed_quizzes)
    RecyclerView mAttemptedQuizzesView;

    @BindView(R.id.empty_attempted_quizzes)
    TextView mEmptyAttempts;

    @BindView(R.id.empty_open_quizzes)
    TextView mEmptyOpens;

    static QuizFragment instance;

    QuizService mQuizService;
    AnswerService mAnswerService;

    public static QuizFragment getInstance() {
        if (instance == null) {
            instance = new QuizFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quiza, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mOpenQuizzesView.setLayoutManager(layoutManager);

        final LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mOpenQuizzesView);
        mOpenQuizzesView.setOnFlingListener(snapHelper);

        mAttemptedQuizzesView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        retrieve();
    }

    private void retrieve() {

        mQuizService = new QuizService();
        mAnswerService = new AnswerService();

        mQuizService.list(new EntityObserver<List<Quiz>>() {
            @Override
            public void onNext(final List<Quiz> quizzes) {

                mAnswerService.list(new EntityObserver<List<Answer>>() {
                    @Override
                    public void onNext(List<Answer> answers) {

                        List<Quiz> openQuizs = new ArrayList<>();
                        List<Quiz> attemptedQuizes = new ArrayList<>();

                        for(Quiz quiz : quizzes) {
                            Log.d("AKE",quiz+"");
                            if (!hasAttempted(quiz, answers)) {
                                if(quiz.isOpen) openQuizs.add(quiz);
                            } else {
                                attemptedQuizes.add(quiz);
                            }
                        }

                        QuizAdapter adapter = new QuizAdapter(getContext(), openQuizs);
                        mOpenQuizzesView.setAdapter(adapter);
                        adapter.setOnQuizClickListener(QuizFragment.this);

                        QuizAdapter closedAdapter = new QuizAdapter(getContext(), attemptedQuizes, answers);
                        mAttemptedQuizzesView.setAdapter(closedAdapter);

                        mEmptyOpens.setVisibility(openQuizs.isEmpty() ? View.VISIBLE : View.GONE);
                        mEmptyAttempts.setVisibility(attemptedQuizes.isEmpty() ? View.VISIBLE : View.GONE);
                    }
                });
            }
        });

    }

    private boolean hasAttempted(Quiz lecture, List<Answer> answers) {
        for(Answer presence : answers) {
            if (presence.getQuiz().getId() == lecture.getId()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onRefresh() {
        retrieve();
    }

    @Override
    public String getTitle() {
        return "Quiz";
    }


    @Override
    public void onQuizClick(Quiz quiz) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        AddAnswerDialog newFragment = AddAnswerDialog.newInstance(quiz);
        newFragment.show(ft, "dialog");
        newFragment.setOnDismissListener(this);
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        retrieve();
    }
}
