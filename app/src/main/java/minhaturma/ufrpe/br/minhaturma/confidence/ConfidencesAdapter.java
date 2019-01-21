package minhaturma.ufrpe.br.minhaturma.confidence;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import minhaturma.ufrpe.br.minhaturma.R;
import minhaturma.ufrpe.br.minhaturma.messages.Message;

public class ConfidencesAdapter extends RecyclerView.Adapter<ConfidencesAdapter.ViewHolder>{

    Context mContext;
    private List<Confidence> mList;

    public ConfidencesAdapter(Context ctx, List<Confidence> list) {
        this.mContext = ctx;
        this.mList = list;
    }

    @Override
    public ConfidencesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.confidence_item, parent, false);

        itemView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ConfidencesAdapter.ViewHolder holder, int position) {
        Confidence confidence = mList.get(position);

        holder.subject.setText(confidence.getSubject().getName());
        holder.status.setBackgroundResource(R.drawable.smart);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.subject)
        protected TextView subject;
        @BindView(R.id.confidence_status_image)
        protected ImageView status;
        @BindView(R.id.container)
        protected View container;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
