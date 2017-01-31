package com.example.guillaume.tp3_mmm.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guillaume.tp3_mmm.R;
import com.example.guillaume.tp3_mmm.fragments.RegionListFragment.OnListFragmentInteractionListener;
import com.example.guillaume.tp3_mmm.model.Region;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Region} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class RegionRecyclerViewAdapter extends RecyclerView.Adapter<RegionRecyclerViewAdapter.ViewHolder> {

    private final List<Region> regions;
    private final OnListFragmentInteractionListener mListener;

    public RegionRecyclerViewAdapter(List<Region> items, OnListFragmentInteractionListener listener) {
        regions = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.region_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = regions.get(position);
        holder.textName.setText(regions.get(position).getName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return regions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textName;
        public Region mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textName = (TextView) view.findViewById(R.id.region_name);
        }

        @Override
        public String toString() {
            return super.toString() + " '";
        }
    }
}
