package minhaturma.ufrpe.br.minhaturma.messages;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import minhaturma.ufrpe.br.minhaturma.R;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder>{

    Context mContext;
    private List<Message> mList;

    public MessagesAdapter(Context ctx, List<Message> list) {
        this.mContext = ctx;
        this.mList = list;
    }

    @Override
    public MessagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);

        itemView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MessagesAdapter.ViewHolder holder, int position) {
        Message message = mList.get(position);

        holder.subject.setText(message.getSubject());
        holder.content.setText(message.getContent());
        holder.sender.setText("De: " + message.getOwner().getName());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YY HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-06:00"));

        holder.date.setText(dateFormat.format(message.getDate()));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.subject)
        protected TextView subject;
        @BindView(R.id.content)
        protected TextView content;
        @BindView(R.id.date)
        protected TextView date;
        @BindView(R.id.from)
        protected TextView sender;
        @BindView(R.id.container)
        protected View container;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            //Setup the click listener
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    clickRecyclerViewInterface.onCustomClick(mList.get(getLayoutPosition()));
                }
            });
        }
    }
}
