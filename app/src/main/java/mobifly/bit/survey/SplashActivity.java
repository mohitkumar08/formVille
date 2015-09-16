package mobifly.bit.survey;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import database.DatabaseHelper;
import helper.CommonHelper;
import service.UploadService;


public class SplashActivity extends Activity {
	transient private final static int TIME_SLOT = 4000;
	DatabaseHelper databaseHelper;
	CommonHelper helper;
	Context ctx;
	transient private int wait;

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate(savedInstanceState);
		try {
			setContentView(R.layout.activity_splash);
			helper = new CommonHelper(SplashActivity.this);
			ctx = SplashActivity.this;

			databaseHelper = new DatabaseHelper(SplashActivity.this);

			final Thread welcomeThread = new Thread() {
				@Override
				public void run() {
					try {
						while ( wait < TIME_SLOT ) {
							sleep(500);
							wait += 500;
						}

					} catch ( Exception error ) {
						error.printStackTrace();

					} finally {


						/*if ( helper.isGpsOnOrOff(getApplicationContext()) ) {
							GPSOnAndOff();
						}*/
						startService(new Intent(SplashActivity.this, UploadService.class));
						startActivity(new Intent(SplashActivity.this, MainActivity.class));
						finish();
					}
				}
			};
			welcomeThread.start();
		} catch ( Exception error ) {
			error.printStackTrace();
		}
	}


	public void turnGPSOn() {
		Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
		intent.putExtra("enabled", true);
		//this.ctx.sendBroadcast(intent);
		this.ctx.sendBroadcast(intent);

		String provider = Settings.Secure.getString(ctx.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
		if ( ! provider.contains("gps") ) { //if gps is disabled
			final Intent poke = new Intent();
			poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
			poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
			poke.setData(Uri.parse("3"));
			this.ctx.sendBroadcast(poke);


		}
	}/*
	private void turnGPSOn() {
		String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_MODE);

		if ( ! provider.contains("gps") ) { //if gps is disabled
			final Intent poke = new Intent();
			poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
			poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
			poke.setData(Uri.parse("3"));
			sendBroadcast(poke);
		}
	}
*/

	private void turnGPSOff() {
		String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

		if ( provider.contains("gps") ) { //if gps is enabled
			final Intent poke = new Intent();
			poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
			poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
			poke.setData(Uri.parse("3"));
			sendBroadcast(poke);
		}
	}


	public void GPSOnAndOff() {

		Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
		intent.putExtra("enabled", true);
		sendBroadcast(intent);

	}


}
