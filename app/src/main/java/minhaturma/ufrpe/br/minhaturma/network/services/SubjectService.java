package minhaturma.ufrpe.br.minhaturma.network.services;


import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import minhaturma.ufrpe.br.minhaturma.confidence.Confidence;
import minhaturma.ufrpe.br.minhaturma.network.api.ConfidenceApi;
import minhaturma.ufrpe.br.minhaturma.network.api.SubjectApi;
import minhaturma.ufrpe.br.minhaturma.subjects.Subject;

/**
 * Created by tuliodesouza
 */
public class SubjectService extends BaseService {

    SubjectApi mSubjectApi;

    public SubjectService(){
        mSubjectApi = mRetrofit.create(SubjectApi.class);
    }

    public void list(Observer<List<Subject>> subscriber){
        mSubjectApi.list().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
