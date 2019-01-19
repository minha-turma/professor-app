package minhaturma.ufrpe.br.minhaturma.network.api;

import io.reactivex.Observable;
import minhaturma.ufrpe.br.minhaturma.students.Student;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by tuliodesouza
 */
public interface StudentApi {

    @POST("/api/login")
    Observable<Student> login(@Body Student student);

}
