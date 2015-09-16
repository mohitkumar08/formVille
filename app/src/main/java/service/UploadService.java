package service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;

/**
 * An {@link android.app.IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class UploadService extends Service {

	/**
	 * Timer instance
	 */
	private transient Timer timer;

	/**
	 * constructor
	 */
	public UploadService() {
		//super("UploadService");
		super();

	}

	/**
	 * create service
	 */
	@Override
	public void onCreate() {

		super.onCreate();
		try {

			timer = new Timer();
			final SendData serverIntegrate = new SendData(this);
			timer.scheduleAtFixedRate(serverIntegrate, 1, 10 * 6000);
			final UploadImage server = new UploadImage(this);
			timer.scheduleAtFixedRate(server, 1, 10 * 12000);

		} catch ( Exception error ) {
			Log.e("Exception", "" + error);
		}
	}

	/**
	 * onStartCommand for start service
	 */
	@Override
	public int onStartCommand( final Intent intent, final int flags, final int startId ) {

		return START_STICKY;
	}

	/**
	 *
	 */
	@Override
	public IBinder onBind( final Intent arg0 ) {
		return null;
	}

	/**
	 *
	 */
	@Override
	public void onDestroy() {
		timer.cancel();
		super.onDestroy();

	}

}
