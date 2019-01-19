package minhaturma.ufrpe.br.minhaturma.network.api;


import java.util.List;

import io.reactivex.Observable;
import minhaturma.ufrpe.br.minhaturma.messages.Message;
import retrofit2.http.GET;

/**
 * Created by tuliodesouza
 */
public interface MessageApi {

    @GET("/message/")
    Observable<List<Message>> list();

}
