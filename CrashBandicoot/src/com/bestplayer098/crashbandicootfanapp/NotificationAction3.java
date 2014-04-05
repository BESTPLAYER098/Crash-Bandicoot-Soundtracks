package com.bestplayer098.crashbandicootfanapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;

public class NotificationAction3 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	    String action3 = MainActivity.i3.getStringExtra("DO3");
	    
	   
        if(action3==("next")){
        	if(MainActivity.playMusic.isPlaying()==true){
        		MainActivity.playMusic.release();
        		MainActivity.newposition=MainActivity.position+1;
        		MainActivity.position=MainActivity.newposition;
				if (MainActivity.position==85){
					MainActivity.position=0;
				}
				MainActivity.Updater();
				MainActivity.playMusic= MediaPlayer.create(MainActivity.context, MainActivity.uri);
				MainActivity.progressBar.setMax(MainActivity.playMusic.getDuration());
				MainActivity.playMusic.start();
			}
			else{
				MainActivity.playMusic.release();
				MainActivity.newposition=MainActivity.position+1;
				MainActivity.position=MainActivity.newposition;
				if (MainActivity.position==85){
					MainActivity.position=0;
				}
				MainActivity.Updater();
				MainActivity.playMusic= MediaPlayer.create(MainActivity.context, MainActivity.uri);
				MainActivity.progressBar.setMax(MainActivity.playMusic.getDuration());
				}
        }
       
        finish();
	}
}