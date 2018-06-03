package sk.brecka.sygicrecruitment.view;

/**
 * Created by Matej on 31. 8. 2016.
 */

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ListItemMarginDecoration extends RecyclerView.ItemDecoration {

    private final int mSpaceSize;
    private boolean mUseHorizontalSpaces;

    public ListItemMarginDecoration(int spaceSize) {
        mSpaceSize = spaceSize;
    }

    public ListItemMarginDecoration(float spaceSize) {
        mSpaceSize = (int) spaceSize;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        final int position = parent.getChildAdapterPosition(view);
        if (position == 0) {
            outRect.top = mSpaceSize;
            outRect.bottom = (int) (mSpaceSize / 2F);

        } else if (position == state.getItemCount() - 1) {
            outRect.top = (int) (mSpaceSize / 2F);
            outRect.bottom = mSpaceSize;

        } else {
            outRect.top = (int) (mSpaceSize / 2F);
            outRect.bottom = (int) (mSpaceSize / 2F);

        }

        outRect.left = mSpaceSize;
        outRect.right = mSpaceSize;
    }
}

