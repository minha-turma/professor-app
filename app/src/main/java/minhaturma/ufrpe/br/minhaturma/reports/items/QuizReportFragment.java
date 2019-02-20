package minhaturma.ufrpe.br.minhaturma.reports.items;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import minhaturma.ufrpe.br.minhaturma.R;
import minhaturma.ufrpe.br.minhaturma.quizzes.Quiz;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizReportFragment extends Fragment {

    private static QuizReportFragment instance;

    public QuizReportFragment() {
        // Required empty public constructor
    }

    public static QuizReportFragment getInstance() {
        if (instance == null) {
            instance = new QuizReportFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_report, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(getTitle());

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("AttemptedQuizzes", Context.MODE_PRIVATE);
        String json = sharedPreferences.getString("AttemptedQuizzes", "");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Quiz>>() {}.getType();
        List<Quiz> quizzes = new ArrayList<>();
        quizzes = gson.fromJson(json, type);

        float acertos = 0f;
        float erros = 0f;

        Log.d("AKE", "Quantidade de quizzes: "+quizzes.size());

        PieChart pieChart = getActivity().findViewById(R.id.chart);

        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(100f, "Erros"));
        entries.add(new PieEntry(0f, "Acertos"));


        PieDataSet set = new PieDataSet(entries, "Resumo dos quizzes");
        set.setColors(new int[]{R.color.redBar , R.color.blueBar} , getActivity().getApplicationContext());
        PieData data = new PieData(set);
        pieChart.setData(data);
        pieChart.invalidate();

    }

    public String getTitle() {
        return "Relatório dos quizzes";
    }

}
