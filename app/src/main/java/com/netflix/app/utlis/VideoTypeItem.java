package com.netflix.app.utlis;

import android.view.LayoutInflater;
import android.view.View;

public interface VideoTypeItem {

    int getViewType();
    View getView(LayoutInflater layoutInflater, View view);

}
