package com.astound.rushiimdb.Transformations;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Astound Rushi on 8/31/2016.
 */

public class ScaleTransformation implements ViewPager.PageTransformer
{

    @Override
    public void transformPage(View page, float position)
    {


        if (position < -1)
        { // [-Infinity,-1)
            // This page is way off-screen to the left.
            page.setAlpha(0.85f);
        } else if (position <= 1)
        { // Page to the left, page centered, page to the right
            // modify page view animations here for pages in view
            final float normalizedposition = Math.abs(Math.abs(position) - 1);
            page.setScaleX(normalizedposition / 2 + 0.5f);
            page.setScaleY(normalizedposition / 2 + 0.5f);
            page.setAlpha(1);
        } else
        { // (1,+Infinity]
            // This page is way off-screen to the right.
            page.setAlpha(0.85f);
        }
    }
}