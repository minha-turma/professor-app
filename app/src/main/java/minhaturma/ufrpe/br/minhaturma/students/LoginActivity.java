package minhaturma.ufrpe.br.minhaturma.students;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import minhaturma.ufrpe.br.minhaturma.R;
import minhaturma.ufrpe.br.minhaturma.MainActivity;
import minhaturma.ufrpe.br.minhaturma.commons.EntityObserver;
import minhaturma.ufrpe.br.minhaturma.network.requests.StudentService;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.username)
    EditText mUsernameView;

    @BindView(R.id.password)
    EditText mPasswordView;

    StudentService mStudentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        mStudentService = new StudentService();
    }


    @OnClick(R.id.button_login)
    public void onLogin() {

        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();

        mStudentService.login(new Student(username, password), new EntityObserver<Student>() {
            @Override
            public void onNext(Student value) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(getApplicationContext(), "Login ou Senha incorreto", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
