package minhaturma.ufrpe.br.minhaturma.network.api;

import io.reactivex.Observable;
import minhaturma.ufrpe.br.minhaturma.students.Student;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by tuliodesouza
 */
public interface StudentApi {

    @POST("/api/login")
    Observable<Student> login(@Body Student student);

    @POST("/api/user/me")
    Observable<Student> me();

    @PUT("/api/user/{id}")
    Observable<Student> update(@Path("id") int id, @Body Student student);

}
