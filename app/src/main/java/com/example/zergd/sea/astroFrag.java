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

// TODO UPdate with Max values, and number shortner k,m,b etc

public class astroFrag extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.astro_frag, container, false);
        return view;
    }

    public void updateAstro(int air, int water, int food,int airM,int waterM,int foodM)
    {
        TextView airT = (TextView) view.findViewById(R.id.airText);
        airT.setText(Global.make3digit(air)+"");
        TextView waterT = (TextView) view.findViewById(R.id.waterText);
        waterT.setText(Global.make3digit(water)+"");
        TextView foodT = (TextView) view.findViewById(R.id.foodText);
        foodT.setText(Global.make3digit(food)+"");
        TextView airMT = (TextView) view.findViewById(R.id.airText);
        airMT.setText(Global.make3digit(airM)+"");
        TextView waterMT = (TextView) view.findViewById(R.id.waterText);
        waterMT.setText(Global.make3digit(waterM)+"");
        TextView foodMT = (TextView) view.findViewById(R.id.foodText);
        foodMT.setText(Global.make3digit(foodM)+"");
    }

    //Update handler
    public Handler updateAstroHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            int air= bundle.getInt("air");
            int water= bundle.getInt("water");
            int food= bundle.getInt("food");
            int airM=bundle.getInt("airM");
            int waterM=bundle.getInt("waterM");
            int foodM=bundle.getInt("foodM");
            updateAstro(air,water,food,airM,waterM,foodM);
        }
    };

    public Handler getHandler(){
        return updateAstroHandler;
    }
}
