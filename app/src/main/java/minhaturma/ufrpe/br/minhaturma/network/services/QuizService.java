package minhaturma.ufrpe.br.minhaturma.network.services;


import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import minhaturma.ufrpe.br.minhaturma.network.api.AnswerApi;
import minhaturma.ufrpe.br.minhaturma.network.api.QuizApi;
import minhaturma.ufrpe.br.minhaturma.quizzes.Answer;
import minhaturma.ufrpe.br.minhaturma.quizzes.Quiz;

/**
 * Created by tuliodesouza
 */
public class QuizService extends BaseService {

    QuizApi mQuizApi;

    public QuizService(){
        mQuizApi = mRetrofit.create(QuizApi.class);
    }

    public void list(Observer<List<Quiz>> subscriber){
        mQuizApi.list().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
