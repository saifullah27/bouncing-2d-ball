package com.aselalee.bouncingball;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

public class CameraPreviewActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		((CustomCameraView)findViewById(R.id.previewSurface)).setCameraDisplayOrientation(this);
	}
}
