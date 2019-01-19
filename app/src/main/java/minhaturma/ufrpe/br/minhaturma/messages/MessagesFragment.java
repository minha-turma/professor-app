package minhaturma.ufrpe.br.minhaturma.messages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import minhaturma.ufrpe.br.minhaturma.R;
import minhaturma.ufrpe.br.minhaturma.commons.EntityObserver;
import minhaturma.ufrpe.br.minhaturma.commons.RefreshableView;
import minhaturma.ufrpe.br.minhaturma.network.requests.MessageService;

public class MessagesFragment extends Fragment implements RefreshableView {

    @BindView(R.id.messages)
    RecyclerView mMessagesView;
    @BindView(R.id.empty_messages)
    TextView mEmptyMessagesView;

    static MessagesFragment instance;
    public static String TAG = "NewsFragment";

    MessageService mMessageService;

    public static MessagesFragment getInstance() {
        if (instance == null) {
            instance = new MessagesFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_messages, container, false);
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

    private void refreshList() {
        mMessageService = new MessageService();
        mMessageService.list(new EntityObserver<List<Message>>() {
            @Override
            public void onNext(List<Message> value) {
                MessagesAdapter adapter = new MessagesAdapter(getContext(), value);

                mMessagesView.setLayoutManager(new LinearLayoutManager(getContext()));
                mMessagesView.setAdapter(adapter);

                mEmptyMessagesView.setVisibility(value.isEmpty() ? View.VISIBLE : View.GONE);

            }
        });
    }
}
