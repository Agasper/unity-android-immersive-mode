package net.agasper.immersivemodeplugin;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.unity3d.player.UnityPlayerNativeActivity;

public class ImmersiveActivity extends UnityPlayerNativeActivity {

	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		Log.d("Immersive Mode Activity", "onCreate Called!");
	}

	@SuppressLint("NewApi")
	public void onResume() {
		super.onResume();
		GoImmersive();
		Log.d("Immersive Mode Activity", "onResume Called!");
	}

	private void GoImmersive() {
		if (Build.VERSION.SDK_INT >= 19) { // KITKAT
			getWindow().getDecorView().setSystemUiVisibility(
					View.SYSTEM_UI_FLAG_LAYOUT_STABLE
							| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
							| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
							| View.SYSTEM_UI_FLAG_FULLSCREEN
							| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
							| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		}
	}

	private Runnable resetImmersive = new Runnable() {

		@SuppressLint("NewApi")
		@Override
		public void run() {
			GoImmersive();
		}

	};

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus)
			GoImmersive();
	}

	private Handler mHandler = new Handler();

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (Build.VERSION.SDK_INT >= 19) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				finish();
			} else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN
					|| keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
				mHandler.postDelayed(resetImmersive, 500);
			}
		}
		return super.onKeyDown(keyCode, event);
	}
}
