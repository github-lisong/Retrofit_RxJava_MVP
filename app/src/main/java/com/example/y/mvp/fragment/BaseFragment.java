package com.example.y.mvp.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * by y on 2016/4/28.
 */
@SuppressWarnings("ALL")
public abstract class BaseFragment extends Fragment {

    Activity mActivity;
    boolean isVisible;
    static final String FRAGMENT_INDEX = "fragment_index";
    int index = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        Bundle bundle = getArguments();
        if (bundle != null) {
            index = bundle.getInt(FRAGMENT_INDEX);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = initView();
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    void Toast(String content) {
        Toast.makeText(mActivity, content, Toast.LENGTH_LONG).show();
    }


    private void onVisible() {
        initData();
    }

    private void onInvisible() {
    }

    protected abstract View initView();

    protected abstract void initData();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}