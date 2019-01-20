package minhaturma.ufrpe.br.minhaturma.network.services;


import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import minhaturma.ufrpe.br.minhaturma.lectures.Lecture;
import minhaturma.ufrpe.br.minhaturma.network.api.LectureApi;

/**
 * Created by tuliodesouza
 */
public class LectureService extends BaseService {

    LectureApi mLectureApi;

    public LectureService(){
        mLectureApi = mRetrofit.create(LectureApi.class);
    }

    public void list(Observer<List<Lecture>> subscriber){
        mLectureApi.list().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
