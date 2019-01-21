package minhaturma.ufrpe.br.minhaturma.network.api;

import java.util.List;

import io.reactivex.Observable;
import minhaturma.ufrpe.br.minhaturma.lectures.Lecture;
import minhaturma.ufrpe.br.minhaturma.subjects.Subject;
import retrofit2.http.GET;

/**
 * Created by tuliodesouza
 */
public interface SubjectApi {

    @GET("/api/subject")
    Observable<List<Subject>> list();

}
