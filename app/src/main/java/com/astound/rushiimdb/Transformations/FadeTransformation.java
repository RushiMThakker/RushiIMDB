package com.astound.rushiimdb.Transformations;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;

import com.astound.rushiimdb.R;

/**
 * Created by Astound Rushi on 8/31/2016.
 */

public class FadeTransformation implements ViewPager.PageTransformer
{

    @Override
    public void transformPage(View page, float position)
    {
        CardView currentView=(CardView)page.findViewById(R.id.card_view);
        if (position < -1)
        { // [-Infinity,-1)
            // This page is way off-screen to the left.
            currentView.setCardElevation(0);
            page.setAlpha(0.85f);
        } else if (position <= 1)
        { // Page to the left, page centered, page to the right
            // modify page view animations here for pages in view
            page.setAlpha((1 - Math.abs(position)));
        } else
        { // (1,+Infinity]
            // This page is way off-screen to the right.
            currentView.setCardElevation(0);
            page.setAlpha(0.85f);
        }
    }
}
