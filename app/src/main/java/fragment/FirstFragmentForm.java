package fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Select;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import fr.ganfra.materialspinner.MaterialSpinner;
import helper.Constant;
import mobifly.bit.survey.R;
import model.Survey;


public class FirstFragmentForm extends Fragment implements Validator.ValidationListener {

	public static FirstFragmentForm fragment;
	public ArrayAdapter< String > cityAdapter;
	public Survey surveyData = Survey.newInstance();

	@NotEmpty (message = "this Field is must")
	@Bind (R.id.editTextAddress)
	EditText address;

	@NotEmpty (message = "this Field is must")
	@Bind (R.id.editTextPlotNo)
	EditText editTextPlotNo;

	@NotEmpty (message = "this Field is must")
	@Bind (R.id.editTextLocality)
	EditText editTextLocality;

	@Select (message = "Please select a city")
	@Bind (R.id.spinnerCity)
	MaterialSpinner spinnerCity;

	Validator validator;
	Boolean isValid = false;

	public FirstFragmentForm() {
	}

	public static FirstFragmentForm newInstance() {
		if ( fragment == null ) {
			fragment = new FirstFragmentForm();
		}
		return fragment;
	}

	public static FirstFragmentForm deleteInstance() {
		fragment = null;
		return fragment;
	}


	@Override
	public void onCreate( Bundle savedInstanceState ) {
		validator = new Validator(this);
		validator.setValidationListener(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container,
							  Bundle savedInstanceState ) {
		View view = inflater.inflate(R.layout.fragment_first_fragment_form, container, false);
		ButterKnife.bind(this, view);
		setCityAdapter();
		return view;

	}

	private void setCityAdapter() {
		cityAdapter = new ArrayAdapter< String >(getActivity(), android.R.layout.simple_spinner_item, Constant.CITY_NAME);
		cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerCity.setAdapter(cityAdapter);
		spinnerCity.setPaddingSafe(0, 0, 0, 0);
	}

	@Override
	public void onAttach( Activity activity ) {
		super.onAttach(activity);
	}

	@Override
	public void onDetach() {
		super.onDetach();

	}

	@Override
	public void onValidationSucceeded() {
		isValid = true;
		surveyData.setPlotno(editTextPlotNo.getText().toString());
		surveyData.setCompleteaddress(address.getText().toString());
		surveyData.setLocality(editTextLocality.getText().toString());
		//editTextLocality
		Log.e("onValidationSucceeded ", "onValidationSucceeded");
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
			if ( view instanceof MaterialSpinner ) {
				spinnerCity.setError("          Please select a city          ");
			}

		}
	}

	public Boolean valid() {
		validator.validate();
		return isValid;
	}

	/*@OnClick ( {R.id.radio_gurgaon, R.id.radio_south_delhi, R.id.radio_faridabad} )
	public void getCity( RadioButton button ) {
		surveyData.setCity(button.getText().toString());
	}*/

	@OnItemSelected (value = R.id.spinnerCity)
	void onNothingSelected( int position ) {
		if ( position >= 0 ) {
			surveyData.setCity(cityAdapter.getItem(position));
		}
	}


}
