package io.github.codefarmer1995.nfcregister.view.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.github.codefarmer1995.nfcregister.view.ViewUtil;


/**
 * Project: JAViewer
 */

public class MeetingItemDecoration extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        Rect rect = new Rect();
        if (parent.getChildAdapterPosition(view) == 0) {
            rect.top = ViewUtil.dpToPx(8);
        }
        rect.bottom = ViewUtil.dpToPx(8);
        outRect.set(rect);
    }
}
