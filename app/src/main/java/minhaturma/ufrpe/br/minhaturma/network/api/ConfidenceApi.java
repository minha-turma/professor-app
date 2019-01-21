package minhaturma.ufrpe.br.minhaturma.network.api;

import java.util.List;

import io.reactivex.Observable;
import minhaturma.ufrpe.br.minhaturma.confidence.Confidence;
import minhaturma.ufrpe.br.minhaturma.lectures.Lecture;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by tuliodesouza
 */
public interface ConfidenceApi {

    @GET("/api/confidence")
    Observable<List<Confidence>> list();

    @POST("/api/confidence")
    Observable<Confidence> add(@Body Confidence confidence);

}
