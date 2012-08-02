package com.aselalee.bouncingball;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CustomCameraView extends SurfaceView {
	private Camera camera;
	private final int cameraId = 0;
	private SurfaceHolder previewHolder;
	private SurfaceHolder.Callback surfaceHolderListener = new SurfaceHolder.Callback() {
		public void surfaceCreated(SurfaceHolder holder) {
			try {
				camera = Camera.open(cameraId);
				camera.setPreviewDisplay(previewHolder);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int w, int h) {
			Parameters params = camera.getParameters();
			params.setPreviewSize( w, h);
			params.setPictureFormat(PixelFormat.JPEG);
			camera.setParameters(params);
			camera.startPreview();
		}
		public void surfaceDestroyed(SurfaceHolder arg0) {
			camera.stopPreview();
			camera.release();
		}
	};

	public CustomCameraView(Context mContext) {
		super(mContext);
		init();
	}
	public CustomCameraView(Context mContext, AttributeSet attrs) {
		super(mContext, attrs);
		init();
	}

	private void init() {
		previewHolder = this.getHolder();
		previewHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		previewHolder.addCallback(surfaceHolderListener);
	}

	protected void onDraw (Canvas canvas) {
		super.onDraw(canvas);
	}

	public void closeCamera() {
		if(camera != null)
			camera.release();
	}

	public void setCameraDisplayOrientation(Activity activity) {
	     android.hardware.Camera.CameraInfo info =
	             new android.hardware.Camera.CameraInfo();
	     android.hardware.Camera.getCameraInfo(cameraId, info);
	     int rotation = activity.getWindowManager().getDefaultDisplay()
	             .getRotation();
	     int degrees = 0;
	     switch (rotation) {
	         case Surface.ROTATION_0: degrees = 0; break;
	         case Surface.ROTATION_90: degrees = 90; break;
	         case Surface.ROTATION_180: degrees = 180; break;
	         case Surface.ROTATION_270: degrees = 270; break;
	     }

	     int result;
	     if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
	         result = (info.orientation + degrees) % 360;
	         result = (360 - result) % 360;  // compensate the mirror
	     } else {  // back-facing
	         result = (info.orientation - degrees + 360) % 360;
	     }
	     camera.stopPreview();
	     camera.setDisplayOrientation(result);
	     camera.startPreview();
	 }
}