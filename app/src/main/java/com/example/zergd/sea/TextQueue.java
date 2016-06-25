package com.example.zergd.sea;

import java.util.ArrayList;
import java.util.Random;

public class TextQueue implements java.io.Serializable{

    private static ArrayList<String> messageQueue = new ArrayList<String>();
    private static ArrayList<String> statusQueue = new ArrayList<String>();



    public static void nextMessage(int turn){
        String str="\nLog Entry [";
        if (turn<3000)
            str=str+"A2-";
        else
            str=str+"Y7-";
        str=str+""+turn%100+"] : ";

        if (messageQueue.isEmpty()||messageQueue.get(0)==null)
        {
            str=str+getNoActivityMessage();
        }else {
            str = str + messageQueue.get(0);
            messageQueue.remove(0);
            str = str + "\n\n";
            while (statusQueue.isEmpty() == false) {
                str = str + "\n-" + statusQueue.get(0);
                statusQueue.remove(0);
            }
        }
        str = str + "\n------End Log-----";
        Global.sendTextBlock(str);
    }

    public static void putMessage(String str){
        putMessage(str,false);
    }
    public static void putMessage(String str,Boolean highpriority){
        if (highpriority==false) {
            messageQueue.add(str);
        }
        if (highpriority==true){
            messageQueue.add(0,str);
        }
    }
    public static void putMessage(String str,int i){
        messageQueue.add(i,str);
    }

    public static void putStatus(String str){
        statusQueue.add(str);
    }

    private static String getNoActivityMessage()
    {
        Random rand = new Random();
        String str="";
        int x = rand.nextInt(6);
        Global.log("X: ===== "+x);
        switch (x) {
            case 0: str = "The entire station creaks...";
                break;
            case 1: str = "The clicks and pops of the station hum ominously in the distance....";
                break;
            case 2: str = "There is a loud beeping noise somewhere far down the hall...";
                break;
            case 3: str = "You feel the gravity field temporarily shut down...Power must be fluctuating";
                break;
            case 4: str = "The hall door is still slightly open, the switch doesn't seem to work...";
                break;
            case 5: str = "The synthesizer's light is glowing blue...you have no idea what that means..";
                break;
            case 6: str = "Everything is peacefully quiet for now..";
                break;
        }
        return str;
    }

    public static void startGameMessages(){
        putMessage("Testing 1");
        putMessage("Testing 2");
        putMessage("It looks the Synthesizer is still functional, albeit barely. You can probably use that to make some basic supplies");
    }

}
