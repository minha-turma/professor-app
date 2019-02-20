package minhaturma.ufrpe.br.minhaturma.reports.items;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import minhaturma.ufrpe.br.minhaturma.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PresenceReportFragment extends Fragment {

    private static PresenceReportFragment instance;

    public PresenceReportFragment() {
        // Required empty public constructor
    }

    public static PresenceReportFragment getInstance() {
        if (instance == null) {
            instance = new PresenceReportFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_presence_report, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BarChart barChart = view.findViewById(R.id.barchart);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(4f, 0.5f));
        entries.add(new BarEntry(6f, 1));

        BarDataSet dataSet1 = new BarDataSet(entries, "Presenças");
        dataSet1.setColor(Color.BLUE);
        BarDataSet dataSet2 = new BarDataSet(entries, "Faltas");
        dataSet2.setColor(Color.RED);

        BarData data = new BarData(dataSet1, dataSet2);

        barChart.setData(data);
    }

    public String getTitle() {
        return "Relatório das presenças";
    }

}
