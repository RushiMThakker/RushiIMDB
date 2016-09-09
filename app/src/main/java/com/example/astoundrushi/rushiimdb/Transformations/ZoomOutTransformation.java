package com.example.astoundrushi.rushiimdb.transformations;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.astoundrushi.rushiimdb.R;


/**
 * Created by Astound Rushi on 8/31/2016.
 */

public class ZoomOutTransformation implements ViewPager.PageTransformer
{

    @Override
    public void transformPage(View page, float position)
    {
        int pageWidth = page.getWidth();
        int pageHeight = page.getHeight();
        float MIN_SCALE = 0.5f;
        float MIN_ALPHA = 0.5f;
        CardView currentView=(CardView)page.findViewById(R.id.card_view);

        if (position < -1)
        { // [-Infinity,-1)
            // This page is way off-screen to the left.
            currentView.setCardElevation(0);
            page.setAlpha(0.5f);
        } else if (position <= 1)
        { // Page to the left, page centered, page to the right
            // modify page view animations here for pages in view
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float vertMargin = pageHeight * (1 - scaleFactor) / 2;
            float horzMargin = pageWidth * (1 - scaleFactor) / 2;
            if (position < 0)
            {
                page.setTranslationX(horzMargin - vertMargin / 2);
            } else
            {
                page.setTranslationX(-horzMargin + vertMargin / 2);
            }

            // Scale the page down (between MIN_SCALE and 1)
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);

            // Fade the page relative to its size.
            page.setAlpha(MIN_ALPHA +
                    (scaleFactor - MIN_SCALE) /
                            (1 - MIN_SCALE) * (1 - MIN_ALPHA));
        } else
        { // (1,+Infinity]
            // This page is way off-screen to the right.
            currentView.setCardElevation(0);
            page.setAlpha(0.5f);
        }
    }
}