package minhaturma.ufrpe.br.minhaturma.network.services;


import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import minhaturma.ufrpe.br.minhaturma.confidence.Confidence;
import minhaturma.ufrpe.br.minhaturma.lectures.Lecture;
import minhaturma.ufrpe.br.minhaturma.network.api.ConfidenceApi;
import minhaturma.ufrpe.br.minhaturma.network.api.LectureApi;

/**
 * Created by tuliodesouza
 */
public class ConfidenceService extends BaseService {

    ConfidenceApi mConfidenceApi;

    public ConfidenceService(){
        mConfidenceApi = mRetrofit.create(ConfidenceApi.class);
    }

    public void list(Observer<List<Confidence>> subscriber){
        mConfidenceApi.list().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void add(Confidence confidence, Observer<Confidence> subscriber){
        mConfidenceApi.add(confidence).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
