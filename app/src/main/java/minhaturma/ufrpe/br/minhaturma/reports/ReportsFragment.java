package minhaturma.ufrpe.br.minhaturma.reports;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import minhaturma.ufrpe.br.minhaturma.R;
import minhaturma.ufrpe.br.minhaturma.commons.MTFragment;
import minhaturma.ufrpe.br.minhaturma.news.ClickRecyclerView_Interface;
import minhaturma.ufrpe.br.minhaturma.presences.PresenceFragment;
import minhaturma.ufrpe.br.minhaturma.reports.items.ConfidenceReportFragment;
import minhaturma.ufrpe.br.minhaturma.reports.items.HumorReportFragment;
import minhaturma.ufrpe.br.minhaturma.reports.items.PresenceReportFragment;
import minhaturma.ufrpe.br.minhaturma.reports.items.QuizReportFragment;

public class ReportsFragment extends Fragment implements MTFragment, View.OnClickListener {

    public static final String TAG = "ReportsFragment";
    static ReportsFragment instance;

    @BindView(R.id.quiz)
    private Button btnQuiz;


    public static ReportsFragment getInstance() {
        if (instance == null) {
            instance = new ReportsFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.perfil_fragment, container, false);
        ButterKnife.bind(this, view);

        Button btnQuiz = view.findViewById(R.id.quiz);
        Button btnPresenca = view.findViewById(R.id.presenca);
        Button btnHumor = view.findViewById(R.id.humor);
        Button btnAutoconfianca = view.findViewById(R.id.autoconfianca);

        btnQuiz.setOnClickListener(this);
        btnPresenca.setOnClickListener(this);
        btnHumor.setOnClickListener(this);
        btnAutoconfianca.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public String getTitle() {
        return "Relatórios";
    }
/*
    private void proximaTela() {

        int id = 0;
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (id == R.id.Quiz) {
            ft.replace(R.id.fragment_container, QuizReportFragment.getInstance());
            getActivity().setTitle(QuizReportFragment.getInstance().getTitle());
        } else if (id == R.id.Presenca) {
            ft.replace(R.id.fragment_container, PresenceReportFragment.getInstance());
            getActivity().setTitle(PresenceReportFragment.getInstance().getTitle());
        } else if (id == R.id.Humor) {
            ft.replace(R.id.fragment_container, HumorReportFragment.getInstance());
            getActivity().setTitle(HumorReportFragment.getInstance().getTitle());
        } else if (id == R.id.Autoconfianca) {
            ft.replace(R.id.fragment_container, ConfidenceReportFragment.getInstance());
            getActivity().setTitle(ConfidenceReportFragment.getInstance().getTitle());
        }
    }*/

    @Override
    public void onClick(View view) {
        Fragment f;

        if (view.getId() == R.id.Quiz) {
            f = new QuizReportFragment();
            replaceFragment(f);
        } else if (view.getId() == R.id.Presenca) {
            f = new PresenceReportFragment();
            replaceFragment(f);
        } else if (view.getId() == R.id.Humor) {
            f = new HumorReportFragment();
            replaceFragment(f);
        } else if (view.getId() == R.id.Autoconfianca) {
            f = new ConfidenceReportFragment();
            replaceFragment(f);
        }
    }

    private void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
