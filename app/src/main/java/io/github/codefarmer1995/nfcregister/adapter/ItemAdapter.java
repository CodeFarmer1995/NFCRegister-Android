package io.github.codefarmer1995.nfcregister.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.List;

import io.github.codefarmer1995.nfcregister.adapter.item.MeetingItem;

/**
 * Project: JAViewer
 */

public abstract class ItemAdapter<I, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {


    private List<MeetingItem> items;

    public ItemAdapter(List<MeetingItem> items) {
        this.items = items;
    }

    public List<MeetingItem> getItems() {
        return items;
    }

    public void setItems(List<MeetingItem> items) {
        int size = this.getItems().size();
        if (size > 0) {
            this.getItems().clear();
            notifyItemRangeRemoved(0, size);
        }
        this.getItems().addAll(items);
        notifyItemRangeInserted(0, items.size());
    }

    @Override
    public int getItemCount() {
        return getItems().size();
    }

    @Override
    public void onViewDetachedFromWindow(VH holder) {
        holder.itemView.clearAnimation();
        super.onViewDetachedFromWindow(holder);
    }
}
