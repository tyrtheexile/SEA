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

    public void updateBase(int alloy,int carbon,int hydrogen,int energy,int alloyM,int carbonM,int hydrogenM,int energyM)
    {
        TextView alloyT = (TextView) view.findViewById(R.id.alloyText);
        alloyT.setText(Global.make3digit(alloy)+"");
        TextView carbonT = (TextView) view.findViewById(R.id.carbonText);
        carbonT.setText(Global.make3digit(carbon)+"");
        TextView hydrogenT = (TextView) view.findViewById(R.id.hydrogenText);
        hydrogenT.setText(Global.make3digit(hydrogen)+"");
        TextView energyT = (TextView) view.findViewById(R.id.energyText);
        energyT.setText(Global.make3digit(energy)+"");

        TextView alloyTM = (TextView) view.findViewById(R.id.alloyMaxText);
        alloyTM.setText("/ "+Global.make3digit(alloyM)+"");
        TextView carbonTM = (TextView) view.findViewById(R.id.carbonMaxTest);
        carbonTM.setText("/ "+Global.make3digit(carbonM)+"");
        TextView hydrogenTM = (TextView) view.findViewById(R.id.hydrogenMaxText);
        hydrogenTM.setText("/ "+Global.make3digit(hydrogenM)+"");
        TextView energyTM = (TextView) view.findViewById(R.id.energyMaxText);
        energyTM.setText("/ "+Global.make3digit(energyM)+"");
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
            int alloyM= bundle.getInt("alloyM");
            int carbonM= bundle.getInt("carbonM");
            int hydrogenM= bundle.getInt("hydrogenM");
            int energyM= bundle.getInt("energyM");
            updateBase(alloy,carbon,hydrogen,energy,alloyM,carbonM,hydrogenM,energyM);
        }
    };

    public Handler getHandler(){
        return updateBaseHandler;
    }
}
