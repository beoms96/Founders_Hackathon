package com.android.samsung.codelab.guestbookdapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.samsung.codelab.guestbookdapp.BR;
import com.android.samsung.codelab.guestbookdapp.databinding.ItemFeelListBinding;
import com.android.samsung.codelab.guestbookdapp.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class FeelIconAdapter extends RecyclerView.Adapter<FeelIconAdapter.ViewHolder> {

    private List<String> emojiList;
    private OnItemSelectListener itemSelectListener;
    private View.OnClickListener itemClickListener = v -> {
        String emoji = (String) v.getTag();
        UserInfo.getInstance().getFeedToWrite().setEmoji(emoji);
        if (itemSelectListener != null) {
            itemSelectListener.onItemSelected();
        }
    };

    public FeelIconAdapter(OnItemSelectListener itemSelectListener) {
        this.itemSelectListener = itemSelectListener;

        this.emojiList = new ArrayList<>();
        emojiList.add("❤️");
        emojiList.add("\uD83D\uDE00");
        emojiList.add("\uD83E\uDD23");
        emojiList.add("\uD83D\uDE0D");
        emojiList.add("\uD83E\uDD29");
        emojiList.add("\uD83E\uDD14");
        emojiList.add("\uD83D\uDE10");
        emojiList.add("\uD83D\uDE44");
        emojiList.add("\uD83E\uDD73");
        emojiList.add("\uD83D\uDE0E");
        emojiList.add("\uD83D\uDC95");
        emojiList.add("\uD83D\uDC4F");
        emojiList.add("\uD83D\uDE29");
        emojiList.add("\uD83D\uDE4F");
        emojiList.add("\uD83D\uDE31");
        emojiList.add("\uD83C\uDF89");
        emojiList.add("\uD83D\uDE1C");
        emojiList.add("\uD83D\uDE21");
        emojiList.add("\uD83D\uDCAF");
        emojiList.add("☕");
        emojiList.add("\uD83C\uDF55");
        emojiList.add("\uD83D\uDE08");
        emojiList.add("\uD83D\uDD25");
        emojiList.add("\uD83D\uDC4D");
        emojiList.add("\uD83E\uDD7A");
        emojiList.add("\uD83E\uDD74");
        emojiList.add("\uD83D\uDC68\u200D\uD83D\uDCBB");
        emojiList.add("\uD83D\uDC69\u200D\uD83D\uDCBB");
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String emoji = emojiList.get(i);
        viewHolder.bind(emoji);
    }

    @Override
    public int getItemCount() {
        return emojiList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemFeelListBinding binding = ItemFeelListBinding.inflate(
                LayoutInflater.from(viewGroup.getContext())
        );
        ViewHolder holder = new ViewHolder(binding);
        holder.itemView.setOnClickListener(itemClickListener);
        return holder;
    }

    public interface OnItemSelectListener {
        void onItemSelected();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemFeelListBinding binding;

        ViewHolder(ItemFeelListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.setFeed(UserInfo.getInstance().getFeedToWrite());
        }

        void bind(String emoji) {
            binding.setVariable(BR.feelEmoji, emoji);
            this.itemView.setTag(emoji);
        }
    }
}
