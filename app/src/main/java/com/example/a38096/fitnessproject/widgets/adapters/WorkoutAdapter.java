package com.example.a38096.fitnessproject.widgets.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a38096.fitnessproject.R;
import com.example.a38096.fitnessproject.common.Constants;
import com.example.a38096.fitnessproject.model.entities.Workout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Serhii Boiko on 08.05.2018.
 */
public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> {

    private List<Workout> mWorkouts;
    private OnWorkoutClickListener clickListener;

    public WorkoutAdapter(List<Workout> mWorkouts, OnWorkoutClickListener clickListener) {
        this.mWorkouts = mWorkouts;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.FORMAT, Locale.ENGLISH);
        holder.tvDate.setText(dateFormat.format(new Date(mWorkouts.get(position).getWorkoutDate())));
        holder.tvWorkoutType.setText(mWorkouts.get(position).getType());
        holder.tvCalories.setText(String.valueOf(mWorkouts.get(position).getCalories()));
        holder.tvDuration.setText(String.valueOf(mWorkouts.get(position).getDuration()));
        holder.tvDistance.setText(String.valueOf(mWorkouts.get(position).getDistance()));
    }

    @Override
    public int getItemCount() {
        return mWorkouts == null ? 0 : mWorkouts.size();
    }

    public void updateItems(List<Workout> workouts) {
        mWorkouts = new ArrayList<>(workouts);
        notifyDataSetChanged();
    }

    public void removeWorkout(int workoutId) {
        for (Workout workout : mWorkouts) {
            if (workout.getWorkoutId() == workoutId) {
                mWorkouts.remove(workout);
                notifyDataSetChanged();
                break;
            }
        }

    }

    public interface OnWorkoutClickListener {
        void onWorkoutClick(Workout workout);

        void onWorkoutLongClick(Workout workout);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.tvWorkoutType)
        TextView tvWorkoutType;
        @BindView(R.id.ivEditWorkout)
        ImageView ivEditWorkout;
        @BindView(R.id.tvCalories)
        TextView tvCalories;
        @BindView(R.id.tvDuration)
        TextView tvDuration;
        @BindView(R.id.tvDistance)
        TextView tvDistance;

        ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_workout, parent, false));

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onWorkoutClick(mWorkouts.get(getAdapterPosition()));
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    clickListener.onWorkoutLongClick(mWorkouts.get(getAdapterPosition()));
                    return true;
                }
            });

        }
    }
}
