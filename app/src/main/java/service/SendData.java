package service;


import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.TimerTask;

import database.DatabaseHelper;
import helper.CommonHelper;
import httphelper.CustomJsonObjectRequest;
import httphelper.CustomVollyRequestQueue;
import model.Survey;


public class SendData extends TimerTask implements Response.ErrorListener,
		Response.Listener< JSONObject > {
	/**
	 * login tag
	 */
	public static final String TAG = "Login";
	/**
	 * request url
	 */
	public static final String URL = "http://54.64.5.133/formville/index.php/api/survey/";
	/**
	 * declear variable
	 */
	public static int lastRecord;
	public static int currentRecordId;
	/**
	 * instance of context
	 */
	private transient final Context context;
	/**
	 * Database helper class instance
	 */
	private transient final DatabaseHelper dbHelper;
	/**
	 * common helper class instance
	 */
	public transient CommonHelper commonHelper;
	/**
	 * internet present check
	 */
	public transient boolean isInternetPresent;
	/**
	 * instance of request queue
	 */
	public Gson jsonData = new Gson();
	/**
	 * instance of request queue
	 */
	transient public RequestQueue queue;
	/**
	 * request instance
	 */
	transient public CustomJsonObjectRequest jsonRequest;
	public JSONObject data = new JSONObject();

	public SendData( final Context context ) {
		super();
		this.context = context;
		commonHelper = new CommonHelper(context);
		isInternetPresent = false;
		isInternetPresent = commonHelper.isConnectingToInternet(context);
		dbHelper = new DatabaseHelper(context);
	}

	@Override
	public void run() {
		try {
			if ( commonHelper.isConnectingToInternet(context) ) {
				currentRecordId = dbHelper.getLastData();
				Log.e("id hai", " " + currentRecordId);
				if ( currentRecordId > 0 ) {
					queue = CustomVollyRequestQueue.getInstance(context).getRequestQueue();
					Survey survey = Survey.findById(Survey.class, currentRecordId);
					Gson jo = new Gson();
					Log.e("ddd", jo.toJson(survey));
					JSONObject senData = new JSONObject();
					JSONObject surveyData = new JSONObject();

					try {
						surveyData.put("architectContact", survey.getArchitectcontact());
						surveyData.put("architectName", survey.getArchitectname());
						surveyData.put("bathRoom", survey.getBathroom());
						surveyData.put("builderName", survey.getBuildername());
						surveyData.put("builderContact", survey.getBuildercontact());
						surveyData.put("category", survey.getCategory());
						surveyData.put("city", survey.getCity());
						surveyData.put("completeAddress", survey.getCompleteaddress());
						surveyData.put("createdDate", survey.getCreateddate());
						surveyData.put("detailConstruction", survey.getDetailconstruction());
						surveyData.put("emailId", survey.getEmailid());
						surveyData.put("floors", survey.getFloors());
						surveyData.put("image", survey.getImage());
						surveyData.put("imagePath", survey.getImagepath());
						surveyData.put("isSync", survey.getIssync());
						surveyData.put("kitchen", survey.getKitchen());
						surveyData.put("latitude", survey.getLatitude());
						surveyData.put("longitude", survey.getLongitude());
						surveyData.put("locality", survey.getLocality());
						surveyData.put("ownerName", survey.getOwnername());
						surveyData.put("ownerContact", survey.getOwnercontact());
						surveyData.put("plotArea", survey.getPlotarea());
						surveyData.put("plotNo", survey.getPlotno());
						surveyData.put("rooms", survey.getRooms());
						surveyData.put("slabs", survey.getSlabs());
						surveyData.put("stageOfConstruction", survey.getStageofconstruction());
						surveyData.put("stayType", survey.getStaytype());
						surveyData.put("surveyOrName", survey.getSurveyorname());
						surveyData.put("typeOfConstruction", survey.getTypeofconstruction());
						surveyData.put("whenConstruction", survey.getWhenconstruction());
						surveyData.put("remarks", survey.getRemarks());
					} catch ( JSONException e1 ) {
						e1.printStackTrace();
					}
					senData.put("survey", surveyData);
					data.put("data", senData);
					Log.e("send data to server", data.toString());
					jsonRequest = new CustomJsonObjectRequest(Request.Method.POST, URL,
							data, this, this);

					jsonRequest.setTag(TAG);
					jsonRequest.setPriority(Request.Priority.IMMEDIATE);

					// setRetryPolicy
					RetryPolicy policy = new DefaultRetryPolicy(5000, 3, 1.0f);
					jsonRequest.setRetryPolicy(policy);
					queue.add(jsonRequest);
				}
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}

	@Override
	public void onErrorResponse( VolleyError volleyError ) {
		Log.e("error", volleyError.getMessage());

		if ( volleyError.toString().equals("com.android.volley.TimeoutError") ) {
			Log.e("timeout error", "timeout");
		}
		if ( volleyError.toString().equals("com.android.volley.NoConnectionError") ) {
			Log.e("No Connection", "No Connection");
		}
	}

	@Override
	public void onResponse( JSONObject response ) {
		Log.e("response from send Audit data server", response.toString());
		try {
			int status = response.getInt("status");
			Log.e("status   " + currentRecordId, " " + status);

			if ( status == 200 ) {
				Log.e("true  ", "true");
				Survey survey = Survey.findById(Survey.class, currentRecordId);
				survey.setIssync("0");
				survey.save();

				//dbHelper.deleteSendData(currentRecordId);
			}
		} catch ( NullPointerException e ) {
			e.printStackTrace();
			Log.e("NullPointerException", "" + e);
		} catch ( Exception e ) {
			e.printStackTrace();
			Log.e("Exception", "" + e);
		}

	}
}
