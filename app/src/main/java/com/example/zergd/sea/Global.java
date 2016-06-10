package com.example.zergd.sea;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.*;
import android.view.View;
import android.support.v7.app.AppCompatActivity;

public class Global {
	
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
        TextView text = outputView;
        text.append("\n"+str);
        scrollView.fullScroll(View.FOCUS_DOWN);
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
}
