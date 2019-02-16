package minhaturma.ufrpe.br.minhaturma.news;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import minhaturma.ufrpe.br.minhaturma.R;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.RecyclerNewsViewHolder>{

    public static ClickRecyclerView_Interface clickRecyclerViewInterface;
    Context mctx;
    private List<News> mList;

    public NewsAdapter(Context ctx, List<News> list, ClickRecyclerView_Interface clickRecyclerViewInterface) {
        this.mctx = ctx;
        this.mList = list;
        this.clickRecyclerViewInterface = clickRecyclerViewInterface;
    }

    @Override
    public NewsAdapter.RecyclerNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_news, parent, false);
        return new RecyclerNewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.RecyclerNewsViewHolder holder, int position) {
        News pessoa = mList.get(position);

        holder.viewTitle.setText(pessoa.tituloVideo);
        if(pessoa.getLinkVideo().contains("khan")){
            holder.viewContent.setText("Khan Academy");
        }else{
            holder.viewContent.setText("You Tube");
        }

        holder.link = pessoa.linkVideo;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    protected class RecyclerNewsViewHolder extends RecyclerView.ViewHolder {

        protected TextView viewTitle;
        protected TextView viewContent;
        protected String link;

        public RecyclerNewsViewHolder(View itemView) {
            super(itemView);
            viewTitle = itemView.findViewById(R.id.link);
            viewContent = itemView.findViewById(R.id.source);

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
