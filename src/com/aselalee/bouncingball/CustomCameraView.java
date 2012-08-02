package com.aselalee.bouncingball;

import java.io.IOException;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CustomCameraView extends SurfaceView {
	Camera camera;
	SurfaceHolder previewHolder;
	SurfaceHolder.Callback surfaceHolderListener = new SurfaceHolder.Callback() {
		public void surfaceCreated(SurfaceHolder holder) {
			try {
				camera = Camera.open(0);
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

	public CustomCameraView(Context context) {
		super(context);
		previewHolder = this.getHolder();
		previewHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		previewHolder.addCallback(surfaceHolderListener);
	}
	public CustomCameraView(Context context, AttributeSet attrs) {
		super(context, attrs);
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
}