package com.alvaro.opos.platform.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alvaro.opos.R;
import com.alvaro.opos.domain.model.exercise.Exercise;
import com.alvaro.opos.presentation.presenter.ExerciseListPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseHolder> {
    private final ExerciseListPresenter presenter;

    public ExerciseAdapter(ExerciseListPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ExerciseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        if (viewGroup instanceof RecyclerView) {
            View view = LayoutInflater
                    .from(viewGroup.getContext())
                    .inflate(R.layout.exercise_row, viewGroup, false);
            return new ExerciseHolder(view);
        } else {
            throw new RuntimeException("Not bound to RecyclerView");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseHolder exerciseHolder, int position) {
        presenter.configureCell(exerciseHolder, position);
    }

    @Override
    public int getItemCount() {
        return presenter.getItemsCount();
    }

    public void refreshData() {
        notifyDataSetChanged();
    }

    public class ExerciseHolder extends RecyclerView.ViewHolder
            implements ExerciseListPresenter.CellView, View.OnClickListener {

        @BindView(R.id.row_question_text)
        TextView questionTextView;

        public ExerciseHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void display(Exercise exercise) {
            questionTextView.setText(exercise.getQuestion());
        }

        @Override
        public void onClick(View view) {
            presenter.onItemClick(getAdapterPosition());
        }
    }
}
