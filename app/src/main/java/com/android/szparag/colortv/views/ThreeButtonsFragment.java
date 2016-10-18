package com.android.szparag.colortv.views;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.szparag.colortv.ColorTVApplication;
import com.android.szparag.colortv.R;
import com.android.szparag.colortv.presenters.contracts.ThreeButtonsBasePresenter;
import com.android.szparag.colortv.utils.Utils;
import com.android.szparag.colortv.views.contracts.ThreeButtonsBaseView;

import java.io.InputStream;

import javax.inject.Inject;

/**
 * A placeholder fragment containing a simple view.
 */
public class ThreeButtonsFragment extends Fragment implements ThreeButtonsBaseView {

    @Inject
    ThreeButtonsBasePresenter presenter;

    //todo: newInstance
    public ThreeButtonsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Utils.getDagger(this).inject(this);

        presenter.setView(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public InputStream getRawResource(int rawResId) {
        return getContext().getResources().openRawResource(rawResId);
    }

    @Override
    public String getPackageName() {
        return getActivity().getPackageName();
    }



    @Override
    public ColorTVApplication getApplication() {
        return getApplication();
    }

    @Override
    public Fragment getAndroidView() {
        return this;
    }


}
