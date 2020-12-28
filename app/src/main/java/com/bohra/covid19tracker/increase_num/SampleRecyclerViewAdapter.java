package com.bohra.covid19tracker.increase_num;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bohra.covid19tracker.R;
import com.bohra.covid19tracker.data.covid.StateWise;

import java.util.List;


public class SampleRecyclerViewAdapter extends RecyclerView.Adapter<SampleRecyclerViewAdapter.State_VH> {

    List<StateWise> stateWisesList;
    Context context;
    private SampleRecyclerViewAdapter.OnItemClickListner onItemClickListner;

    int qtyd = 0;

    public SampleRecyclerViewAdapter(List<StateWise> stateWisesList, Context context) {
        this.stateWisesList = stateWisesList;
        this.context = context;
    }

    @NonNull
    @Override
    public SampleRecyclerViewAdapter.State_VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.increase_num_items,parent,false);
        return new SampleRecyclerViewAdapter.State_VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SampleRecyclerViewAdapter.State_VH holder, int position) {
        StateWise stateWiseData = stateWisesList.get(position);

        holder.title.setText(stateWiseData.getState());

        holder.sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qtyd = qtyd-1;
                holder.num.setText(String.valueOf(qtyd));holder.num.setText("" + (qtyd - 1));
            }
        });


        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    qtyd = qtyd+1;
                    holder.num.setText(String.valueOf(qtyd));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return stateWisesList.size();
    }

    class State_VH extends RecyclerView.ViewHolder{

        TextView title,num;
        Button sub,add;

        public State_VH(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            num = itemView.findViewById(R.id.num);
            add = itemView.findViewById(R.id.add);
            sub = itemView.findViewById(R.id.sub);

        }
    }

    public interface OnItemClickListner{
        void onStatelickListner(View view,int postion);
    }
}

