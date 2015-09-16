package database;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import mobifly.bit.survey.R;

/**
 * Created by Bit on 9/10/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

	/**
	 * DatabaseHelper class instance
	 */
	transient public DatabaseHelper helper;
	/**
	 * system resource
	 */
	transient public Resources resources;
	/**
	 * class context
	 */
	transient public Context ctx;

	/**
	 * constructor
	 */
	public DatabaseHelper( final Context context, final String name, final SQLiteDatabase.CursorFactory factory,
						   final int version ) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	/**
	 * constructor
	 */
	public DatabaseHelper( final Context context ) {

		this(context, context.getResources().getString(R.string.database_name), null, context.getResources().getInteger(R.integer.database_version));
		ctx = context;
		resources = ctx.getResources();
	}

	/**
	 * create tables
	 */
	@Override
	public void onCreate( final SQLiteDatabase database ) {
		// TODO Auto-generated method stub

		try {
			String[] queryArray = resources.getStringArray(R.array.create_app_tables);
			for ( int i = 0; i < queryArray.length; i++ ) {
				database.execSQL(queryArray[i]);
				Log.e("tables created ", queryArray[i]);
			}
		} catch ( NullPointerException error ) {
			error.printStackTrace();

		} catch ( Exception error ) {
			error.printStackTrace();
		}
		Log.e("Create ", "create ");
	}

	/**
	 * upgrade database
	 */
	@Override
	public void onUpgrade( final SQLiteDatabase database, final int oldVersion, final int newVersion ) {
		// TODO Auto-generated method stub

	}

	public int getLastData() {
		SQLiteDatabase database = this.getReadableDatabase();
		Cursor cursor = null;
		int returnValueId = 0;
		try {
			int val = 1;
			final String query = "SELECT id FROM SURVEY WHERE  issync='" + val + "' ORDER BY id ASC LIMIT 1";
			cursor = database.rawQuery(query, null);
			if ( cursor.moveToFirst() ) {
				returnValueId = cursor.getInt(0);
			}
		} catch ( NullPointerException error ) {
			Log.e("NullPointer", "" + error);
		} catch ( Exception error ) {
			Log.e("Exception", "" + error);
		}


		return returnValueId;
	}

	public void deleteSendData( final int inputId ) {
		SQLiteDatabase database = this.getWritableDatabase();
		int count = 0;
		count = database.delete("SURVEY", "ID=" + inputId, null);
		Log.e("deleted insoection", String.valueOf(count));

	}

	public Cursor getOneImageAtATime( final SQLiteDatabase database ) {
		Cursor cursor = null;
		int isSync = 1;
		try {
			String query = "SELECT * FROM PHOTO WHERE  ISSYNC='" + isSync + "' ORDER BY id ASC LIMIT 1";
			cursor = database.rawQuery(query, null);
		} catch ( NullPointerException error ) {
			Log.e("NullPointer", " " + error);
		} catch ( Exception error ) {
			Log.e("Exception", " " + error);
		}

		return cursor;
	}

	/**
	 * delete photo table
	 */
	public void deletePhoto( final SQLiteDatabase database, final int inputId ) {
		database.delete("PHOTO", "id=" + inputId, null);

	}
}
