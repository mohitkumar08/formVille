package helper;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * Created by MOHIT KUMAR on 7/13/2015.
 */
public class CommonHelper {
	/**
	 * context object reference object
	 */
	transient public Context context;

	/**
	 * context constructor
	 */
	public CommonHelper( final Context context ) {
		super();
		this.context = context;
	}

	/**
	 * check gps is present or not
	 * return true if gps present
	 */
	public boolean isGpsOnOrOff( final Context context ) {
		final LocationManager manager = ( LocationManager ) context.getSystemService(Context.LOCATION_SERVICE);
		return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}


	/**
	 * check internet connectivity
	 *
	 * @return true if connect the internet otherwise false
	 */
	public boolean isConnectingToInternet( final Context context ) {
		ConnectivityManager connectivity = ( ConnectivityManager ) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		Boolean flag = false;
		if ( connectivity != null ) {
			/**
			 * connectivity not null
			 */
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if ( info == null ) {
				flag = false;
			} else {
				flag = checkNetworkDevice(info);
			}
		}
		return flag;
	}

	/**
	 * check all network devices
	 */
	public Boolean checkNetworkDevice( final NetworkInfo[] info ) {
		Boolean flag = false;

		for ( int i = 0; i < info.length; i++ ) {
			if ( info[i].getState() == NetworkInfo.State.CONNECTED ) {
				flag = true;
			}
		}
		return flag;

	}

	/**
	 * show gps setting
	 */
	/*public void showGpsSetting( final Context context_parent ) {
		final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context_parent, R.style.alert_dialog);
		alertDialog.setCancelable(false);
		alertDialog.setTitle("Location Not Enabled");
		alertDialog.setMessage("Proctor requires location services .Enable location services such as GPS now?");
		alertDialog.setPositiveButton("Go to Settings", new DialogInterface.OnClickListener() {
			*/

	/**
	 * dialog
	 *//*
			@Override
			public void onClick( final DialogInterface dialog, final int which ) {
				try {
					final Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					context_parent.startActivity(intent);
				} catch ( NullPointerException error ) {
					Log.e("NullPointerException", "" + error);
				} catch ( Exception error ) {
					Log.e("Exception", "" + error);
				}
			}
		});
		alertDialog.show();
	}*/
	public boolean isGpsPresent( final Context context ) {
		final LocationManager manager = ( LocationManager ) context.getSystemService(Context.LOCATION_SERVICE);
		final boolean statusOfGPS = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		return statusOfGPS;
	}


}
