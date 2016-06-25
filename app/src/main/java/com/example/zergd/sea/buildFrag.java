package com.example.zergd.sea;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class buildFrag extends Fragment{

    private static View view;
    private ImageButton r0c0,r0c1,r0c2,r0c3,r0c4;
    private ImageButton r1c0,r1c1,r1c2,r1c3,r1c4;
    private ImageButton r2c0,r2c1,r2c2,r2c3,r2c4;
    private ImageButton r3c0,r3c1,r3c2,r3c3,r3c4;
    private ImageButton r4c0,r4c1,r4c2,r4c3,r4c4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.multi_choice_frag, container, false);
        r0c0 = (ImageButton) view.findViewById(R.id.r0c0);
        r0c1 = (ImageButton) view.findViewById(R.id.r0c1);
        r0c2 = (ImageButton) view.findViewById(R.id.r0c2);
        r0c3 = (ImageButton) view.findViewById(R.id.r0c3);
        r0c4 = (ImageButton) view.findViewById(R.id.r0c4);
        r1c0 = (ImageButton) view.findViewById(R.id.r1c0);
        r1c1 = (ImageButton) view.findViewById(R.id.r1c1);
        r1c2 = (ImageButton) view.findViewById(R.id.r1c2);
        r1c3 = (ImageButton) view.findViewById(R.id.r1c3);
        r1c4 = (ImageButton) view.findViewById(R.id.r1c4);
        r2c0 = (ImageButton) view.findViewById(R.id.r2c0);
        r2c1 = (ImageButton) view.findViewById(R.id.r2c1);
        r2c2 = (ImageButton) view.findViewById(R.id.r2c2);
        r2c3 = (ImageButton) view.findViewById(R.id.r2c3);
        r2c4 = (ImageButton) view.findViewById(R.id.r2c4);
        r3c0 = (ImageButton) view.findViewById(R.id.r3c0);
        r3c1 = (ImageButton) view.findViewById(R.id.r3c1);
        r3c2 = (ImageButton) view.findViewById(R.id.r3c2);
        r3c3 = (ImageButton) view.findViewById(R.id.r3c3);
        r3c4 = (ImageButton) view.findViewById(R.id.r3c4);
        r4c0 = (ImageButton) view.findViewById(R.id.r4c0);
        r4c1 = (ImageButton) view.findViewById(R.id.r4c1);
        r4c2 = (ImageButton) view.findViewById(R.id.r4c2);
        r4c3 = (ImageButton) view.findViewById(R.id.r4c3);
        r4c4 = (ImageButton) view.findViewById(R.id.r4c4);
        return view;
    }

    private ImageButton findButton(int row,int col){
        switch(row) {
            case 0:
                switch (col) {
                    case 0: return r0c0;
                    case 1: return r0c1;
                    case 2: return r0c2;
                    case 3: return r0c3;
                    case 4: return r0c4;
                    default: Global.log("Bad Col num in findButton (buildFrag)");
                }
            case 1:
                switch (col) {
                    case 0: return r1c0;
                    case 1: return r1c1;
                    case 2: return r1c2;
                    case 3: return r1c3;
                    case 4: return r1c4;
                    default: Global.log("Bad Col num in findButton (buildFrag)");
                }
            case 2:
                switch (col) {
                    case 0: return r2c0;
                    case 1: return r2c1;
                    case 2: return r2c2;
                    case 3: return r2c3;
                    case 4: return r2c4;
                    default: Global.log("Bad Col num in findButton (buildFrag)");
                }
            case 3:
                switch (col) {
                    case 0: return r3c0;
                    case 1: return r3c1;
                    case 2: return r3c2;
                    case 3: return r3c3;
                    case 4: return r3c4;
                    default: Global.log("Bad Col num in findButton (buildFrag)");
                }
            case 4:
                switch (col) {
                    case 0: return r4c0;
                    case 1: return r4c1;
                    case 2: return r4c2;
                    case 3: return r4c3;
                    case 4: return r4c4;
                    default: Global.log("Bad Col num in findButton (buildFrag)");
                }
            default: Global.log("Bad Row num in findButton (buildFrag)");
                return null;
        }

    }

}
