package minhaturma.ufrpe.br.minhaturma.network.api;

import java.util.List;

import io.reactivex.Observable;
import minhaturma.ufrpe.br.minhaturma.quizzes.Quiz;
import retrofit2.http.GET;

/**
 * Created by tuliodesouza
 */
public interface QuizApi {

    @GET("/api/quiz")
    Observable<List<Quiz>> list();

}
