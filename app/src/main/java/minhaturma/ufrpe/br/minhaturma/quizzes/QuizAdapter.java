package minhaturma.ufrpe.br.minhaturma.quizzes;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.nio.file.Path;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import minhaturma.ufrpe.br.minhaturma.R;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder>{

    public interface OnQuizClickListener {
        void onQuizClick(Quiz quiz);
    }

    public OnQuizClickListener listener;
    Context mContext;
    private List<Quiz> mList;
    private List<Answer> mAnswers;

    boolean isOpen;

    public void setOnQuizClickListener(OnQuizClickListener listener) {
        this.listener = listener;
    }

    public QuizAdapter(Context ctx, List<Quiz> list) {
        this.mContext = ctx;
        this.mList = list;
        this.listener = listener;
        this.isOpen = isOpen;
    }

    public QuizAdapter(Context ctx, List<Quiz> list, List<Answer> answers) {
        this.mContext = ctx;
        this.mList = list;
        this.listener = listener;
        this.isOpen = true;
        this.mAnswers = answers;
    }

    @Override
    public QuizAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(QuizAdapter.ViewHolder holder, int position) {
        Quiz quiz = mList.get(position);

        holder.statement.setText(quiz.getStatement());

        if (isOpen) {
            String answerText = "";
            for (Answer answer : mAnswers) {
                if (answer.getQuiz().getStatement().equals(quiz.statement)) {
                    answerText = answer.getChoice();
                }
            }
            holder.hint.setText(String.format("Sua resposta: %s | Gabarito: %s", answerText, quiz.correct));
            int color = answerText.equals(quiz.correct) ? android.R.color.holo_green_dark : android.R.color.holo_red_dark;
            holder.hint.setTextColor(ContextCompat.getColor(mContext, color));
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.statement)
        protected TextView statement;
        @BindView(R.id.quiz_container)
        protected View container;
        @BindView(R.id.hint_text)
        protected TextView hint;

        public ViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);

            //Setup the click listener
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listener != null) {
                        listener.onQuizClick(mList.get(getLayoutPosition()));
                    }
                }
            });
        }
    }
}
