package minhaturma.ufrpe.br.minhaturma.confidence;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import minhaturma.ufrpe.br.minhaturma.R;
import minhaturma.ufrpe.br.minhaturma.auth.AuthService;
import minhaturma.ufrpe.br.minhaturma.commons.EntityObserver;
import minhaturma.ufrpe.br.minhaturma.commons.MTFragment;
import minhaturma.ufrpe.br.minhaturma.messages.Message;
import minhaturma.ufrpe.br.minhaturma.network.services.ConfidenceService;
import minhaturma.ufrpe.br.minhaturma.network.services.MessageService;
import minhaturma.ufrpe.br.minhaturma.students.Student;
import minhaturma.ufrpe.br.minhaturma.subjects.Subject;

public class ConfidencesFragment extends Fragment implements MTFragment, DialogInterface.OnDismissListener {

    @BindView(R.id.confidences)
    RecyclerView mConfidencesView;
    @BindView(R.id.empty_confidence_messages)
    TextView mEmptyMessagesView;

    static ConfidencesFragment instance;
    public static String TAG = "NewsFragment";

    ConfidenceService mConfidenceService;

    public static ConfidencesFragment getInstance() {
        if (instance == null) {
            instance = new ConfidencesFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_confidences, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        refreshList();
    }

    @Override
    public void onRefresh() {
        refreshList();
    }

    @OnClick(R.id.add_confidence)
    public void addConfidence() {

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        AddConfidenceDialog newFragment = AddConfidenceDialog.newInstance();
        newFragment.show(ft, "dialog");
        newFragment.setOnDismissListener(this);

    }

    @Override
    public String getTitle() {
        return "Auto Confiança";
    }



    private void refreshList() {
        mConfidenceService = new ConfidenceService();
        mConfidenceService.list(new EntityObserver<List<Confidence>>() {
            @Override
            public void onNext(List<Confidence> value) {
                ConfidencesAdapter adapter = new ConfidencesAdapter(getContext(), value);

                mConfidencesView.setLayoutManager(new LinearLayoutManager(getContext()));
                mConfidencesView.setAdapter(adapter);

                mEmptyMessagesView.setVisibility(value.isEmpty() ? View.VISIBLE : View.GONE);

            }
        });
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
        refreshList();
    }
}
