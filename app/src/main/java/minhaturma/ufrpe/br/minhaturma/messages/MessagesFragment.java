package minhaturma.ufrpe.br.minhaturma.messages;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import minhaturma.ufrpe.br.minhaturma.R;

public class MessagesFragment extends Fragment {

    static MessagesFragment instance;

    public static MessagesFragment getInstance() {
        if (instance == null) {
            instance = new MessagesFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_messages, container, false);
    }

}
