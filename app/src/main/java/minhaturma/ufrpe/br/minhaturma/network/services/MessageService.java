package minhaturma.ufrpe.br.minhaturma.network.services;


import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import minhaturma.ufrpe.br.minhaturma.messages.Message;
import minhaturma.ufrpe.br.minhaturma.network.api.MessageApi;

/**
 * Created by tuliodesouza
 */
public class MessageService extends BaseService {

    MessageApi mAnswerApi;

    public MessageService(){
        mAnswerApi = mRetrofit.create(MessageApi.class);
    }

    public void list(Observer<List<Message>> subscriber){
        mAnswerApi.list().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
