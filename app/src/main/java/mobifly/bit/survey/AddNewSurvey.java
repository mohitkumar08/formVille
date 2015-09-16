package mobifly.bit.survey;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fragment.FifthFragmentForm;
import fragment.FirstFragmentForm;
import fragment.FourthFragmentForm;
import fragment.SecondFragmentForm;
import fragment.SixFragmentForm;
import fragment.ThirdFragmentForm;
import helper.CommonHelper;
import model.Survey;
import myinterface.ActivityToFragment;


public class AddNewSurvey extends LocationActivity implements ActivityToFragment, SixFragmentForm.OnBackPressed {

	public static int currentFragment = 1;
	public static List< Object > fragmentListObject = new ArrayList<>();
	@Bind (R.id.awesomeToolbar)
	public Toolbar toolbar;
	public Survey surveyData = Survey.newInstance();
	FragmentTransaction fragmentTransaction;
	android.support.v4.app.FragmentManager fragmentManager;
	@Bind (R.id.button_prev)
	ImageView previous;
	@Bind (R.id.button_next)
	ImageView next;
	FragmentManager manager;
	CommonHelper commonHelper;

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_survey);
		ButterKnife.bind(this);
		fragmentTransaction = this.getFragmentManager().beginTransaction();
		manager = getSupportFragmentManager();
		replaceCurrentFragment(currentFragment);
		surveyData.setCreateddate(getCurrentDateTime());
		toolbar.setTitle("Add New Survey");
		toolbar.setTitleTextColor(Color.WHITE);

	}

	private void replaceCurrentFragment( int currentFragment ) {

		switch ( currentFragment ) {
			case 1:
				previous.setVisibility(View.INVISIBLE);
				manager.beginTransaction().replace(R.id.layout_container, FirstFragmentForm.newInstance()).commit();
				break;
			case 2:
				showData();
				previous.setVisibility(View.VISIBLE);
				manager.beginTransaction().replace(R.id.layout_container, SecondFragmentForm.newInstance()).commit();
				break;
			case 3:
				manager.beginTransaction().replace(R.id.layout_container, ThirdFragmentForm.newInstance()).commit();
				break;
			case 4:
				manager.beginTransaction().replace(R.id.layout_container, FourthFragmentForm.newInstance()).commit();
				break;
			case 5:
				next.setVisibility(View.VISIBLE);
				manager.beginTransaction().replace(R.id.layout_container, FifthFragmentForm.newInstance()).commit();
				break;
			case 6:
				next.setVisibility(View.INVISIBLE);
				manager.beginTransaction().replace(R.id.layout_container, SixFragmentForm.newInstance()).commit();
				break;
		}

	}

	@OnClick (R.id.button_prev)
	public void setPrevious( View view ) {
		switch ( currentFragment ) {
			case 1:
				replaceCurrentFragment(currentFragment);
				break;
			case 2:
				currentFragment = currentFragment - 1;
				replaceCurrentFragment(currentFragment);
				break;
			case 3:
				currentFragment = currentFragment - 1;
				replaceCurrentFragment(currentFragment);
				break;
			case 4:
				currentFragment = currentFragment - 1;
				replaceCurrentFragment(currentFragment);
				break;
			case 5:
				currentFragment = currentFragment - 1;
				replaceCurrentFragment(currentFragment);
				break;
			case 6:
				currentFragment = currentFragment - 1;
				replaceCurrentFragment(currentFragment);
				break;
		}
	}


	@OnClick (R.id.button_next)
	public void setNext( View view ) {
		switch ( currentFragment ) {
			case 1:
				if ( FirstFragmentForm.newInstance().valid() ) {
					currentFragment = currentFragment + 1;
					replaceCurrentFragment(currentFragment);
				}
				break;

			case 2:
				if ( SecondFragmentForm.newInstance().valid() ) {
					currentFragment = currentFragment + 1;
					replaceCurrentFragment(currentFragment);
				}
				break;
			case 3:
				if ( ThirdFragmentForm.newInstance().valid() ) {
					currentFragment = currentFragment + 1;
					replaceCurrentFragment(currentFragment);
				}
				break;
			case 4:
				if ( FourthFragmentForm.newInstance().valid() ) {
					currentFragment = currentFragment + 1;
					replaceCurrentFragment(currentFragment);
				}
				break;
			case 5:
				if ( FifthFragmentForm.newInstance().valid() ) {
					currentFragment = currentFragment + 1;
					replaceCurrentFragment(currentFragment);
				}
				break;
			case 6:
				if ( SixFragmentForm.newInstance().valid() ) {
					replaceCurrentFragment(currentFragment);
				}
				break;
		}

	}

	@Override
	public void isValid( Boolean status ) {

	}

/*
	@Override
	public boolean onCreateOptionsMenu( Menu menu ) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_add_new_survey, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected( MenuItem item ) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if ( id == R.id.action_settings ) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}*/


	public String getCurrentDateTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(new Date());
	}

	public void showData() {
		try {
			Log.e("inform", surveyData.getCompleteaddress() + "   " + surveyData.getPlotno());
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}

	@Override
	public void currantLocation( final android.location.Location mLocation ) {
		try {

			surveyData.setLongitude(String.valueOf(mLocation.getLongitude()));
			surveyData.setLatitude(String.valueOf(mLocation.getLatitude()));
			Log.e("langitude and lotitude", surveyData.getLatitude() + "   " + surveyData.getLongitude());
		} catch ( Exception error ) {
			error.printStackTrace();
			Log.e("Exception", "log" + error);
		}
	}

	/**
	 * on Resume of activity
	 */
	@Override
	public void onResume() {
		commonHelper = new CommonHelper(getApplicationContext());
		isGpsPresent = commonHelper.isGpsPresent(context);
		if ( ! isGpsPresent ) {
			showGpsSetting();
		}
		super.onResume();
	}

	@Override
	public boolean onKeyDown( final int keyCode, final KeyEvent event ) {
		Log.e("onKeyDown", "onKeyDown");
		if ( keyCode == KeyEvent.KEYCODE_BACK ) {

		}
		return super.onKeyDown(keyCode, event);
	}


	@Override
	public void back() {
		currentFragment = 1;
		//manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
		Intent intent = new Intent(AddNewSurvey.this, MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		clearFragment();
		finish();

	}

	public void clearFragment() {
		Survey.deleteInstance();
		FirstFragmentForm.deleteInstance();
		SecondFragmentForm.deleteInstance();
		ThirdFragmentForm.deleteInstance();
		FourthFragmentForm.deleteInstance();
		FifthFragmentForm.deleteInstance();
		SixFragmentForm.deleteInstance();
	}


}
