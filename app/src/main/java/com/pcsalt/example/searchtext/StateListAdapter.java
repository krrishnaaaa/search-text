package com.pcsalt.example.searchtext;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        holder.tvName.setText(currentResult.getName());
    }

    @Override
    public int getItemCount() {
        return resultList == null ? 0 : resultList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
