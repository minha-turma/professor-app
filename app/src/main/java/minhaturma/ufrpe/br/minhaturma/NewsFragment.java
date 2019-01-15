package minhaturma.ufrpe.br.minhaturma;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class NewsFragment extends Fragment implements ClickRecyclerView_Interface{

    static NewsFragment instance;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    NewsAdapter adapter;
    private List<NewsModel> newsList = new ArrayList<>();

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

        setaRecyclerView();
    }

    public void setaRecyclerView(){

        //Aqui é instanciado o Recyclerview
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.news_recyclerview);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        newsList = generateList();

        adapter = new NewsAdapter(getContext(), newsList, this);
        mRecyclerView.setAdapter(adapter);
    }

    private List<NewsModel> generateList(){
        List<NewsModel> list = new ArrayList<>();

        for(int i=0;i<20;i++){
            NewsModel n = new NewsModel(i+"");
            list.add(n);
        }
        return list;
    }


    @Override
    public void onCustomClick(Object object) {

    }
}
