package com.android.szparag.colortv.presenters.contracts;

import com.android.szparag.colortv.views.contracts.BaseAndroidView;
import com.android.szparag.colortv.views.contracts.ThreeButtonsBaseView;

/**
 * Created by ciemek on 17/10/2016.
 */

public interface ThreeButtonsBasePresenter<T extends ThreeButtonsBaseView> extends BasePresenter<T> {

    void setView(ThreeButtonsBaseView view);

    void killRealm();

}
