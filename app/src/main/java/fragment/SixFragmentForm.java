package fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Select;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import fr.ganfra.materialspinner.MaterialSpinner;
import helper.Constant;
import info.hoang8f.widget.FButton;
import mobifly.bit.survey.R;
import model.Photo;
import model.Survey;

public class SixFragmentForm extends Fragment implements Validator.ValidationListener {

	public static final String imageName = getCurrentDateTime() + ".jpg";
	static final int REQUEST_IMAGE_CAPTURE = 1;
	public static SixFragmentForm fragment = new SixFragmentForm();
	public static Uri imageUri;
	public static int currentSdk;
	public final String root = setFolderLocation();
	public Survey surveyData = Survey.newInstance();
	public ArrayAdapter< String > snAdapter;
	Boolean isValid = false;
	Validator validator;
	@Bind (R.id.buttonSubmitData)
	FButton submitData;
	@Bind (R.id.imageViewCaptureImage)
	ImageView imageView;
	@Select (message = "Please select a choice")
	@Bind (R.id.spinnerSurveyor)
	MaterialSpinner spinnerSurveyor;


	@Bind (R.id.editTextRemarks)
	EditText remarks;

	//

	OnBackPressed backPressed;

	public SixFragmentForm() {
		// Required empty public constructor
	}

	public static SixFragmentForm newInstance() {

		if ( fragment == null ) {
			fragment = new SixFragmentForm();
		}
		return fragment;
	}

	public static SixFragmentForm deleteInstance() {
		fragment = null;
		return fragment;
	}

	public static String getCurrentDateTime() {
		return new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(new Date());
	}

	@Override
	public void onCreate( Bundle savedInstanceState ) {
		validator = new Validator(this);
		validator.setValidationListener(this);
		currentSdk = Integer.parseInt(Build.VERSION.SDK);
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container,
							  Bundle savedInstanceState ) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_six_fragment_form, container, false);
		ButterKnife.bind(this, view);
		setAdapter();
		return view;
	}

	private void setAdapter() {
		snAdapter = new ArrayAdapter< String >(getActivity(), android.R.layout.simple_spinner_item, Constant.SURVEYOR_NAME);
		snAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerSurveyor.setAdapter(snAdapter);
		spinnerSurveyor.setPaddingSafe(0, 0, 0, 0);


	}

	@Override
	public void onAttach( Activity activity ) {

		backPressed = ( OnBackPressed ) getActivity();
		super.onAttach(activity);
	}

	@Override
	public void onDetach() {
		super.onDetach();

	}

	@OnItemSelected (value = R.id.spinnerSurveyor)
	void onSelectDetailCons( int position ) {
		if ( position >= 0 ) {
			surveyData.setSurveyorname(snAdapter.getItem(position));
		}
	}

	@OnClick (R.id.imageViewCaptureImage)
	public void captureImage() {

		imageUri = Uri.fromFile(new File(root, imageName));
		Log.e("in the less 19", "   " + currentSdk);
		if ( currentSdk < 19 ) {
			final Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			cameraIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageUri);
			startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);

		} else {
			Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

		}


	}

	@OnClick (R.id.buttonSubmitData)
	public void submitData() {
		validator.validate();

	}

	private void saveDataIntoDatabase() {
		surveyData.setRemarks(remarks.getText().toString());
		surveyData.setIssync("1");
		surveyData.save();
		Photo userPhoto = new Photo();
		userPhoto.setImagename(surveyData.getImage());
		userPhoto.setImagepath(imageUri.getPath());
		userPhoto.setSurveyid("1");
		userPhoto.setIssync(1);
		userPhoto.save();
		backPressed.back();
	}

	public Boolean valid() {
		return isValid;
	}

	@Override
	public void onValidationSucceeded() {
		isValid = true;
		if ( isValid ) {
			if ( valid() && null != surveyData.getImage() ) {
				saveDataIntoDatabase();
			} else {
				Toast.makeText(getActivity(), "please capture image", Toast.LENGTH_LONG).show();
			}
		}

	}

	@Override
	public void onValidationFailed( List< ValidationError > validationErrors ) {
		for ( ValidationError error : validationErrors ) {
			View view = error.getView();
			String message = error.getCollatedErrorMessage(getActivity());
			Log.e("errror   ", message);
			if ( view instanceof EditText ) {
				view.requestFocus();
				(( EditText ) view).setError(message);
				isValid = false;
			}
			if ( view instanceof RadioGroup ) {
				view.requestFocus();
				Toast.makeText(getActivity(), "please fill all field", Toast.LENGTH_LONG).show();
				isValid = false;
			}

			if ( view instanceof MaterialSpinner ) {

				spinnerSurveyor.setError("       this Field is must          ");
				isValid = false;
			}

		}
	}

	@Override
	public void onActivityResult( int requestCode, int resultCode, Intent data ) {
		try {
			Log.e("in the less 19", "" + resultCode + "    " + currentSdk + imageUri.getPath());
			if ( resultCode == - 1 ) {
				Log.e("in the less 19", "" + currentSdk + imageUri.getPath());
				if ( requestCode == REQUEST_IMAGE_CAPTURE ) {
					if ( currentSdk == 19 ) {

						Bundle extras = data.getExtras();
						Bitmap imageBitmap = ( Bitmap ) extras.get("data");

						OutputStream stream = new FileOutputStream(imageUri.getPath());
						imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
						surveyData.setImage(imageUri.getPath());
						imageView.setImageBitmap(imageBitmap);
						saveImageInDatabase(imageUri);


					} else {

						showImage(imageUri);
						saveImageInDatabase(imageUri);


					}
				}
			} else {
				showImage(imageUri);
			}


		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}

	private void saveImageInDatabase( Uri imageUri ) {
		File file = new File(imageUri.getPath());
		surveyData.setImage(file.getName());
		surveyData.setImagepath(imageUri.getPath());
		Log.e("image file name", file.getName());
		Log.e("image path", imageUri.getPath());
	}

	private String setFolderLocation() {
		final String path = Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		return path + "/FormVille/";

	}

	/*@Override
	public void onDestroyView() {
		Log.e(" onDestroyView", "fragment");
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		Log.e("onDestroy on fragment", "onDestroy");
		super.onDestroy();
	}

	@Override
	protected void finalize() throws Throwable {
		Log.e("finalize", "finalize");
		super.finalize();
	}*/
	public void showImage( Uri imageUri ) {
		FileInputStream fileInputStream;
		BufferedInputStream bufferedStream;


		try {
			fileInputStream = new FileInputStream(imageUri.getPath());
			bufferedStream = new BufferedInputStream(fileInputStream);
			Bitmap bMap = BitmapFactory.decodeStream(bufferedStream);
			imageView.setImageBitmap(bMap);
		} catch ( FileNotFoundException e ) {
			e.printStackTrace();
		}
	}


	/*@Override
	public void onStop() {
		Log.e(" onStop on fragment", " onStop on fragment");
		super.onStop();
	}*/

	public interface OnBackPressed {
		public void back();
	}
}
