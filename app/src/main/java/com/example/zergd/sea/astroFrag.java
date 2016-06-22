package com.example.zergd.sea;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
//import android.support.v4.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class astroFrag extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.astro_frag, container, false);
        return view;
    }

    public void updateAstro(int air, int water, int food)
    {
        TextView airT = (TextView) view.findViewById(R.id.airText);
        airT.setText(air+"");
        TextView waterT = (TextView) view.findViewById(R.id.waterText);
        waterT.setText(water+"");
        TextView foodT = (TextView) view.findViewById(R.id.foodText);
        foodT.setText(food+"");
    }

    public Handler updateAstroHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            int air= bundle.getInt("air");
            int water= bundle.getInt("water");
            int food= bundle.getInt("food");
            updateAstro(air,water,food);
        }
    };

    public Handler getHandler(){
        return updateAstroHandler;
    }
}
