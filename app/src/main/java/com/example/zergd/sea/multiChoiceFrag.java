package com.example.zergd.sea;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class multiChoiceFrag extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.multi_choice_frag, container, false);
        Button but = (Button) view.findViewById(R.id.button1);
        but.setText("Testing");
        return view;
    }

}
