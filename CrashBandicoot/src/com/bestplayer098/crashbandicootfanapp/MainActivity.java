package com.bestplayer098.crashbandicootfanapp;

import java.io.IOException;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity implements MediaPlayer.OnCompletionListener {
	
	
	static NotificationManager nm;
	NotificationCompat.Builder builder; 
	static final int uniqueID = 153135;
	
	
	static boolean isInFront= true;
    private Handler mHandler = new Handler();
   
    static int putposition;
    int setprogress;
    
	ImageButton playIBtn;
	ImageButton stopIBtn;
	ImageButton nextIBtn;
	ImageButton beforeIBtn;
	ImageButton loopIBtn;
	static SeekBar progressBar;
	static TextView tvName;
	
	static String track[];
	
	static Intent i1;
	static Intent i2;
	static Intent i3;
	
	static Uri uri;
	static int newposition;
	static int position;
	
	static MediaPlayer playMusic;
	static Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		//playMusic.setAudioStreamType(AudioManager.STREAM_MUSIC);
		MainActivity.context = this;
		
		playIBtn = (ImageButton) findViewById(R.id.playImageButton);
        stopIBtn = (ImageButton) findViewById(R.id.stopImageButton);
        nextIBtn = (ImageButton) findViewById(R.id.nextImageButton);
        beforeIBtn = (ImageButton) findViewById(R.id.beforeImageButton);
        loopIBtn = (ImageButton) findViewById(R.id.loopImageButton);
        progressBar = (SeekBar) findViewById(R.id.progressSeekBar);
        tvName = (TextView) findViewById(R.id.tvName);
        
        position=getIntent().getExtras().getInt("position1");
    	
        
        Updater();
        
        playMusic=MediaPlayer.create(this, uri);
        playMusic.setLooping(false);
        progressBar.setMax(playMusic.getDuration());
        playMusic.setOnCompletionListener(this);
        playMusic.start();
      	seekUpdation();
      	notificationcreate();
      	
	}
	 public static Context getContext(){
	        return context;
	    }
	
	 @Override
	 protected void onNewIntent(Intent intent) {
	     super.onNewIntent(intent);
	     setIntent(intent);
	 }
	 
	@SuppressWarnings("deprecation")
	private void notificationcreate() {
		// TODO Auto-generated method stub
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
      	
      	builder = (NotificationCompat.Builder)
      	        new NotificationCompat.Builder(getApplicationContext());

      	i1 = new Intent(getApplicationContext(), NotificationAction1.class).putExtra("DO1", "before").addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
	  	PendingIntent pIntent1 = 
	  	        PendingIntent.getActivity(getApplicationContext(), 1, i1, 0);
	  	
	  	i2 = new Intent(getApplicationContext(), NotificationAction2.class).putExtra("DO2", "play").addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
	  	PendingIntent pIntent2 = 
	  	        PendingIntent.getActivity(getApplicationContext(), 2, i2, 0);
	  	
	  	i3 = new Intent(getApplicationContext(), NotificationAction3.class).putExtra("DO3", "next").addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
	  	PendingIntent pIntent3 = 
	  	        PendingIntent.getActivity(getApplicationContext(), 3, i3, 0);
	  	
	  	
	  	String title= getString(R.string.app_name);
	  	builder
	  	    .setAutoCancel(true)
	  	    .setSmallIcon(R.drawable.ic_launcher)
	  	    .setContentTitle(title)
	  	    .setContentText(track[putposition]);
	  	builder
	  		.addAction(R.drawable.ic_back, "", pIntent1)
	  		.addAction(R.drawable.ic_pause, "", pIntent2)
	  		.addAction(R.drawable.ic_next, "", pIntent3);
	  	Notification n = builder.getNotification();
	  	n.flags = Notification.FLAG_NO_CLEAR;
      	nm.notify(uniqueID, n);
      	
	}


	Runnable run = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			seekUpdation();
			
			}
		};
		
		public void seekUpdation() {
			// TODO Auto-generated method stub
			if (isInFront==true){
			 progressBar.setProgress(playMusic.getCurrentPosition());
			}
		        mHandler.postDelayed(run, 100);
			
		}	

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		
		
	playIBtn.setOnClickListener(new View.OnClickListener() {
		
		public void onClick(View v) {
			if (playMusic.isPlaying()==true){
				playMusic.pause();
			}
			else{
				playMusic.start();
			}
		}
		
	});
	
	stopIBtn.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			playMusic.start();
			playMusic.pause();
			playMusic.seekTo(0);
		}
	});
    
    nextIBtn.setOnClickListener(new View.OnClickListener() {

    	@Override
    	public void onClick(View v) {
    		// TODO Auto-generated method stub
	
			if(playMusic.isPlaying()==true){
				playMusic.release();
				newposition=position+1;
				position=newposition;
				if (position==85){
					position=0;
				}
				Updater();
				playMusic= MediaPlayer.create(MainActivity.this, uri);
				progressBar.setMax(playMusic.getDuration());
				playMusic.start();
			}
			else{
				playMusic.release();
				newposition=position+1;
				position=newposition;
				if (position==85){
					position=0;
				}
				Updater();
				playMusic= MediaPlayer.create(MainActivity.this, uri);
				progressBar.setMax(playMusic.getDuration());
				}
			notificationcreate();
		}
    });
    
    beforeIBtn.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(playMusic.isPlaying()==true){
				playMusic.release();
				newposition= position-1;
				position=newposition;
				if (position==-1){
					position=84;
				}
				Updater();
				playMusic= MediaPlayer.create(MainActivity.this, uri);
				progressBar.setMax(playMusic.getDuration());
				playMusic.start();
			}
			else{
				playMusic.release();
				newposition= position-1;
				position=newposition;
				if (position==-1){
					position=84;
				}
				Updater();
				playMusic= MediaPlayer.create(MainActivity.this, uri);
				progressBar.setMax(playMusic.getDuration());
			}
			notificationcreate();
		}
	});
    
    loopIBtn.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (playMusic.isLooping()==false){
			playMusic.setLooping(true);
			}
			else{playMusic.setLooping(false);
			}
		}
	});
}

	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		
        
	}
	
	public static void Updater() {
		// TODO Auto-generated method stub
		
		uri= Uri.parse("android.resource://com.bestplayer098.crashbandicootfanapp/raw/track"+position);
		
		if(position>52){
			track = MainActivity.getContext().getResources().getStringArray(R.array.Crash_Bandicoot_3);
    		putposition=position-53;
    		tvName.setText(String.valueOf(track[putposition]));
		}
    	else if(position>26){
    		track = MainActivity.getContext().getResources().getStringArray(R.array.Crash_Bandicoot_2);
    		putposition=position-27;
    		tvName.setText(String.valueOf(track[putposition]));
    		
		}
        else{
        	track =MainActivity.getContext().getResources().getStringArray(R.array.Crash_Bandicoot_1);
        	putposition=position;
        	tvName.setText(String.valueOf(track[putposition]));	
        }
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		
		if (playMusic.isLooping()==true){
			progressBar.setProgress(0);
			try {
				playMusic.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			playMusic.start();
		}else{
			progressBar.setProgress(0);
		}
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		isInFront=false;
		notificationcreate();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		isInFront=true;
		if(isInFront==true){
	        progressBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

				@Override
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
					// TODO Auto-generated method stub
					 if (fromUser) {
						 setprogress=progress;
			         }
				}

				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					isInFront=false;
				}

				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					 if (playMusic.isPlaying()==true){
						 playMusic.pause();
						 playMusic.seekTo(setprogress);
						 playMusic.start();
						
					 }else{
						 playMusic.seekTo(setprogress);
					 }
					 isInFront=true;
				}
	        });
		}
	}
}