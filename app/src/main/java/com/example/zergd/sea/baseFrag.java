package com.example.zergd.sea;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//import android.support.v4.app.Fragment;

public class baseFrag extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_frag, container, false);
        TextView hydrogen = (TextView) view.findViewById(R.id.hydrogenText);
        hydrogen.setText("100");
        return view;
    }
}
