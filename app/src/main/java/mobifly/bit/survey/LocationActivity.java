package mobifly.bit.survey;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;


public class LocationActivity extends FragmentActivity implements GoogleApiClient.ConnectionCallbacks,
		GoogleApiClient.OnConnectionFailedListener {
	/**
	 * create instance of variable
	 */
	private static final long INTERVAL = 1000 * 10;
	/**
	 * create instance of variable
	 */
	private static final long FASTEST_INTERVAL = 1000 * 5;
	/**
	 * create instance of variable
	 */
	transient public Context context;
	/**
	 * create instance of variable
	 */
	transient public LocationRequest mLocationRequest;
	/**
	 * create instance of variable
	 */
	transient public GoogleApiClient mGoogleApiClient;
	/**
	 * create instance of variable
	 */
	transient public android.location.Location mCurrentLocation;

	public transient boolean isGpsPresent;
	public transient boolean isInternetPresent;

	/**
	 * default constructor
	 */
	public LocationActivity() {
		super();

	}

	/**
	 * on create for UI
	 */
	protected void onCreate( final Bundle savedInstance ) {
		super.onCreate(savedInstance);
		context = getApplicationContext();
		//createLocationRequest();
		isInternetPresent = false;
		isGpsPresent = false;
		buildGoogleApiClient();

	}

	/**
	 * create location request
	 */
	protected void createLocationRequest() {
		mLocationRequest = new LocationRequest();
		mLocationRequest.setInterval(INTERVAL);
		mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
		mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
	}

	/**
	 * start
	 */
	@Override
	public void onStart() {
		super.onStart();
		mGoogleApiClient.connect();
	}

	/**
	 * build google api client
	 */
	protected synchronized void buildGoogleApiClient() {
		mLocationRequest = new LocationRequest();
		mLocationRequest.setInterval(INTERVAL);
		mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
		mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

		mGoogleApiClient = new GoogleApiClient.Builder(this)
				.addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this)
				.addApi(LocationServices.API)
				.build();
	}

	/**
	 * on connected google
	 */
	@Override
	public void onConnected( final Bundle bundle ) {
		mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
		try {
			currantLocation(mCurrentLocation);
		} catch ( NullPointerException error ) {
			Log.e("NullPointerException", "" + error);
		} catch ( Exception error ) {
			Log.e("Exception", "" + error);
		}
	}

	/**
	 * on connection suspend
	 */
	@Override
	public void onConnectionSuspended( final int iTemp ) {
// TODO Auto-generated method stub
	}

	/**
	 * current location
	 */
	public void currantLocation( final android.location.Location mLastLocation ) {
		// TODO Auto-generated method stub
	}

	/**
	 * connection failed
	 */
	@Override
	public void onConnectionFailed( final ConnectionResult connectionResult ) {
		// TODO Auto-generated method stub
	}

	/**
	 * on stop
	 */
	@Override
	protected void onStop() {
		super.onStop();
		if ( mGoogleApiClient.isConnected() ) {
			mGoogleApiClient.disconnect();
		}
	}

	/**
	 * show Gps setting dialog
	 */
	@SuppressLint ( "NewApi" )
	protected void showGpsSetting() {
		final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this, R.style.Base_Theme_AppCompat_Dialog);
		alertDialog.setCancelable(false);
		alertDialog.setTitle("GPS");
		alertDialog.setMessage("Please enable GPS to use application.");
		alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			/**
			 * dialog
			 */
			@Override
			public void onClick( final DialogInterface dialog, final int which ) {
				try {
					final Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					startActivity(intent);
				} catch ( Exception e ) {
					e.printStackTrace();

				}
			}
		});
		alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			/**
			 * dialog
			 */
			@Override
			public void onClick( final DialogInterface dialog, final int which ) {
				try {
					finish();
				} catch ( Exception e ) {
					e.printStackTrace();

				}
			}
		});
		alertDialog.show();
	}
}
