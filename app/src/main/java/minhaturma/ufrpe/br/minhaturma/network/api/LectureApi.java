package minhaturma.ufrpe.br.minhaturma.network.api;

import java.util.List;

import io.reactivex.Observable;
import minhaturma.ufrpe.br.minhaturma.lectures.Lecture;
import minhaturma.ufrpe.br.minhaturma.students.Student;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by tuliodesouza
 */
public interface LectureApi {

    @GET("/api/lecture")
    Observable<List<Lecture>> list();

}
