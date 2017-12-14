package com.dannextech.apps.eventmanager;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amulyakhare.textdrawable.util.ColorGenerator;

/**
 * Created by amoh on 12/9/2017.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private EventTaskDetails[] taskDetails;
    ColorGenerator generator = ColorGenerator.MATERIAL;

    public TaskAdapter(EventTaskDetails[] taskDetails) {
        this.taskDetails = taskDetails;
    }

    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_details,parent,false);
        TaskAdapter.ViewHolder viewHolder = new TaskAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TaskAdapter.ViewHolder holder, int position) {
        holder.tvTitle.setText(taskDetails[position].getTitle());
        holder.tvDate.setText(taskDetails[position].getDate());
        holder.tvTime.setText(taskDetails[position].getTime());

        holder.cvTaskDetails.setCardBackgroundColor(generator.getRandomColor());
    }

    @Override
    public int getItemCount() {
        return taskDetails.length == 0 ? 0 : taskDetails.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,tvDate,tvTime;
        CardView cvTaskDetails;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTTitle);
            tvDate = itemView.findViewById(R.id.tvTDate);
            tvTime = itemView.findViewById(R.id.tvTTime);
            cvTaskDetails = itemView.findViewById(R.id.cvTaskDetails);
        }
    }
}
