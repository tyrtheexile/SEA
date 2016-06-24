package com.example.zergd.sea;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.*;
import android.view.View;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import android.os.*;

public class Global implements java.io.Serializable {
	
	//Debug Variable
	// Debug 0 - No Debug
	// Debug 1 - Text Mode, No Window, Output basic text
	// Debug 3 - Moderate Debug
	// Debug 5 - Verbose Debug
	// Debug 6 - Verbose Debug Text Mode
	private static int debug=0;
	private static int timeIncrement=1000;
	private static boolean immortal=false;
	private static int basesize=20;
    private static TextView outputView;
    private static ScrollView scrollView;
    private static Activity currentActivity;
    private static Boolean gameInProgress = false;
	public static ArrayList<String> outputStrings=new ArrayList<String>(){{add("OutputString1\n");}};

    public static Handler buttonNames;
    public static Handler butttonPress;
    public static Handler textUpdate;
    public static Handler updateProgressHandler;

    public static int choice=0;
    public static Boolean choiceFlag=false;
	
	//Testmode is used to implement different starting setupds
	//Testmode 1-default start
	//Testmode 2-Fabricator in inventory
	//Testmode 3-High Resource start
	private static int testmode=1;
	
	public static void set_Debug(int i) {debug=i;}
	public static int get_Debug() {return debug;}
	
	public static void DebugMSG(int lvl,String str)
	{
		if (debug>=lvl) System.out.println(str);
	}
	public static int getTimeIncrement() {
		return timeIncrement;
	}
	public static void setTimeIncrement(int i) {
		timeIncrement = i;
	}
	public static void TextDisp(String str)
	{
	    /*Runnable dispText = new Runnable() {
	        public void run() {
	    		TextView text = outputView;
	            text.append("\n"+str);
	            scrollView.fullScroll(View.FOCUS_DOWN);
	        }
	    };*/

        //Gen 2
		//Global.outputStrings.add(str);

        //Gen 3
        Handler hand = Global.getHandler("textDisp");
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("text",str);
        msg.setData(bundle);
        hand.sendMessage(msg);
	}
	
	public static void setImmortal(Boolean i) {immortal=i;}
	public static Boolean getImmortal() {return immortal;}
	public static int getBasesize() {
		return basesize;
	}
	public static void setBasesize(int basesize) {
		Global.basesize = basesize;
	}
	public static int getTestmode() {
		return testmode;
	}
	public static void setTestmode(int testmode) {
		Global.testmode = testmode;
	}
    public static void setView(TextView view,ScrollView scrollView)
    {
        Global.outputView = view;
        Global.scrollView = scrollView;
        view.setMovementMethod(new ScrollingMovementMethod());

    }
	public static TextView getTextView()
	{
		return outputView;
	}
    public static void setActivity(Activity act)
    {
        Global.currentActivity=act;
    }
    public static Activity getActivity()
    {return Global.currentActivity;}

    public static Boolean getGameInProgress()
    {return Global.gameInProgress;}

    public static void setGameInProgress(Boolean state)
    {
        Global.gameInProgress=state;
    }

    public static String getOutputBlock(){
        String outputBlock="";

        if (Global.outputStrings.isEmpty())
            return "Output is empty\n";
        while(Global.outputStrings.size()>1) {
            String str = Global.outputStrings.get(1);
            Global.outputStrings.remove(1);
            outputBlock=outputBlock+str+"\n";
        }
        return outputBlock;
    }

    public static void setHandler(Handler h1,Handler h2, Handler h3,Handler h4)
    {
        buttonNames = h1;
        butttonPress = h2;
        textUpdate = h3;
        updateProgressHandler =h4;
    }

    public static Handler getHandler(String name){
        if (name.equals("buttonNames"))
            return buttonNames;
        if (name.equals("buttonPress"))
            return butttonPress;
        if (name.equals("progress"))
            return updateProgressHandler;
        else
            return textUpdate;
    }

    public static int retrieveChoice(){
        if (choiceFlag==true)
        {
            choiceFlag=false;
            return choice;
        }
        return 0;
    }

    public static void log(String s)
    {
        Log.e("RTest: ", s);
    }
}
