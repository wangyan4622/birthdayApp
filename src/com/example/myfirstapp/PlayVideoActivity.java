package com.example.myfirstapp;

import java.io.File;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;

public class PlayVideoActivity extends Activity {
	
	private VideoView mVideoView;
	private MediaController mMediaController;
	private int mPositionWhenPaused = -1;
	public static final String TAG = "VideoPlayer";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_video);
		
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		mVideoView=(VideoView)findViewById(R.id.videoview);
		
		mMediaController=new MediaController(this);
		mVideoView.setMediaController(mMediaController);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play_video, menu);
		return true;
	}
	
	public void onCompletion(MediaPlayer mp){
		this.finish();
	}
	@Override
	public void onStart(){
		mVideoView.setVideoPath(getSdPath()+"/DCIM/Camera/video.mp4");
		mVideoView.start();
		
		super.onStart();
	}
//
//	public void onPause(){
//		mPositionWhenPaused = mVideoView.getCurrentPosition();
//		mVideoView.stopPlayback();
//		Log.d(TAG, "OnStop: mPositionWhenPaused = "+mPositionWhenPaused);
//		Log.d(TAG, "OnStop: getDuration = "+mVideoView.getDuration());
//		
//		super.onPause();
//		
//	}
//	
//	public void onResume(){
//		if(mPositionWhenPaused>=0){
//			mVideoView.seekTo(mPositionWhenPaused);
//			mPositionWhenPaused = -1;
//		}
//		super.onResume();
//	}
//	
	public String getSdPath(){
		File sdDir = null;
		boolean isSdExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
		if(isSdExist){
			sdDir = Environment.getExternalStorageDirectory();
			
		}
		return sdDir.toString();
	}

}
