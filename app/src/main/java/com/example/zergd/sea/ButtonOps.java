package com.example.zergd.sea;

import android.view.View;
import android.widget.Button;
import android.app.Activity;

public class ButtonOps {

    private Boolean buttonClicked=false;
    private Boolean buttonUpdated=false;
    private int choiceNum=3;

    private String button1Name="Choice1";
    private String button2Name="Choice2";

    Button button1;
    Button button2;

    private Activity workingView;

    public ButtonOps(Activity a1)
    {
        workingView=a1;
        button1 = (Button) workingView.findViewById(R.id.CH1);
        button2 = (Button) workingView.findViewById(R.id.CH2);
    }

    private Boolean choiceMade(){
        if (buttonUpdated==true && buttonClicked==true)
        {
            buttonUpdated=false;
            buttonClicked=false;
            return true;
        }
        return false;
    }

    public int getChoice(){
        if (choiceMade())
            return choiceNum;
        return 0;
    }

    public void updateButtons(String s1,String s2)
    {
        button1Name=s1;
        button2Name=s2;
        button1.setText(button1Name);
        button2.setText(button2Name);
        buttonUpdated=true;
        buttonClicked=false;
    }

    public void setOnClickListners()
    {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClicked=true;
                if (choiceMade())
                {
                    Global.choice=1;
                    Global.choiceFlag=true;
                }
                buttonClicked=false;
                Global.log("Button1 Clicked");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClicked=true;
                if (choiceMade())
                {
                    Global.choice=2;
                    Global.choiceFlag=true;
                }
                buttonClicked=false;
            }
        });
    }
}
