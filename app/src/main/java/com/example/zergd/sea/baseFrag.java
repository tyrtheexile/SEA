package com.example.zergd.sea;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//import android.support.v4.app.Fragment;

public class baseFrag extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.base_frag, container, false);
        return view;
    }

    public void updateBase(int alloy,int carbon,int hydrogen,int energy)
    {
        TextView alloyT = (TextView) view.findViewById(R.id.alloyText);
        alloyT.setText(alloy+"");
        TextView carbonT = (TextView) view.findViewById(R.id.carbonText);
        carbonT.setText(carbon+"");
        TextView hydrogenT = (TextView) view.findViewById(R.id.hydrogenText);
        hydrogenT.setText(hydrogen+"");
        TextView energyT = (TextView) view.findViewById(R.id.energyText);
        energyT.setText(energy+"");
    }

    //Update handler
    public Handler updateBaseHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            int alloy= bundle.getInt("alloy");
            int carbon= bundle.getInt("carbon");
            int hydrogen= bundle.getInt("hydrogen");
            int energy= bundle.getInt("energy");
            updateBase(alloy,carbon,hydrogen,energy);
        }
    };

    public Handler getHandler(){
        return updateBaseHandler;
    }
}
