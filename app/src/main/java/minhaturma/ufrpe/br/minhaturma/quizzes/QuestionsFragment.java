package minhaturma.ufrpe.br.minhaturma.quizzes;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import minhaturma.ufrpe.br.minhaturma.R;
import minhaturma.ufrpe.br.minhaturma.commons.EntityObserver;
import minhaturma.ufrpe.br.minhaturma.commons.MTFragment;
import minhaturma.ufrpe.br.minhaturma.messages.Message;
import minhaturma.ufrpe.br.minhaturma.messages.MessagesAdapter;
import minhaturma.ufrpe.br.minhaturma.network.requests.MessageService;
import minhaturma.ufrpe.br.minhaturma.network.requests.QuizService;
import minhaturma.ufrpe.br.minhaturma.news.ClickRecyclerView_Interface;
import minhaturma.ufrpe.br.minhaturma.news.News;
import minhaturma.ufrpe.br.minhaturma.news.NewsAdapter;
import minhaturma.ufrpe.br.minhaturma.students.Student;

public class QuizFragment extends Fragment implements MTFragment {

    static QuizFragment instance;

    QuizService mQuizService;

    @BindView(R.id.quiz_recyclerview)
    RecyclerView mQuizesView;

    public static QuizFragment getInstance() {
        if (instance == null) {
            instance = new QuizFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        super.onViewCreated(view, savedInstanceState);

        refreshList();
    }

    @Override
    public void onRefresh() {
        refreshList();
    }

    @Override
    public String getTitle() {
        return "Quiz";
    }

    private void refreshList() {
        mQuizService = new QuizService();
        mQuizService.listQuestions(new EntityObserver<List<Question>>() {
            @Override
            public void onNext(List<Question> value) {
                QuizAdapter adapter = new QuizAdapter(getContext(), value);

                mQuizesView.setLayoutManager(new LinearLayoutManager(getContext()));
                mQuizesView.setAdapter(adapter);

            }
        });
//        mQuizService.list(new EntityObserver<List<Quiz>>() {
//            @Override
//            public void onNext(List<Quiz> value) {
//                QuizAdapter adapter = new QuizAdapter(value, getContext());
//
//                mQuizesView.setLayoutManager(new LinearLayoutManager(getContext()));
//                mQuizesView.setAdapter(adapter);
//
//            }
//        });
    }
}
