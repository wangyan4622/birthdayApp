package com.example.myfirstapp;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PlayMusicActivity extends Activity {
	private MediaPlayer mp;
	private boolean isPaused;
	private Button stopButton;
	private Button pauseButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_music);

		mp = MediaPlayer.create(getBaseContext(), R.raw.copy);
		mp.start();

		stopButton = (Button) findViewById(R.id.button_stop);
		pauseButton = (Button) findViewById(R.id.button_pause);

		isPaused = false;

		stopButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mp.stop();
			}
		});
		pauseButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (isPaused) {
					isPaused=false;
					pauseButton.setText("Pause");
					mp.start();
				} else {
					isPaused=true;
					pauseButton.setText("Play");
					mp.pause();
				}
			}
		});
		mp.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				if (mp != null) {
					mp.release();
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play_music, menu);
		return true;
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
	    	if(mp!=null){
	    		mp.release();
	    	}
	    	this.finish();
	    	
	        Log.d(this.getClass().getName(), "back button pressed");
	    }
	    return super.onKeyDown(keyCode, event);
	}

}
