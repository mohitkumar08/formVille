package httphelper;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

/**
 * volley  custom request queue
 */
public class CustomVollyRequestQueue {
	/**
	 * instance of CustomVollyRequestQueue
	 */
	public static CustomVollyRequestQueue mInstance;
	/**
	 * context instance
	 */
	transient private static Context mCtx;
	/**
	 * RequestQueue instance
	 */
	transient private RequestQueue mRequestQueue;

	/**
	 * constructor
	 */
	public CustomVollyRequestQueue( final Context context ) {
		super();
		this.mCtx = context;
		//this.mRequestQueue = getRequestQueue();
	}

	/**
	 * singleton instance
	 */
	public static synchronized CustomVollyRequestQueue getInstance( final Context context ) {
		if ( mInstance == null ) {
			mInstance = new CustomVollyRequestQueue(context);
		}
		return mInstance;
	}

	/**
	 * get request queue
	 */
	public RequestQueue getRequestQueue() {
		if ( mRequestQueue == null ) {
			final Cache cache = new DiskBasedCache(mCtx.getCacheDir(), 10 * 1024 * 1024);
			final Network network = new BasicNetwork(new HurlStack());
			mRequestQueue = new RequestQueue(cache, network);
			// Don't forget to start the volley request queue
			mRequestQueue.start();
		}
		return mRequestQueue;
	}
}
/**
 *
 */