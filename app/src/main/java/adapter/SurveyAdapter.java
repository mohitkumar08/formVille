package adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import helper.Constant;
import mobifly.bit.survey.MainActivity;
import mobifly.bit.survey.R;
import model.Survey;

/**
 * Created by Bit on 9/15/2015.
 */
public class SurveyAdapter extends RecyclerView.Adapter< SurveyAdapter.ContactViewHolder > {

	/**
	 * location list view
	 */
	transient public final List< Survey > surveyDataList;
	/**
	 * context of Audit Activity
	 */
	transient final private Context context;
	MainActivity mainActivity;

	public SurveyAdapter( final Context context, List< Survey > locationData, MainActivity main ) {
		super();
		this.surveyDataList = locationData;
		this.context = context;
		this.mainActivity = main;
	}

	@Override
	public SurveyAdapter.ContactViewHolder onCreateViewHolder( ViewGroup viewGroup, int i ) {
		final View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardviewlayout, viewGroup, false);
		return new ContactViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder( final SurveyAdapter.ContactViewHolder contactViewHolder, int location ) {

		try {
			final Survey obj = surveyDataList.get(location);
			contactViewHolder.date.setText(obj.getCreateddate());
			contactViewHolder.stage.setText(obj.getCompleteaddress());
			contactViewHolder.type.setText(", " + obj.getLocality());
			//contactViewHolder.category.setText(obj.getCategory());
			if ( obj.getOwnercontact() != null || obj.getOwnercontact() != Constant.EMPTY ) {
				contactViewHolder.ownerPhone.setText(obj.getOwnercontact());
			} else {
				contactViewHolder.ownerPhone.setText(obj.getBuildercontact());
			}
			contactViewHolder.ownerPhone.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick( View v ) {
					Intent i = new Intent(android.content.Intent.ACTION_CALL, Uri.parse("tel:+91" + contactViewHolder.ownerPhone.getText()));
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(i);
				}
			});
			contactViewHolder.image.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick( View v ) {
					if ( obj.getImagepath() != null || obj.getImagepath() != Constant.EMPTY ) {
						showImage(obj.getImagepath());
					} else {
						Toast.makeText(context, "Image is not in Phone", Toast.LENGTH_LONG).show();
					}
				}
			});
			contactViewHolder.map.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick( View v ) {

					/*String uri = String.format(Locale.ENGLISH, "geo:%s,%s", "28.451885299999997", "77.06013570000005");
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(intent);*/
					//latitude longitude
					//	String locationAddress ="geo:"+
					Uri gmmIntentUri = Uri.parse("geo:" + obj.getLatitude().toString() + "," + obj.getLongitude() + "?z=20m&data=!3m1!1e3");
					Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
					mapIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					mapIntent.setPackage("com.google.android.apps.maps");
					context.startActivity(mapIntent);
				}
			});

		/*	contactViewHolder.plotArea.setText("Plot Area = "+obj.getPlotarea());
			contactViewHolder.noOfFloor.setText("Floors = "+obj.getFloors());
	*/
		} catch ( NullPointerException error ) {
			error.printStackTrace();
		} catch ( Exception error ) {
			error.printStackTrace();
		}
	}

	public void showImage( String imagePath ) {
		final AlertDialog.Builder imageDialog = new AlertDialog.Builder(mainActivity);
		LayoutInflater inflater = ( LayoutInflater ) context.getSystemService(mainActivity.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.show_image_layout, ( ViewGroup ) mainActivity.findViewById(R.id.relativeLayout));
		final ImageView image = ( ImageView ) layout.findViewById(R.id.captureImage1);
		imageDialog.setView(layout);
		imageDialog.create();
		FileInputStream fileInputStream;
		BufferedInputStream bufferedStream;
		try {
			fileInputStream = new FileInputStream(imagePath);
			bufferedStream = new BufferedInputStream(fileInputStream);
			Bitmap bMap = BitmapFactory.decodeStream(bufferedStream);
			image.setImageBitmap(bMap);
			imageDialog.setPositiveButton("Ok",
					new DialogInterface.OnClickListener() {
						public void onClick( final DialogInterface dialog, final int which ) {
							dialog.dismiss();
						}
					});
			imageDialog.show();
		} catch ( FileNotFoundException error ) {
			Toast.makeText(context, "Image is not in Phone", Toast.LENGTH_LONG).show();
			error.printStackTrace();
		} catch ( Exception error ) {
			error.printStackTrace();
		}

	}


	@Override
	public int getItemCount() {
		return surveyDataList.size();
	}

	/**
	 * view holder
	 */
	public static class ContactViewHolder extends RecyclerView.ViewHolder {

		transient protected TextView date;
		transient protected TextView stage;
		transient protected TextView type;
		transient protected TextView category;
		transient protected TextView ownerPhone;
		transient protected ImageView image;
		transient protected ImageView map;
		/*transient protected TextView plotArea;
		transient protected TextView noOfFloor;
*/

		/**
		 * constructor
		 */
		public ContactViewHolder( final View view ) {
			super(view);
			date = ( TextView ) view.findViewById(R.id.textViewDate);
			stage = ( TextView ) view.findViewById(R.id.textViewStage);
			type = ( TextView ) view.findViewById(R.id.textViewType);

			category = ( TextView ) view.findViewById(R.id.textViewCategory);
			ownerPhone = ( TextView ) view.findViewById(R.id.textViewPhoneNo);
/*			plotArea = ( TextView ) view.findViewById(R.id.textViewPlotArea);
			noOfFloor = ( TextView ) view.findViewById(R.id.textViewNoOfFloor);
*/
			image = ( ImageView ) view.findViewById(R.id.imageViewPicture);
			map = ( ImageView ) view.findViewById(R.id.imageViewMap);
		}
	}
}
