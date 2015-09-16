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
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import mobifly.bit.survey.R;
import model.Survey;

public class FourthFragmentForm extends Fragment implements Validator.ValidationListener {

	public static FourthFragmentForm fragment;
	public Survey surveyData = Survey.newInstance();
	@Email (message = "please enter valid email")
	@Bind (R.id.editTextEmailId)
	EditText editTextEmailId;
	@Bind (R.id.editTextOwnerName)
	EditText editTextOwnerName;
	@Pattern (regex = "(^$|[0-9]{10})", message = "Please enter valid mobile Number")
	@Bind (R.id.editTextOwnerContactNo)
	EditText editTextOwnerContact;
	Validator validator;
	Boolean isValid = false;

	public FourthFragmentForm() {
		// Required empty public constructor
	}

	public static FourthFragmentForm newInstance() {
		if ( fragment == null ) {
			fragment = new FourthFragmentForm();
		}

		return fragment;
	}

	public static FourthFragmentForm deleteInstance() {
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
		View view = inflater.inflate(R.layout.fragment_fourth_fragment_form, container, false);
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

		if ( editTextEmailId.getText().length() > 0 ) {
			validator.validate();
		} else {
			isValid = true;
			surveyData.setEmailid(editTextEmailId.getText().toString());
			surveyData.setOwnername(editTextOwnerName.getText().toString());
			surveyData.setOwnercontact(editTextOwnerContact.getText().toString());

		}

		return isValid;
	}

	@Override
	public void onValidationSucceeded() {
		isValid = true;
		Log.e("some information", editTextOwnerContact.getText().toString());
		surveyData.setEmailid(editTextEmailId.getText().toString());
		surveyData.setOwnername(editTextOwnerName.getText().toString());
		surveyData.setOwnercontact(editTextOwnerContact.getText().toString());

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
