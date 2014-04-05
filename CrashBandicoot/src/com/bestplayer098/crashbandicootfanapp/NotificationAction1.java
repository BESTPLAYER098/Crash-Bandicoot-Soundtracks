package com.bestplayer098.crashbandicootfanapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;

public class NotificationAction1 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String action1 = MainActivity.i1.getStringExtra("DO1");
	    
	    if(action1==("before")){
        	if(MainActivity.playMusic.isPlaying()==true){
        		MainActivity.playMusic.release();
        		MainActivity.newposition= MainActivity.position-1;
        		MainActivity.position=MainActivity.newposition;
				if (MainActivity.position==-1){
					MainActivity.position=84;
				}
				MainActivity.Updater();
				MainActivity.playMusic= MediaPlayer.create(MainActivity.context, MainActivity.uri);
				MainActivity.progressBar.setMax(MainActivity.playMusic.getDuration());
				MainActivity.playMusic.start();
			}
			else{
				MainActivity.playMusic.release();
				MainActivity.newposition= MainActivity.position-1;
				MainActivity.position=MainActivity.newposition;
				if (MainActivity.position==-1){
					MainActivity.position=84;
				}
				MainActivity.Updater();
				MainActivity.playMusic= MediaPlayer.create(MainActivity.context, MainActivity.uri);
				MainActivity.progressBar.setMax(MainActivity.playMusic.getDuration());
			}
        }
        
        
        finish();
	}
}