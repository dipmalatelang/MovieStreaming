package com.netflix.app.utlis;

import android.view.LayoutInflater;
import android.view.View;

public interface VideoTypeItem {

    public int getViewType();
    public View getView(LayoutInflater layoutInflater, View view);

}
