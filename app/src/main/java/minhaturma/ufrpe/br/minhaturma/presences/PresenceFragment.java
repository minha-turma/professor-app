package minhaturma.ufrpe.br.minhaturma.presences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import minhaturma.ufrpe.br.minhaturma.R;
import minhaturma.ufrpe.br.minhaturma.auth.AuthService;
import minhaturma.ufrpe.br.minhaturma.commons.EntityObserver;
import minhaturma.ufrpe.br.minhaturma.commons.MTFragment;
import minhaturma.ufrpe.br.minhaturma.lectures.Lecture;
import minhaturma.ufrpe.br.minhaturma.lectures.LecturesAdapter;
import minhaturma.ufrpe.br.minhaturma.network.services.LectureService;
import minhaturma.ufrpe.br.minhaturma.network.services.PresenceService;
import minhaturma.ufrpe.br.minhaturma.students.Student;

public class PresenceFragment extends Fragment implements MTFragment, LecturesAdapter.OnLectureClickListener {

    @BindView(R.id.open_lectures)
    RecyclerView mOpenLecturesView;

    @BindView(R.id.closed_lectures)
    RecyclerView mClosedLecturesView;

    @BindView(R.id.empty_attempted_lectures)
    TextView mEmptyAttempts;

    @BindView(R.id.empty_open_lectures)
    TextView mEmptyOpens;

    static PresenceFragment instance;

    LectureService mLectureService;
    PresenceService mPresenceService;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static PresenceFragment getInstance() {
        if (instance == null) {
            instance = new PresenceFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_presence, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mOpenLecturesView.setLayoutManager(layoutManager);

        final LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mOpenLecturesView);
        mOpenLecturesView.setOnFlingListener(snapHelper);

        mClosedLecturesView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        retrieve();
    }

    private void retrieve() {

        mLectureService = new LectureService();
        mPresenceService = new PresenceService();

        mLectureService.list(new EntityObserver<List<Lecture>>() {
            @Override
            public void onNext(final List<Lecture> lectures) {

                mPresenceService.list(new EntityObserver<List<Presence>>() {
                    @Override
                    public void onNext(List<Presence> presences) {

                        List<Lecture> openLectures = new ArrayList<>();
                        for(Lecture lecture : lectures) {
                            if (!hasAttempted(lecture, presences) && lecture.isOpen()) {
                                openLectures.add(lecture);
                            }
                        }

                        List<Lecture> attemptedLectures = new ArrayList<>();

                        for(Lecture lecture : lectures) {
                            if (hasAttempted(lecture, presences)) {
                                attemptedLectures.add(lecture);
                            }
                        }

                        LecturesAdapter adapter = new LecturesAdapter(getContext(), openLectures, true);
                        mOpenLecturesView.setAdapter(adapter);
                        adapter.setOnLectureClickListener(PresenceFragment.this);

                        LecturesAdapter closedAdapter = new LecturesAdapter(getContext(), attemptedLectures, false);
                        mClosedLecturesView.setAdapter(closedAdapter);

                        mEmptyOpens.setVisibility(openLectures.isEmpty() ? View.VISIBLE : View.GONE);
                        mEmptyAttempts.setVisibility(attemptedLectures.isEmpty() ? View.VISIBLE : View.GONE);
                    }
                });
            }
        });

    }

    private boolean hasAttempted(Lecture lecture, List<Presence> presences) {
        for(Presence presence : presences) {
            if (presence.getLecture().getId() == lecture.getId()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onRefresh() {
        retrieve();
    }

    @Override
    public String getTitle() {
        return "Chamadas";
    }

    @Override
    public void onLectureClick(Lecture lecture) {
        int userId = AuthService.getInstance().getLoggedUserId();
        lecture.setDate(null);

        Log.d("AKE",lecture+"");

        mPresenceService.add(new Presence(lecture, new Student(userId)), new EntityObserver<Presence>() {
            @Override
            public void onNext(Presence value) {
                retrieve();
            }
        });

        sharedPreferences = getActivity().getSharedPreferences("Topic", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("Topic", lecture.getTopic());
        editor.apply();

        sharedPreferences = getActivity().getSharedPreferences("Topic", Context.MODE_PRIVATE);
        String result = sharedPreferences.getString("Topic", "");
        Log.e("AKE","Result: "+result);


    }
}
