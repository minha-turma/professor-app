package minhaturma.ufrpe.br.minhaturma.network.services;


import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import minhaturma.ufrpe.br.minhaturma.lectures.Lecture;
import minhaturma.ufrpe.br.minhaturma.network.api.LectureApi;
import minhaturma.ufrpe.br.minhaturma.network.api.PresenceApi;
import minhaturma.ufrpe.br.minhaturma.presences.Presence;

/**
 * Created by tuliodesouza
 */
public class PresenceService extends BaseService {

    PresenceApi mPresenceApi;

    public PresenceService(){
        mPresenceApi = mRetrofit.create(PresenceApi.class);
    }

    public void list(Observer<List<Presence>> subscriber){
        mPresenceApi.list().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void add(Presence presence, Observer<Presence> subscriber){
        mPresenceApi.add(presence).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
