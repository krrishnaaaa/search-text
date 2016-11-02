package com.pcsalt.example.searchtext.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pcsalt.example.searchtext.R;
import com.pcsalt.example.searchtext.model.state.Result;

import java.util.List;

/**
 * Created by Navkrishna on 02 November, 2016
 */

public class StateListAdapter extends RecyclerView.Adapter<StateListAdapter.ViewHolder> {

    private List<Result> resultList;

    public StateListAdapter(List<Result> resultList) {
        this.resultList = resultList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Result currentResult = resultList.get(position);
        Context context = holder.itemView.getContext();
        if (currentResult.getLargestCity() == null) {
            holder.tvLargestCity.setVisibility(View.GONE);
        } else {
            holder.tvLargestCity.setVisibility(View.VISIBLE);
            holder.tvLargestCity.setText(String.format(context.getString(R.string.txt_largest_city), currentResult.getLargestCity()));
        }
        holder.tvCapital.setText(String.format(context.getString(R.string.txt_capital), currentResult.getCapital()));
        holder.tvStateName.setText(String.format(context.getString(R.string.txt_state), currentResult.getName()));
    }

    @Override
    public int getItemCount() {
        return resultList == null ? 0 : resultList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvLargestCity, tvCapital, tvStateName;

        ViewHolder(View itemView) {
            super(itemView);
            tvLargestCity = (TextView) itemView.findViewById(R.id.tv_largest_city);
            tvCapital = (TextView) itemView.findViewById(R.id.tv_capital);
            tvStateName = (TextView) itemView.findViewById(R.id.tv_state_name);
        }
    }
}
