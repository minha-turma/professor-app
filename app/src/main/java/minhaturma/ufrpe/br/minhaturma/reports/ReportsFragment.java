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

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import minhaturma.ufrpe.br.minhaturma.R;
import minhaturma.ufrpe.br.minhaturma.commons.MTFragment;
import minhaturma.ufrpe.br.minhaturma.news.ClickRecyclerView_Interface;
import minhaturma.ufrpe.br.minhaturma.presences.PresenceFragment;
import minhaturma.ufrpe.br.minhaturma.reports.items.ConfidenceReportFragment;
import minhaturma.ufrpe.br.minhaturma.reports.items.HumorReportFragment;
import minhaturma.ufrpe.br.minhaturma.reports.items.PresenceReportFragment;
import minhaturma.ufrpe.br.minhaturma.reports.items.QuizReportFragment;

public class ReportsFragment extends Fragment implements MTFragment {

    public static final String TAG = "ReportsFragment";
    static ReportsFragment instance;


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

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showFragment(view);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public String getTitle() {
        return "Relatórios";
    }

    private void showFragment(View view) {
        Button btnQuiz = view.findViewById(R.id.Quiz);
        Button btnPresenca = view.findViewById(R.id.Presenca);
        Button btnHumor = view.findViewById(R.id.Humor);
        Button btnAutoconfianca = view.findViewById(R.id.Autoconfianca);

        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f = new QuizReportFragment();
                replaceFragment(f);
            }
        });
        btnPresenca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f = new PresenceReportFragment();
                replaceFragment(f);
            }
        });
        btnHumor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f = new HumorReportFragment();
                replaceFragment(f);
            }
        });
        btnAutoconfianca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f = new ConfidenceReportFragment();
                replaceFragment(f);
            }
        });
    }

    private void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
