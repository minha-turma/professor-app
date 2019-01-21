package minhaturma.ufrpe.br.minhaturma.students;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import minhaturma.ufrpe.br.minhaturma.R;
import minhaturma.ufrpe.br.minhaturma.MainActivity;
import minhaturma.ufrpe.br.minhaturma.auth.AuthService;
import minhaturma.ufrpe.br.minhaturma.commons.EntityObserver;
import minhaturma.ufrpe.br.minhaturma.network.services.StudentService;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.username)
    EditText mUsernameView;

    @BindView(R.id.password)
    EditText mPasswordView;

    StudentService mStudentService;
    AuthService mAuthService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        mStudentService = new StudentService();
        mAuthService = AuthService.getInstance();

        if (mAuthService.isAuthenticated()) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    @OnClick(R.id.button_login)
    public void onLogin() {

        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();

        mStudentService.login(new Student(username, password), new EntityObserver<Student>() {
            @Override
            public void onNext(Student value) {
                mAuthService.saveAccessToken(value.getAccess_token());
                mStudentService.me(new EntityObserver<Student>() {
                    @Override
                    public void onNext(Student value) {
                        mAuthService.saveLoggedUserId(value.getId());
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }
                });
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(getApplicationContext(), "Login ou Senha incorreto", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
