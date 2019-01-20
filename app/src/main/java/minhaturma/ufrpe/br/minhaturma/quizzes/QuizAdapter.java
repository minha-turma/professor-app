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

        holder.viewTitle.setText(pessoa.getTitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    protected class RecyclerNewsViewHolder extends RecyclerView.ViewHolder {

        protected TextView viewTitle;
        protected TextView viewContent;

        public RecyclerNewsViewHolder(View itemView) {
            super(itemView);
            viewTitle = itemView.findViewById(R.id.textView_newsTitle);
            viewContent = itemView.findViewById(R.id.textView_newsContent);

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
