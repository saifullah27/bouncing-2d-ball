package com.aselalee.bouncingball;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void openCameraActivity(View view) {
		Intent cameraInent = new Intent(getBaseContext(), CameraPreviewActivity.class);
		cameraInent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		cameraInent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
		getApplication().startActivity(cameraInent);
		return;
	}

	public void openBouncingBallActivity(View view) {
		Intent ballIntent = new Intent(getBaseContext(), BouncingBallActivity.class);
		ballIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		ballIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
		getApplication().startActivity(ballIntent);
		return;
	}
}
