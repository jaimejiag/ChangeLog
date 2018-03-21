package com.example.jaimejimenez.versionslog.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jaimejimenez.versionslog.R;
import com.example.jaimejimenez.versionslog.data.db.models.ChangelogCambio;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaimejimenez on 20/03/18.
 */

public class ChangesAdapter extends RecyclerView.Adapter<ChangesAdapter.ChangesHolder> {
    private List<ChangelogCambio> mChangesList;


    public ChangesAdapter() {
        mChangesList = new ArrayList<>();
    }


    @Override
    public ChangesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_version_change, parent, false);
        ChangesHolder holder = new ChangesHolder(view);

        return holder;
    }


    @Override
    public void onBindViewHolder(ChangesHolder holder, int position) {
        holder.txvChange.setText(mChangesList.get(position).getCambio());
    }


    @Override
    public int getItemCount() {
        return mChangesList.size();
    }


    public void addAll(List<ChangelogCambio> changesList) {
        mChangesList.clear();
        mChangesList.addAll(changesList);
        notifyDataSetChanged();
    }


    public class ChangesHolder extends RecyclerView.ViewHolder {
        private TextView txvChange;

        public ChangesHolder(View itemView) {
            super(itemView);
            txvChange = itemView.findViewById(R.id.txv_version_changes);
        }
    }
}
