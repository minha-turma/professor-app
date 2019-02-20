package minhaturma.ufrpe.br.minhaturma.reports;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import minhaturma.ufrpe.br.minhaturma.R;
import minhaturma.ufrpe.br.minhaturma.news.ClickRecyclerView_Interface;

import static minhaturma.ufrpe.br.minhaturma.news.NewsAdapter.clickRecyclerViewInterface;

public class ReportsAdapter extends RecyclerView.Adapter<ReportsAdapter.RecyclerReportsViewHolder> {

    public static ClickRecyclerView_Interface clickRecyclerViewInterface;
    Context mctx;
    private List<Reports> mList;

    public ReportsAdapter(Context ctx, List<Reports> list, ClickRecyclerView_Interface clickRecyclerViewInterface) {
        this.mctx = ctx;
        this.mList = list;
        this.clickRecyclerViewInterface = clickRecyclerViewInterface;
    }

    @Override
    public RecyclerReportsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_news, parent, false);
        return new RecyclerReportsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerReportsViewHolder holder, int position) {
        Reports report = mList.get(position);

        holder.viewTitle.setText(report.tituloReport);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    protected class RecyclerReportsViewHolder extends RecyclerView.ViewHolder {

        protected TextView viewTitle;

        public RecyclerReportsViewHolder(View itemView) {
            super(itemView);
            viewTitle = itemView.findViewById(R.id.reports);

            //Setup the click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    clickRecyclerViewInterface.onCustomClick(mList.get(getLayoutPosition()));

                }
            });
        }
    }
}
