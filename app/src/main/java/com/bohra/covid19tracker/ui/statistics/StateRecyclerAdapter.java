package com.bohra.covid19tracker.ui.statistics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bohra.covid19tracker.R;
import com.bohra.covid19tracker.data.covid.StateWise;

import java.util.List;

public class StateRecyclerAdapter extends RecyclerView.Adapter<StateRecyclerAdapter.State_VH> {

    List<StateWise> stateWisesList;
    Context context;
    private StateRecyclerAdapter.OnItemClickListner onItemClickListner;

    public StateRecyclerAdapter(List<StateWise> stateWisesList, Context context) {
        this.stateWisesList = stateWisesList;
        this.context = context;
    }

    public void setOnItemClickListner(StateRecyclerAdapter.OnItemClickListner onItemClickListner){
        this.onItemClickListner = onItemClickListner;
    }

    @NonNull
    @Override
    public State_VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.state_list_items,parent,false);
        return new State_VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull State_VH holder, int position) {
        StateWise stateWiseData = stateWisesList.get(position);
        holder.stateCode.setText(stateWiseData.getStatecode());
        holder.stateName.setText(stateWiseData.getState());

        holder.stateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListner.onStatelickListner(holder.itemView,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stateWisesList.size();
    }

    class State_VH extends RecyclerView.ViewHolder{

        TextView stateCode,stateName;
        ConstraintLayout stateLayout;

        public State_VH(@NonNull View itemView) {
            super(itemView);

            stateCode = itemView.findViewById(R.id.stateCode);
            stateName = itemView.findViewById(R.id.stateName);
            stateLayout = itemView.findViewById(R.id.stateLayout);
        }
    }

    public interface OnItemClickListner{
        void onStatelickListner(View view,int postion);
    }
}
