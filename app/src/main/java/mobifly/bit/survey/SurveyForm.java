package mobifly.bit.survey;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TabHost;

import java.util.Locale;


public class SurveyForm extends ActionBarActivity implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {


	static int currentPage = 0;
	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_survey_form);


		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = ( ViewPager ) findViewById(R.id.pager);
		mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled( int position, float positionOffset, int positionOffsetPixels ) {
				//	Log.e(" onPageScrolled "," "+ position+"   "+positionOffset+"   "+positionOffsetPixels);
			}

			@Override
			public void onPageSelected( int position ) {


				Log.e(" onPageSelected ", " " + position);
			}

			@Override
			public void onPageScrollStateChanged( int state ) {
				if ( state == 1 ) {
					checkValidation(currentPage);
				}
				Log.e(" onPageScrollStateChanged ", " " + state);
			}
		});
		mViewPager.setAdapter(mSectionsPagerAdapter);

	}

	private void checkValidation( int page ) {
		/*switch ( page ) {
			case 1:
				FirstForm form = new FirstForm();
				form.checkValidation();
				break;
			case 2:
				break;
			default:
				break;

		}*/
	}

	@Override
	public void onPageScrolled( int position, float positionOffset, int positionOffsetPixels ) {

	}

	@Override
	public void onPageSelected( int position ) {

	}

	@Override
	public void onPageScrollStateChanged( int state ) {

	}

	@Override
	public void onTabChanged( String tabId ) {

	}

/*

	@Override
	public boolean onCreateOptionsMenu( Menu menu ) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_survey_form, menu);
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
	}
*/


	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter( FragmentManager fm ) {
			super(fm);
		}

		@Override
		public Fragment getItem( int position ) {
			Log.e("SectionsPagerAdapter   ", "     " + position);

			/*switch ( position ) {
				case 0:
					FirstForm firstForm = new FirstForm();
					return firstForm;
				case 1:
					SecondForm secondForm = new SecondForm();
					return secondForm;


			}*/
			return null;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 2;
		}

		@Override
		public CharSequence getPageTitle( int position ) {
			Locale l = Locale.getDefault();
			switch ( position ) {
				case 0:
					return "Food";
				case 1:
					return "Housing";
			/*	case 2:
					return "Park";
*/
			}
			return null;
		}
	}

/*
	public static class FirstForm extends Fragment implements  Validator.ValidationListener{
		*//**
	 * The fragment argument representing the section number for this
	 * fragment.
	 *//*
		@NotEmpty ( message = "You must agree to the terms." )
		@Bind ( R.id.editText_plot )
		EditText plot;
		Validator validator;
		private static final String ARG_SECTION_NUMBER = "section_number";

		*//**
	 * Returns a new instance of this fragment for the given section
	 * number.
	 *//*
		*//*public static FirstForm newInstance( int sectionNumber ) {
			FirstForm fragment = new FirstForm();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}*//*
		public FirstForm() {
		}

		@Override
		public View onCreateView( LayoutInflater inflater, ViewGroup container,
								  Bundle savedInstanceState ) {

			View rootView = inflater.inflate(R.layout.fragment_first_form, container, false);
			return rootView;
		}

		public void checkValidation()
		{
			validator.validate();
		}
		*//**
	 * When creating, retrieve this instance's number from its arguments.
	 *//*
		@Override
		public void onCreate( Bundle savedInstanceState ) {
			validator = new Validator(this);
			validator.setValidationListener(this);
			super.onCreate(savedInstanceState);

		}

		@Override
		public void onValidationSucceeded() {

		}

		@Override
		public void onValidationFailed( List< ValidationError > validationErrors ) {

		}
	}

	public static class SecondForm extends Fragment {
		*//**
	 * The fragment argument representing the section number for this
	 * fragment.
	 *//*
		private static final String ARG_SECTION_NUMBER = "section_number";


		public SecondForm() {
		}

		@Override
		public View onCreateView( LayoutInflater inflater, ViewGroup container,
								  Bundle savedInstanceState ) {
			View rootView = inflater.inflate(R.layout.fragment_second_form, container, false);
			return rootView;
		}
	}

	public static class ThirdForm extends Fragment {
		*//**
	 * The fragment argument representing the section number for this
	 * fragment.
	 *//*
		private static final String ARG_SECTION_NUMBER = "section_number";

		*//**
	 * Returns a new instance of this fragment for the given section
	 * number.
	 *//*
		public static ThirdForm newInstance( int sectionNumber ) {
			ThirdForm fragment = new ThirdForm();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public ThirdForm() {
		}

		@Override
		public View onCreateView( LayoutInflater inflater, ViewGroup container,
								  Bundle savedInstanceState ) {
			View rootView = inflater.inflate(R.layout.fragment_third_form, container, false);
		*//*	DiscreteSeekBar discreteSeekBar1 = (DiscreteSeekBar) findViewById(R.id.discrete1);
			discreteSeekBar1.setNumericTransformer(new DiscreteSeekBar.NumericTransformer() {
				@Override
				public int transform(int value) {
					return value * 100;
				}
			});
		}*//*
			return rootView;
		}
	}

	public static class FourthForm extends Fragment {
		*//**
	 * The fragment argument representing the section number for this
	 * fragment.
	 *//*
		private static final String ARG_SECTION_NUMBER = "section_number";

		*//**
	 * Returns a new instance of this fragment for the given section
	 * number.
	 *//*
		public static FourthForm newInstance( int sectionNumber ) {
			FourthForm fragment = new FourthForm();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public FourthForm() {
		}

		@Override
		public View onCreateView( LayoutInflater inflater, ViewGroup container,
								  Bundle savedInstanceState ) {
			View rootView = inflater.inflate(R.layout.fragment_fourth_form, container, false);
			return rootView;
		}
	}

	public static class FifthForm extends Fragment {
		*//**
	 * The fragment argument representing the section number for this
	 * fragment.
	 *//*
		private static final String ARG_SECTION_NUMBER = "section_number";

		*//**
	 * Returns a new instance of this fragment for the given section
	 * number.
	 *//*
		public static FifthForm newInstance( int sectionNumber ) {
			FifthForm fragment = new FifthForm();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public FifthForm() {
		}

		@Override
		public View onCreateView( LayoutInflater inflater, ViewGroup container,
								  Bundle savedInstanceState ) {
			View rootView = inflater.inflate(R.layout.fragment_fifth_form, container, false);
			return rootView;
		}
	}

	public static class SixForm extends Fragment {
		*//**
	 * The fragment argument representing the section number for this
	 * fragment.
	 *//*
		private static final String ARG_SECTION_NUMBER = "section_number";

		*//**
	 * Returns a new instance of this fragment for the given section
	 * number.
	 *//*
		public static SixForm newInstance( int sectionNumber ) {
			SixForm fragment = new SixForm();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public SixForm() {
		}

		@Override
		public View onCreateView( LayoutInflater inflater, ViewGroup container,
								  Bundle savedInstanceState ) {
			View rootView = inflater.inflate(R.layout.fragment_six_form, container, false);
			return rootView;
		}
	}*/
}
