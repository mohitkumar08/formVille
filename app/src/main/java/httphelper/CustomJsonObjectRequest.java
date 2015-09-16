package httphelper;


import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * custom volley request
 */
public class CustomJsonObjectRequest extends JsonObjectRequest {
	/**
	 * priority instance
	 */
	transient public Priority mPriority;
	//private Map< String, String > headers = new HashMap<>();

	/**
	 * constructor
	 */
	public CustomJsonObjectRequest( final int method, final String url,
									final JSONObject jsonRequest, final Response.Listener< JSONObject > listener,
									final Response.ErrorListener errorListener ) {
		super(method, url, jsonRequest, listener, errorListener);
		// TODO Auto-generated constructor stub
	}

	/*public void setCookies( List< String > cookies ) {
		StringBuilder sb = new StringBuilder();
		for ( String cookie : cookies ) {
			sb.append(cookie).append("; ");
		}
		headers.put("Cookie", sb.toString());
	}*/

	/**
	 * get priority
	 */
	@Override
	public Priority getPriority() {
		return mPriority == null ? Priority.NORMAL : mPriority;
	}

	/**
	 * set priority
	 */
	public void setPriority( final Priority priority ) {
		mPriority = priority;
	}

	/**
	 * set header
	 */
	@Override
	public Map< String, String > getHeaders() throws AuthFailureError {
		final HashMap< String, String > headers = new HashMap<>();
		headers.put("Content-Type", "application/json; charset=utf-8");
		headers.put("Content-Type", "text/plain");
		return headers;
	}
}
