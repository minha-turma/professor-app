package minhaturma.ufrpe.br.minhaturma.presences;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import minhaturma.ufrpe.br.minhaturma.R;

public class PresenceFragment extends Fragment {

    static PresenceFragment instance;

    public static PresenceFragment getInstance() {
        if (instance == null) {
            instance = new PresenceFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_presence, container, false);
    }

}
