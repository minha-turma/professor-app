package minhaturma.ufrpe.br.minhaturma.quizzes;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import minhaturma.ufrpe.br.minhaturma.R;

public class QuizFragment extends Fragment {

    static QuizFragment instance;

    public static QuizFragment getInstance() {
        if (instance == null) {
            instance = new QuizFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }

}
