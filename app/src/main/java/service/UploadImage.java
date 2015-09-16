package service;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONObject;

import java.io.File;
import java.util.TimerTask;

import database.DatabaseHelper;
import helper.CommonHelper;
import httphelper.MultipartUtility;
import model.Photo;

/**
 * Created by Bit on 9/11/2015.
 */
public class UploadImage extends TimerTask {

	/**
	 * request url
	 */
	private static final String REQUEST_URL = "http://54.64.5.133/formville/index.php/api/uploadImage/";
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
	 * constructor
	 */

	public UploadImage( final Context context ) {
		super();
		this.context = context;

		commonHelper = new CommonHelper(context);
		isInternetPresent = false;
		isInternetPresent = commonHelper.isConnectingToInternet(context);
		dbHelper = new DatabaseHelper(context);

	}

	/**
	 * send data to server
	 */
	@Override
	public void run() {
		try {
			if ( commonHelper.isConnectingToInternet(context) ) {
				SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
				Cursor cursor = dbHelper.getOneImageAtATime(sqLiteDatabase);
				Log.e("send image", DatabaseUtils.dumpCursorToString(cursor));
				final MultipartUtility multipart = new MultipartUtility(REQUEST_URL, "UTF-8");
				int photoId = 0;
				if ( cursor.moveToFirst() ) {
					photoId = cursor.getInt(cursor.getColumnIndex("ID"));
					String imageName = cursor.getString(cursor.getColumnIndex("IMAGEPATH"));
					Log.e("image name", imageName);
					File imageFile = new File(imageName);
					multipart.addFilePart("image", imageFile);
				}
				JSONObject response = multipart.finish();

				if ( response != null ) {
					onSuccessResponse(response, sqLiteDatabase, photoId);
				}

			}
		} catch ( NullPointerException e ) {
			e.printStackTrace();
			Log.e("NullPointerException", " " + e);
		} catch ( Exception e ) {
			e.printStackTrace();
			Log.e("Exception", " " + e);
		}

	}

	/**
	 * success response
	 */

	public void onSuccessResponse( final JSONObject response, final SQLiteDatabase sqLiteDatabase, final int photoId ) {

		try {
			Log.e("response in image", response.toString());
			int status = Integer.parseInt(response.getString("status"));
			if ( status == 200 ) {
				//	dbHelper.deletePhoto(sqLiteDatabase, photoId);
				Photo photo = Photo.findById(Photo.class, photoId);
				photo.setIssync(0);
				photo.save();

				Log.e("image send sucessfuly", "yupp");
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