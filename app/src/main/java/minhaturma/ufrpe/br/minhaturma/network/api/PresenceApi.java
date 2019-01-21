package minhaturma.ufrpe.br.minhaturma.network.api;

import java.util.List;

import io.reactivex.Observable;
import minhaturma.ufrpe.br.minhaturma.lectures.Lecture;
import minhaturma.ufrpe.br.minhaturma.presences.Presence;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by tuliodesouza
 */
public interface PresenceApi {

    @GET("/api/presence")
    Observable<List<Presence>> list();

    @POST("/api/presence")
    Observable<Presence> add(@Body Presence presence);

}
