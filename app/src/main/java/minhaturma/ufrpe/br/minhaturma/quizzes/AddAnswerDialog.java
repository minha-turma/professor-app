package minhaturma.ufrpe.br.minhaturma.quizzes;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import minhaturma.ufrpe.br.minhaturma.R;
import minhaturma.ufrpe.br.minhaturma.auth.AuthService;
import minhaturma.ufrpe.br.minhaturma.commons.EntityObserver;
import minhaturma.ufrpe.br.minhaturma.confidence.Confidence;
import minhaturma.ufrpe.br.minhaturma.network.services.AnswerService;
import minhaturma.ufrpe.br.minhaturma.network.services.ConfidenceService;
import minhaturma.ufrpe.br.minhaturma.network.services.QuizService;
import minhaturma.ufrpe.br.minhaturma.network.services.SubjectService;
import minhaturma.ufrpe.br.minhaturma.students.Student;
import minhaturma.ufrpe.br.minhaturma.subjects.Subject;

public class AddAnswerDialog extends DialogFragment {

    DialogInterface.OnDismissListener mListener;

    @BindView(R.id.statement)
    TextView mStatementView;

    @BindView(R.id.a_alternative)
    TextView mAlternativeA;
    @BindView(R.id.b_alternative)
    TextView mAlternativeB;
    @BindView(R.id.c_alternative)
    TextView mAlternativeC;
    @BindView(R.id.d_alternative)
    TextView mAlternativeD;
    @BindView(R.id.e_alternative)
    TextView mAlternativeE;

    @BindView(R.id.quiz_answer)
    RadioGroup quizAnswer;

    AnswerService mAnswerService;

    Quiz quiz;

    static AddAnswerDialog newInstance(Quiz quiz) {
        AddAnswerDialog f = new AddAnswerDialog();
        f.setQuiz(quiz);
        return f;
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener listener) {
        mListener = listener;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_answer_dialog, null);
        ButterKnife.bind(this, v);

        mStatementView.setText(quiz.getStatement());

        mAlternativeA.setText("A: " + quiz.getAlternatives().get(0));
        mAlternativeB.setText("B: " + quiz.getAlternatives().get(1));
        mAlternativeC.setText("C: " + quiz.getAlternatives().get(2));
        mAlternativeD.setText("D: " + quiz.getAlternatives().get(3));
        mAlternativeE.setText("E: " + quiz.getAlternatives().get(4));

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        int width = getResources().getDimensionPixelSize(R.dimen.answer_dialog_width);
        int height = getResources().getDimensionPixelSize(R.dimen.answer_dialog_height);
        getDialog().getWindow().setLayout(width, height);
    }


    @OnClick(R.id.answer_button)
    public void answer() {
        Student me = new Student(AuthService.getInstance().getLoggedUserId());
        int answerViewId = quizAnswer.getCheckedRadioButtonId();
        String answerText = ((RadioButton)getView().findViewById(answerViewId)).getText().toString();

        Answer answer = new Answer(quiz, me, answerText);
        mAnswerService.add(answer, new EntityObserver<Answer>() {
            @Override
            public void onNext(Answer value) {
                Toast.makeText(getContext(), "Auto confiança adicionada", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAnswerService = new AnswerService();
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mListener != null) {
            mListener.onDismiss(dialog);
        }
    }
}
