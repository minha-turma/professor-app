package minhaturma.ufrpe.br.minhaturma.reports.items;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import minhaturma.ufrpe.br.minhaturma.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfidenceReportFragment extends Fragment {

    private static ConfidenceReportFragment instance;

    public ConfidenceReportFragment() {
        // Required empty public constructor
    }

    public static ConfidenceReportFragment getInstance() {
        if (instance == null) {
            instance = new ConfidenceReportFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confidence_report, container, false);
    }

    public String getTitle() {
        return "Relatório da autoconfiança";
    }

}
