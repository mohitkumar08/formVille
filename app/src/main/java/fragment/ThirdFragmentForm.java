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

public class ThirdFragmentForm extends Fragment implements Validator.ValidationListener {
	public static ThirdFragmentForm fragment;
	public ArrayAdapter< String > socAdapter;
	public ArrayAdapter< String > wcAdapter;
	public ArrayAdapter< String > dcAdapter;
	public Survey surveyData = Survey.newInstance();
	Validator validator;
	Boolean isValid = false;

	@NotEmpty (message = "this Field is must")
	@Max (value = 10, message = "please enter value 0-10")
	@Bind (R.id.editTextNoOfBathroom)
	EditText bathroom;


	@NotEmpty (message = "this Field is must")
	@Max (value = 10, message = "please enter value 0-10")
	@Bind (R.id.editTextNoOfRooms)
	EditText rooms;

	@NotEmpty (message = "this Field is must")
	@Max (value = 10, message = "please enter value 0-10")
	@Bind (R.id.editTextNoOfKitchen)
	EditText kitchen;

	@NotEmpty (message = "this Field is must")
	@Max (value = 10, message = "please enter value 0-10")
	@Bind (R.id.editTextslabsDecreate)
	EditText slabsDecreate;

	@Select (message = "Please select a choice")
	@Bind (R.id.spinnerStageOfConstruction)
	MaterialSpinner spinnerStageOfConstruction;
	@Select (message = "Please select a choice")
	@Bind (R.id.spinnerWhenConstruction)
	MaterialSpinner spinnerWhenConstruction;
	@Select (message = "Please select a choice")
	@Bind (R.id.spinnerDetailsConstruction)
	MaterialSpinner spinnerDetailsConstruction;
	/*
		@Bind ( R.id.slabsDecreate )
		DiscreteSeekBar discreteSeekBar;*/


	public ThirdFragmentForm() {
		// Required empty public constructor
	}

	public static ThirdFragmentForm newInstance() {
		if ( fragment == null ) {
			fragment = new ThirdFragmentForm();
		}

		return fragment;
	}

	public static ThirdFragmentForm deleteInstance() {
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
		View view = inflater.inflate(R.layout.fragment_third_fragment_form, container, false);
		ButterKnife.bind(this, view);
		setAdapter();
		return view;
	}

	private void setAdapter() {

		socAdapter = new ArrayAdapter< String >(getActivity(), android.R.layout.simple_spinner_item, Constant.STAGE_OF_CONSTRUCTION);
		socAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerStageOfConstruction.setAdapter(socAdapter);
		spinnerStageOfConstruction.setPaddingSafe(0, 0, 0, 0);


		wcAdapter = new ArrayAdapter< String >(getActivity(), android.R.layout.simple_spinner_item, Constant.WHEN_CONSTRUCTION);
		wcAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerWhenConstruction.setAdapter(wcAdapter);
		spinnerWhenConstruction.setPaddingSafe(0, 0, 0, 0);

		dcAdapter = new ArrayAdapter< String >(getActivity(), android.R.layout.simple_spinner_item, Constant.DETAIL_OF_CONSTRUCTION);
		dcAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerDetailsConstruction.setAdapter(dcAdapter);
		spinnerDetailsConstruction.setPaddingSafe(0, 0, 0, 0);


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
		surveyData.setBathroom(bathroom.getText().toString());
		surveyData.setRooms(rooms.getText().toString());
		surveyData.setKitchen(kitchen.getText().toString());
		//surveyData.setSlabs(String.valueOf(discreteSeekBar.getProgress()));
		surveyData.setSlabs(slabsDecreate.getText().toString());
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
				spinnerStageOfConstruction.setError("       this Field is must          ");
				spinnerWhenConstruction.setError("       this Field is must          ");
				spinnerDetailsConstruction.setError("       this Field is must          ");
				isValid = false;
			}

		}
	}

	public Boolean valid() {
		validator.validate();
		return isValid;
	}

	@OnItemSelected (value = R.id.spinnerStageOfConstruction)
	void onSelectStageOfCons( int position ) {
		if ( position >= 0 ) {
			surveyData.setStageofconstruction(socAdapter.getItem(position));
		}
	}

	@OnItemSelected (value = R.id.spinnerWhenConstruction)
	void onSelectWhenCons( int position ) {
		if ( position >= 0 ) {
			surveyData.setWhenconstruction(wcAdapter.getItem(position));
		}
	}

	@OnItemSelected (value = R.id.spinnerDetailsConstruction)
	void onSelectDetailCons( int position ) {
		if ( position >= 0 ) {
			surveyData.setDetailconstruction(dcAdapter.getItem(position));
		}
	}

}
