package fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import mobifly.bit.survey.R;
import model.Survey;

public class FifthFragmentForm extends Fragment implements Validator.ValidationListener {

	public static FifthFragmentForm fragment;
	public Survey surveyData = Survey.newInstance();
	Validator validator;
	Boolean isValid = false;
	@Bind (R.id.editTextBuilderName)
	EditText builderName;
	/*@Max(value = 10,message = "error")*/
	@Pattern (regex = "(^$|[0-9]{10})", message = "Please enter valid mobile Number")
	@Bind (R.id.editTextBuilderContact)
	EditText builderContact;

	//	@Length ( max = 10, message = "error" )
	@Bind (R.id.editTextArchitectName)
	EditText architectName;
	@Pattern (regex = "(^$|[0-9]{10})", message = "Please enter valid mobile Number")
	@Bind (R.id.editTextArchitectContact)
	EditText architectContact;

	public FifthFragmentForm() {
		// Required empty public constructor
	}

	public static FifthFragmentForm newInstance() {
		if ( fragment == null ) {
			fragment = new FifthFragmentForm();
		}
		return fragment;
	}

	public static FifthFragmentForm deleteInstance() {
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
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_fifth_fragment_form, container, false);
		ButterKnife.bind(this, view);
		return view;
	}


	@Override
	public void onAttach( Activity activity ) {
		super.onAttach(activity);

	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

	public Boolean valid() {
		validator.validate();
		return isValid;
	}

	@Override
	public void onValidationSucceeded() {
		isValid = true;
		surveyData.setBuildername(builderName.getText().toString());
		surveyData.setBuildercontact(builderContact.getText().toString());
		surveyData.setArchitectname(architectName.getText().toString());
		surveyData.setArchitectcontact(architectContact.getText().toString());
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


		}
	}
}
