package minhaturma.ufrpe.br.minhaturma.network.requests;


import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import minhaturma.ufrpe.br.minhaturma.auth.AuthService;
import minhaturma.ufrpe.br.minhaturma.messages.Message;
import minhaturma.ufrpe.br.minhaturma.network.api.MessageApi;
import minhaturma.ufrpe.br.minhaturma.network.api.StudentApi;
import minhaturma.ufrpe.br.minhaturma.students.Student;

/**
 * Created by tuliodesouza
 */
public class StudentService extends BaseService {

    StudentApi mStudentApi;
    AuthService mAuthService;

    public StudentService(){
        mStudentApi = mRetrofit.create(StudentApi.class);
        mAuthService = AuthService.getInstance();
    }

    public void login(Student student, Observer<Student> subscriber){
        mStudentApi.login(student).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void me(Observer<Student> subscriber){
        mStudentApi.me().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void update(Student student, Observer<Student> subscriber){
        mStudentApi.update(student.getId(), student).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
