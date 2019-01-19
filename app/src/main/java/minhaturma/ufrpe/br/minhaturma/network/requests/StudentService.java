package minhaturma.ufrpe.br.minhaturma.network.requests;


import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import minhaturma.ufrpe.br.minhaturma.messages.Message;
import minhaturma.ufrpe.br.minhaturma.network.api.MessageApi;
import minhaturma.ufrpe.br.minhaturma.network.api.StudentApi;
import minhaturma.ufrpe.br.minhaturma.students.Student;

/**
 * Created by tuliodesouza
 */
public class StudentService extends BaseService {

    StudentApi mStudentApi;

    public StudentService(){
        mStudentApi = mRetrofit.create(StudentApi.class);
    }

    public void login(Student student, Observer<Student> subscriber){
        mStudentApi.login(student).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
