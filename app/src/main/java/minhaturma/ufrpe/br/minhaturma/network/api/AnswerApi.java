package minhaturma.ufrpe.br.minhaturma.network.api;

import java.util.List;

import io.reactivex.Observable;
import minhaturma.ufrpe.br.minhaturma.confidence.Confidence;
import minhaturma.ufrpe.br.minhaturma.quizzes.Answer;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by tuliodesouza
 */
public interface AnswerApi {

    @GET("/api/answer")
    Observable<List<Answer>> list();

    @POST("/api/answer")
    Observable<Answer> add(@Body Answer answer);

}
