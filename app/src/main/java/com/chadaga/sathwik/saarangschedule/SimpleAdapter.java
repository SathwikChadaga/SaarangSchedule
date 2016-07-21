package com.chadaga.sathwik.saarangschedule;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * Created by Sathwik on 07-07-2016
 */

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.ViewHolder> {
    //
    Context context;
    List<Event> events;
    int fragment;
    DatabaseHandler db;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView title, subtitle, time;
        public final RelativeLayout RLEventItem;
        public final FloatingActionButton fab;


        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            title = (TextView) itemView.findViewById(R.id.text_decibels);
            subtitle = (TextView) itemView.findViewById(R.id.text_event);
            RLEventItem = (RelativeLayout) itemView.findViewById(R.id.rl_event_item);
            fab = (FloatingActionButton) itemView.findViewById(R.id.fab_event_add);
            time = (TextView) itemView.findViewById(R.id.tv_time);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        context = parent.getContext();
        db = new DatabaseHandler(context);
        return new ViewHolder(view);
    }

    void setFragment(int ipday) {
        fragment = ipday;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
//        events.get(position).getTitle()


        events = db.getEvents(fragment);
        holder.title.setText(events.get(position).getTitle());
        holder.subtitle.setText(events.get(position).getSubtitle());
        holder.time.setText(events.get(position).getTime());
        holder.RLEventItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Toast toast = Toast.makeText(context, "This click navigates to\nDETAILS PAGE of " + events.get(position).getTitle(), Toast.LENGTH_SHORT);
                toast.show();

               // TODO: Start new intent to show details of the event
            }
        });
        holder.fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Toast toast = Toast.makeText(context, "This click navigates to\nREGISTRATIONS PAGE", Toast.LENGTH_SHORT);
                toast.show();
                // TODO: Start new intent to navigate to registrations page
            }
        });

    }

    @Override
    public int getItemCount() {
        return 5;
    }

}

