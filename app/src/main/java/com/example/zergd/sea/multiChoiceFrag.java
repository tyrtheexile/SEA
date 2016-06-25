package com.example.zergd.sea;


import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class multiChoiceFrag extends Fragment {

    private static View view;
    private static Button but1b;
    private static Button but2b;
    private static Button but3b;
    private static Button but4b;
    private static Button but5b;
    private static Button but6b;
    private static Button cancel;

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
        cancel = (Button) view.findViewById(R.id.multiCancel);
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
            String butDesc1 = bundle.getString("butDesc1");
            String butDesc2 = bundle.getString("butDesc2");
            String butDesc3 = bundle.getString("butDesc3");
            String butDesc4 = bundle.getString("butDesc4");
            String butDesc5 = bundle.getString("butDesc5");
            String butDesc6 = bundle.getString("butDesc6");
            but1b = (Button) view.findViewById(R.id.button1);
            but2b = (Button) view.findViewById(R.id.button2);
            but3b = (Button) view.findViewById(R.id.button3);
            but4b = (Button) view.findViewById(R.id.button4);
            but5b = (Button) view.findViewById(R.id.button5);
            but6b = (Button) view.findViewById(R.id.button6);
            TextView but1Desc = (TextView) view.findViewById(R.id.desc1);
            TextView but2Desc = (TextView) view.findViewById(R.id.desc2);
            TextView but3Desc = (TextView) view.findViewById(R.id.desc3);
            TextView but4Desc = (TextView) view.findViewById(R.id.desc4);
            TextView but5Desc = (TextView) view.findViewById(R.id.desc5);
            TextView but6Desc = (TextView) view.findViewById(R.id.desc6);
            but1Desc.setText(butDesc1);
            but2Desc.setText(butDesc2);
            but3Desc.setText(butDesc3);
            but4Desc.setText(butDesc4);
            but5Desc.setText(butDesc5);
            but6Desc.setText(butDesc6);
            but1b.setAlpha(.85f);
            but2b.setAlpha(.85f);
            but3b.setAlpha(.85f);
            but4b.setAlpha(.85f);
            but5b.setAlpha(.85f);
            but6b.setAlpha(.85f);
            cancel.setAlpha(.85f);
            but1b.setEnabled(true);
            but2b.setEnabled(true);
            but3b.setEnabled(true);
            but4b.setEnabled(true);
            but5b.setEnabled(true);
            but6b.setEnabled(true);
            Global.log("but1: "+butOff1+"   but2:"+butOff2);
            if (butOff1==false) {
                but1b.setEnabled(false);
                but1b.setAlpha(.5f);
            }
            if (butOff2==false) {
                but2b.setEnabled(false);
                but2b.setAlpha(.5f);
            }
            if (butOff3==false) {
                but3b.setEnabled(false);
                but3b.setAlpha(.5f);
            }
            if (butOff4==false) {
                but4b.setEnabled(false);
                but4b.setAlpha(.5f);
            }
            if (butOff5==false) {
                but5b.setEnabled(false);
                but5b.setAlpha(.5f);
            }
            if (butOff6==false) {
                but6b.setEnabled(false);
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
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    choice=7;
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

    /*
    public void onPause(){
        super.onPause();
        choice=8;
        multiChoiceFrag.sendAns();
    }
    */
}
