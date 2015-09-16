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
import com.mobsandgeeks.saripaar.annotation.Max;
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

public class SecondFragmentForm extends Fragment implements Validator.ValidationListener {
	public static SecondFragmentForm fragment;
	public ArrayAdapter< String > tocAdapter;
	public ArrayAdapter< String > categoryAdapter;
	public ArrayAdapter< String > stayTypeAdapter;
	public Survey surveyData = Survey.newInstance();
	Validator validator;
	Boolean isValid = false;

	@NotEmpty ( message = "this Field is must" )
	@Bind ( R.id.editTextPlotSize )
	EditText plotSize;

	@NotEmpty ( message = "this Field is must" )
	@Max ( value = 10, message = "please enter value 0-10" )
	@Bind ( R.id.editTextNoOfFloors )
	EditText floors;

	@Select ( message = "Please select a choice" )
	@Bind ( R.id.spinnerTypeOfConstruction )
	MaterialSpinner spinnerTypeOfConstruction;
	@Select ( message = "Please select a choice" )
	@Bind ( R.id.spinnerCategory )
	MaterialSpinner spinnerCategory;
	@Select ( message = "Please select a choice" )
	@Bind ( R.id.spinnerStayType )
	MaterialSpinner spinnerStayType;

	public SecondFragmentForm() {
		// Required empty public constructor
	}

	public static SecondFragmentForm newInstance() {
		if ( fragment == null ) {
			fragment = new SecondFragmentForm();
		}
		return fragment;
	}


	public static SecondFragmentForm deleteInstance() {
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
		View view = inflater.inflate(R.layout.fragment_second_fragment_form, container, false);
		ButterKnife.bind(this, view);
		setAdapter();
		return view;
	}

	private void setAdapter() {

		tocAdapter = new ArrayAdapter< String >(getActivity(), android.R.layout.simple_spinner_item, Constant.TYPE_OF_CONSTRUCTION);
		tocAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerTypeOfConstruction.setAdapter(tocAdapter);
		spinnerTypeOfConstruction.setPaddingSafe(0, 0, 0, 0);


		categoryAdapter = new ArrayAdapter< String >(getActivity(), android.R.layout.simple_spinner_item, Constant.CATEGORY);
		categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerCategory.setAdapter(categoryAdapter);
		spinnerCategory.setPaddingSafe(0, 0, 0, 0);

		stayTypeAdapter = new ArrayAdapter< String >(getActivity(), android.R.layout.simple_spinner_item, Constant.STAY_TYPE);
		stayTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerStayType.setAdapter(stayTypeAdapter);
		spinnerStayType.setPaddingSafe(0, 0, 0, 0);


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
		surveyData.setPlotarea(plotSize.getText().toString());
		surveyData.setFloors(floors.getText().toString());
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
				spinnerCategory.setError("       this Field is must          ");
				spinnerTypeOfConstruction.setError("       this Field is must          ");
				spinnerStayType.setError("       this Field is must          ");

			}
		}
	}

	public Boolean valid() {
		validator.validate();
		return isValid;
	}

	@OnItemSelected ( value = R.id.spinnerTypeOfConstruction )
	void onSelectTypeOfCons( int position ) {
		if ( position >= 0 ) {
			surveyData.setTypeofconstruction(tocAdapter.getItem(position));
		}
	}

	@OnItemSelected ( value = R.id.spinnerCategory )
	void OnSelectCategory( int position ) {
		if ( position >= 0 ) {
			surveyData.setCategory(categoryAdapter.getItem(position));
		}
	}

	@OnItemSelected ( value = R.id.spinnerStayType )
	void OnSelectStayType( int position ) {
		if ( position >= 0 ) {
			surveyData.setStaytype(stayTypeAdapter.getItem(position));
		}
	}

}
