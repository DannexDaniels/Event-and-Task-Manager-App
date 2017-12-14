package com.dannextech.apps.eventmanager;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amulyakhare.textdrawable.util.ColorGenerator;

/**
 * Created by amoh on 12/8/2017.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private EventTaskDetails[] eventTaskDetails;
    ColorGenerator generator = ColorGenerator.MATERIAL;

    public EventAdapter(EventTaskDetails[] eventTaskDetails) {
        this.eventTaskDetails = eventTaskDetails;
        Log.e("AdapterMsg",String.valueOf(eventTaskDetails.length));
        Log.e("AdapterMsg",String.valueOf(eventTaskDetails.length));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_details,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(eventTaskDetails[position].getTitle());
        holder.tvVenue.setText(eventTaskDetails[position].getVenue());
        holder.tvDate.setText(eventTaskDetails[position].getDate());
        holder.tvTime.setText(eventTaskDetails[position].getTime());



        holder.cvEventDetails.setCardBackgroundColor(generator.getRandomColor());
    }

    @Override
    public int getItemCount() {
        return eventTaskDetails.length == 0 ? 0 : eventTaskDetails.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,tvVenue,tvDate,tvTime;
        CardView cvEventDetails;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvETitle);
            tvVenue = itemView.findViewById(R.id.tvEVenue);
            tvDate = itemView.findViewById(R.id.tvEDate);
            tvTime = itemView.findViewById(R.id.tvETime);
            cvEventDetails = itemView.findViewById(R.id.cvEventDetails);
        }
    }
}
