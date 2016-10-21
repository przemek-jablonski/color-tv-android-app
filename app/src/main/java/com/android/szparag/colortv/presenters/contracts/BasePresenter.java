package com.android.szparag.colortv.presenters.contracts;

import com.android.szparag.colortv.views.contracts.BaseAndroidView;

/**
 * Created by ciemek on 17/10/2016.
 */

public interface BasePresenter<T extends BaseAndroidView> {

    void setView(T view);
}
