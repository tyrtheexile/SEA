package com.example.zergd.sea;


import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class multiChoiceFrag extends Fragment {

    private static View view;
    private static Button but1b;
    private static Button but2b;
    private static Button but3b;
    private static Button but4b;
    private static Button but5b;
    private static Button but6b;

    public static int choice =0;
    public static Boolean choiceFlag=false;

    static Handler choiceHandler;

    public void addHandler(Handler c)
    {
        choiceHandler=c;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.multi_choice_frag, container, false);
        Button but = (Button) view.findViewById(R.id.button1);
        return view;
    }

    public static Handler updateMulti = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String but1 = bundle.getString("but1");
            String but2 = bundle.getString("but2");
            String but3 = bundle.getString("but3");
            String but4 = bundle.getString("but4");
            String but5 = bundle.getString("but5");
            String but6 = bundle.getString("but6");
            but1b = (Button) view.findViewById(R.id.button1);
            but2b = (Button) view.findViewById(R.id.button2);
            but3b = (Button) view.findViewById(R.id.button3);
            but4b = (Button) view.findViewById(R.id.button4);
            but5b = (Button) view.findViewById(R.id.button5);
            but6b = (Button) view.findViewById(R.id.button6);
            but1b.setText(but1);
            but2b.setText(but2);
            but3b.setText(but3);
            but4b.setText(but4);
            but5b.setText(but5);
            but6b.setText(but6);
            but1b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    choice=1;
                    multiChoiceFrag.sendAns();
                }
            });
            but2b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    choice=2;
                    multiChoiceFrag.sendAns();
                }
            });
            but3b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    choice=3;
                    multiChoiceFrag.sendAns();
                }
            });
            but4b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    choice=4;
                    multiChoiceFrag.sendAns();
                }
            });
            but5b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    choice=5;
                    multiChoiceFrag.sendAns();
                }
            });
            but6b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    choice=6;
                    multiChoiceFrag.sendAns();
                }
            });
        }
    };

    public static Handler getHandler(){
        return updateMulti;
    }

    private static void sendAns(){
        choiceFlag=true;
        /*
        Bundle bund = new Bundle();
        Message msg = new Message();
        bund.putInt("choice",multiChoiceFrag.choice);
        msg.setData(bund);
        choiceHandler.sendMessage(msg);*/
    }

    public void onPause(){
        choice=7;
        multiChoiceFrag.sendAns();
    }

}
