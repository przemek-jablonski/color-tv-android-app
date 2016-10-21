package com.android.szparag.colortv.adapters;

import android.view.View;

/**
 * Created by ciemek on 20/10/2016.
 */

/**
 * Custom onClick interface with specified view position.
 * Used in BaseAdapter<T>.
 */
public interface RecyclerOnPosClickListener {

    void OnPosClick(View v, int position);

}
