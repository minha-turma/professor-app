package minhaturma.ufrpe.br.minhaturma.news;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import minhaturma.ufrpe.br.minhaturma.R;
import minhaturma.ufrpe.br.minhaturma.commons.MTFragment;

public class NewsFragment extends Fragment implements ClickRecyclerView_Interface, Runnable, MTFragment {

    public static final String TAG = "NewsFragment" ;
    static NewsFragment instance;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    NewsAdapter adapter;
    private List<News> newsList = new ArrayList<>();

    HttpService service;

    public static NewsFragment getInstance() {
        if (instance == null) {
            instance = new NewsFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        run();
        setaRecyclerView();
    }

    public void setaRecyclerView(){

        run();

    }

    private List<News> generateList(){
        List<News> list = new ArrayList<>();

        try {
            list = service.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public void onCustomClick(Object object) {

        News n = (News)object;
        //Log.d("News","Link: "+n.linkVideo);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(n.linkVideo));
        startActivity(browserIntent);

    }

    @Override
    public void run() {
        Log.d("News","RUN");

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Topic", Context.MODE_PRIVATE);
        String result = sharedPreferences.getString("Topic", "");

        String url;

        if(result == null || result.equals("")){
            url = "https://da-recomendacao.herokuapp.com/recomendacao?assunto=ecologia";
        }else{
            url = "https://da-recomendacao.herokuapp.com/recomendacao?assunto="+result;
        }
        Log.e("AKE","URL: "+result);

        service = new HttpService(url);
        //Aqui é instanciado o Recyclerview

        newsList = generateList();

        mRecyclerView = getView().findViewById(R.id.news_recyclerview);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);


        adapter = new NewsAdapter(getContext(), newsList, this);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    public void onRefresh() {
        setaRecyclerView();
    }

    @Override
    public String getTitle() {
        return null;
    }
}
