package com.bestplayer098.crashbandicootfanapp;

import android.os.Bundle;
import android.app.Activity;

public class NotificationAction2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	    String action2 = MainActivity.i2.getStringExtra("DO2");
	   
        if(action2==("play")){
    			if (MainActivity.playMusic.isPlaying()==true){
    				MainActivity.playMusic.pause();
    			}
    			else{
    				MainActivity.playMusic.start();
    			}
        }
        
        
        finish();
	}
}