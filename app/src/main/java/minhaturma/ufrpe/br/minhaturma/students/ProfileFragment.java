package minhaturma.ufrpe.br.minhaturma.students;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import minhaturma.ufrpe.br.minhaturma.R;
import minhaturma.ufrpe.br.minhaturma.commons.EntityObserver;
import minhaturma.ufrpe.br.minhaturma.commons.MTFragment;
import minhaturma.ufrpe.br.minhaturma.network.services.StudentService;

public class ProfileFragment extends Fragment implements MTFragment {

    @BindView(R.id.profile_container)
    View mProfileContainer;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.username)
    TextView mUsername;

    @BindView(R.id.happy)
    ImageView mHappy;
    @BindView(R.id.confused)
    ImageView mConfused;
    @BindView(R.id.mad)
    ImageView mMad;
    @BindView(R.id.unhappy)
    ImageView mUnhappy;

    Student mStudent;

    static ProfileFragment instance;
    public static String TAG = "ProfileFragment";

    StudentService mStudentService;

    public static ProfileFragment getInstance() {
        if (instance == null) {
            instance = new ProfileFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        retrieve();
    }

    @Override
    public void onRefresh() {
        retrieve();
    }

    @Override
    public String getTitle() {
        return "Perfil";
    }

    private void retrieve() {
        mStudentService = new StudentService();
        mStudentService.me(new EntityObserver<Student>() {
            @Override
            public void onNext(Student value) {
                mProfileContainer.setVisibility(View.VISIBLE);

                mName.setText(value.getName());
                mUsername.setText(value.getUsername());

                mStudent = value;

                if (mStudent.getFeeling() != null) {
                    updateFeelingsView();
                }
            }
        });
    }

    @OnClick(R.id.happy)
    public void onHappy() {
        mStudent.setFeeling("Happy");
        resetBackgrounds();
        updateFeelingsView();
    }

    @OnClick(R.id.confused)
    public void onConfused() {
        mStudent.setFeeling("Confused");
        resetBackgrounds();
        updateFeelingsView();
    }

    @OnClick(R.id.mad)
    public void onMad() {
        mStudent.setFeeling("Mad");
        resetBackgrounds();
        updateFeelingsView();
    }

    @OnClick(R.id.unhappy)
    public void onUnhappy() {
        mStudent.setFeeling("Unhappy");
        resetBackgrounds();
        updateFeelingsView();
    }

    @OnClick(R.id.update_profile)
    public void updateProfile() {
        mStudentService.update(mStudent, new EntityObserver<Student>() {
            @Override
            public void onNext(Student value) {
                mStudent = value;
                Toast.makeText(getContext(), "Perfil atualizado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateFeelingsView() {
        resetBackgrounds();

        switch (mStudent.getFeeling()) {
            case "Happy":
                mHappy.setBackgroundResource(R.drawable.shape_underline);
                break;
            case "Confused":
                mConfused.setBackgroundResource(R.drawable.shape_underline);
                break;
            case "Mad":
                mMad.setBackgroundResource(R.drawable.shape_underline);
                break;
            case "Unhappy":
                mUnhappy.setBackgroundResource(R.drawable.shape_underline);
                break;
        }
    }

    private void resetBackgrounds() {
        mHappy.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
        mConfused.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
        mMad.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
        mUnhappy.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
    }
}
