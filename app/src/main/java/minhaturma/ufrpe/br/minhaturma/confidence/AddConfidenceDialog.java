package minhaturma.ufrpe.br.minhaturma.confidence;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import minhaturma.ufrpe.br.minhaturma.R;
import minhaturma.ufrpe.br.minhaturma.auth.AuthService;
import minhaturma.ufrpe.br.minhaturma.commons.EntityObserver;
import minhaturma.ufrpe.br.minhaturma.network.services.ConfidenceService;
import minhaturma.ufrpe.br.minhaturma.network.services.SubjectService;
import minhaturma.ufrpe.br.minhaturma.students.Student;
import minhaturma.ufrpe.br.minhaturma.subjects.Subject;

public class AddConfidenceDialog extends DialogFragment {

    DialogInterface.OnDismissListener mListener;

    @BindView(R.id.subject_spinner)
    Spinner mSubjectsDropdown;

    @BindView(R.id.smart)
    ImageView mSmart;
    @BindView(R.id.confused)
    ImageView mConfused;
    @BindView(R.id.unsecure)
    ImageView mUnsecure;

    SubjectService mSubjectService;
    ConfidenceService mConfidenceService;

    List<Subject> mSubjects;

    String status = "";

    static AddConfidenceDialog newInstance() {
        AddConfidenceDialog f = new AddConfidenceDialog();
        return f;
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener listener) {
        mListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_confidence_dialog, null);
        ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        int width = getResources().getDimensionPixelSize(R.dimen.dialog_width);
        int height = getResources().getDimensionPixelSize(R.dimen.dialog_height);
        getDialog().getWindow().setLayout(width, height);
    }

    @OnClick(R.id.smart)
    public void onSmart() {
        status = "Confident";
        resetBackgrounds();
        updateConfidenceView();
    }

    @OnClick(R.id.confused)
    public void onConfused() {
        status = "Confused";
        resetBackgrounds();
        updateConfidenceView();
    }

    @OnClick(R.id.unsecure)
    public void onUnsecure() {
        status = "Unsecure";
        resetBackgrounds();
        updateConfidenceView();
    }

    @OnClick(R.id.add_confidence)
    public void addConfidence() {
        Student me = new Student(AuthService.getInstance().getLoggedUserId());
        Subject subject = null;
        for (Subject s : mSubjects) {
            if (s.getName().equals(mSubjectsDropdown.getSelectedItem())) {
                subject = s;
            }
        }

        Confidence confidence = new Confidence(status, me, subject);
        mConfidenceService.add(confidence, new EntityObserver<Confidence>() {
            @Override
            public void onNext(Confidence value) {
                Toast.makeText(getContext(), "Auto confiança adicionada", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSubjectService = new SubjectService();
        mConfidenceService = new ConfidenceService();

        mSubjectService.list(new EntityObserver<List<Subject>>() {
            @Override
            public void onNext(List<Subject> value) {
                ArrayList<String> items = new ArrayList<>();

                for (Subject subject : value) {
                    items.add(subject.getName());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
                mSubjectsDropdown.setAdapter(adapter);
                mSubjects = value;
            }
        });
    }

    private void updateConfidenceView() {
        resetBackgrounds();

        switch (status) {
            case "Confident":
                mSmart.setBackgroundResource(R.drawable.shape_underline);
                break;
            case "Confused":
                mConfused.setBackgroundResource(R.drawable.shape_underline);
                break;
            case "Unsecure":
                mUnsecure.setBackgroundResource(R.drawable.shape_underline);
                break;
        }
    }

    private void resetBackgrounds() {
        mSmart.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
        mConfused.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
        mUnsecure.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mListener != null) {
            mListener.onDismiss(dialog);
        }
    }
}
