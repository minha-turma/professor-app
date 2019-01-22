package minhaturma.ufrpe.br.minhaturma.network.services;


import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import minhaturma.ufrpe.br.minhaturma.network.api.AnswerApi;
import minhaturma.ufrpe.br.minhaturma.network.api.PresenceApi;
import minhaturma.ufrpe.br.minhaturma.presences.Presence;
import minhaturma.ufrpe.br.minhaturma.quizzes.Answer;

/**
 * Created by tuliodesouza
 */
public class AnswerService extends BaseService {

    AnswerApi mAnswerApi;

    public AnswerService(){
        mAnswerApi = mRetrofit.create(AnswerApi.class);
    }

    public void list(Observer<List<Answer>> subscriber){
        mAnswerApi.list().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void add(Answer answer, Observer<Answer> subscriber){
        mAnswerApi.add(answer).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
