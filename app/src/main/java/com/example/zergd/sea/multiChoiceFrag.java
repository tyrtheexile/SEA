package com.example.zergd.sea;


import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

// TODO Add a Cancel Button
public class multiChoiceFrag extends Fragment {

    private static View view;
    private static Button but1b;
    private static Button but2b;
    private static Button but3b;
    private static Button but4b;
    private static Button but5b;
    private static Button but6b;

    public static int choice =10;
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
            Boolean butOff1 = bundle.getBoolean("butOff1");
            Boolean butOff2 = bundle.getBoolean("butOff2");
            Boolean butOff3 = bundle.getBoolean("butOff3");
            Boolean butOff4 = bundle.getBoolean("butOff4");
            Boolean butOff5 = bundle.getBoolean("butOff5");
            Boolean butOff6 = bundle.getBoolean("butOff6");
            but1b = (Button) view.findViewById(R.id.button1);
            but2b = (Button) view.findViewById(R.id.button2);
            but3b = (Button) view.findViewById(R.id.button3);
            but4b = (Button) view.findViewById(R.id.button4);
            but5b = (Button) view.findViewById(R.id.button5);
            but6b = (Button) view.findViewById(R.id.button6);
            but1b.setAlpha(.85f);
            but2b.setAlpha(.85f);
            but3b.setAlpha(.85f);
            but4b.setAlpha(.85f);
            but5b.setAlpha(.85f);
            but6b.setAlpha(.85f);
            but1b.setEnabled(true);
            but2b.setEnabled(true);
            but3b.setEnabled(true);
            but4b.setEnabled(true);
            but5b.setEnabled(true);
            but6b.setEnabled(true);
            if (!butOff2) {
                but2b.setEnabled(butOff2);
                but2b.setAlpha(.5f);
            }
            if (!butOff3) {
                but3b.setEnabled(butOff3);
                but3b.setAlpha(.5f);
            }
            if (!butOff4) {
                but4b.setEnabled(butOff4);
                but4b.setAlpha(.5f);
            }
            if (!butOff5) {
                but5b.setEnabled(butOff5);
                but5b.setAlpha(.5f);
            }
            if (!butOff6) {
                but6b.setEnabled(butOff6);
                but6b.setAlpha(.5f);
            }
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
        super.onPause();
        choice=7;
        multiChoiceFrag.sendAns();
    }

}
