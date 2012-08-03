package com.aselalee.bouncingball;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;

public class ProximityTestActivity extends Activity implements SensorEventListener{
	private SensorManager mSensorManager;
	private Sensor mProximitySensor;
	private PowerManager mPowerManager;
	private WakeLock mProximityWakelock;
	private KeyguardManager mKeygaurdManager;
	private KeyguardLock mKeyGaurdLock;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_proximity_test);
		Log.i("ProximityTest", this.getClass().getName() + " - OnCreate");
		int PROXIMITY_SCREEN_OFF_WAKE_LOCK = 32;
		mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
		mProximitySensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mPowerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
		mProximityWakelock = mPowerManager.newWakeLock(PROXIMITY_SCREEN_OFF_WAKE_LOCK, "ProximityWakeLock");
		mKeygaurdManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
		mSensorManager.registerListener(this, mProximitySensor, SensorManager.SENSOR_DELAY_UI);
		mKeyGaurdLock = mKeygaurdManager.newKeyguardLock("KeyGaurdLock");
	}

	@Override
	public void onPause() {
		Log.i("ProximityTest", this.getClass().getName() + " - OnPause");
		super.onPause();
		
	}

	@Override
	public void onResume() {
		Log.i("ProximityTest", this.getClass().getName() + " - onResume");
		mKeyGaurdLock.reenableKeyguard();
		super.onResume();
	}

	@Override
	public void onDestroy() {
		Log.i("ProximityTest", this.getClass().getName() + " - OnDestroy");
		mSensorManager.unregisterListener(this);
		if(mProximityWakelock.isHeld())
			mProximityWakelock.release();
		super.onDestroy();
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		//Log.i("ProximityTest", this.getClass().getName() + " - onAccuracyChanged");
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		//Log.i("ProximityTest", this.getClass().getName() + " - onSensorChanged");
		if(mProximityWakelock == null || mKeyGaurdLock == null) return;
		if(event.values[0] < 4.0f) {
			//Lock
			if(!mProximityWakelock.isHeld()) {
				mProximityWakelock.acquire();
			}
		} else {
			//Release
			if(mProximityWakelock.isHeld()) {
				mProximityWakelock.release();
			}
			mKeyGaurdLock.disableKeyguard();
		}
	}
}
