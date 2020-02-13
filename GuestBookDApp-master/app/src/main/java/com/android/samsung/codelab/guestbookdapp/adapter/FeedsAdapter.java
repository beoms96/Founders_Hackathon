package com.android.samsung.codelab.guestbookdapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.android.samsung.codelab.guestbookdapp.databinding.ItemFeedListBinding;
import com.android.samsung.codelab.guestbookdapp.model.Feed;

import java.util.ArrayList;
import java.util.List;

public class FeedsAdapter extends RecyclerView.Adapter<FeedsAdapter.ViewHolder> {

    private List<Feed> feedList;

    public FeedsAdapter() {
        this.feedList = new ArrayList<>();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Feed feed = feedList.get(i);
        viewHolder.bind(feed);
    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

    void setItem(List<Feed> movie) {
        if (movie == null) {
            return;
        }
        this.feedList = movie;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemFeedListBinding binding = ItemFeedListBinding.inflate(
                LayoutInflater.from(viewGroup.getContext())
                , viewGroup
                , false
        );
        return new ViewHolder(binding);
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ItemFeedListBinding binding;

        ViewHolder(ItemFeedListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Feed feed) {
            binding.setVariable(BR.feed, feed);
        }
    }
}
