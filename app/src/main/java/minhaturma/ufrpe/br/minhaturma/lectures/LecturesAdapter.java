package minhaturma.ufrpe.br.minhaturma.lectures;

import android.content.Context;
import android.support.v4.content.ContextCompat;
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
import minhaturma.ufrpe.br.minhaturma.messages.Message;

public class LecturesAdapter extends RecyclerView.Adapter<LecturesAdapter.ViewHolder>{

    public interface OnLectureClickListener {
        void onLectureClick(Lecture lecture);
    }

    Context mContext;
    private List<Lecture> mList;
    private OnLectureClickListener mListener;

    private boolean isOpen = false;

    public LecturesAdapter(Context ctx, List<Lecture> list, boolean isOpen) {
        this.mContext = ctx;
        this.mList = list;
        this.isOpen = isOpen;
    }

    public void setOnLectureClickListener(OnLectureClickListener listener) {
        mListener = listener;
    }

    @Override
    public LecturesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lecture_item, parent, false);

        itemView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LecturesAdapter.ViewHolder holder, int position) {
        Lecture lecture = mList.get(position);

        holder.subject.setText(lecture.getSubject().getName());
        holder.professor.setText(lecture.getOwner().getName());

        if (isOpen) {
            holder.status.setText("Stauts: Em andamento");
            holder.status.setTextColor(ContextCompat.getColor(mContext, android.R.color.holo_green_dark));
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YY HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-06:00"));

        holder.date.setText(dateFormat.format(lecture.getDate()));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.subject)
        protected TextView subject;
        @BindView(R.id.professor)
        protected TextView professor;
        @BindView(R.id.date)
        protected TextView date;
        @BindView(R.id.status)
        protected TextView status;
        @BindView(R.id.container)
        protected View container;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            //Setup the click listener
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onLectureClick(mList.get(getLayoutPosition()));
                    }
                }
            });
        }
    }
}
