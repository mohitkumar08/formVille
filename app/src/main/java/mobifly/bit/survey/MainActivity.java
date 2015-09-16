package mobifly.bit.survey;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.melnykov.fab.FloatingActionButton;

import java.util.List;

import adapter.SurveyAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import model.Survey;


public class MainActivity extends ActionBarActivity {


	@Bind ( R.id.startSurvey )
	FloatingActionButton fab;
	@Bind ( R.id.cardView )
	RecyclerView recyclerView;


	ActionBar actionBar;


	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		actionBar = getSupportActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#E64A19")));
		setTitle("Survey Data");

		setSavedSurveyData();

	}

	private void setSavedSurveyData() {
		try {
			final List< Survey > surveyList = Survey.listAll(Survey.class);
			Gson json = new Gson();
			Log.e("data kya hai", json.toJson(surveyList));
			recyclerView.setHasFixedSize(true);
			final LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
			llm.setOrientation(LinearLayoutManager.VERTICAL);
			recyclerView.setLayoutManager(llm);
			final SurveyAdapter adapter1 = new SurveyAdapter(getApplicationContext(), surveyList, MainActivity.this);
			adapter1.notifyDataSetChanged();
			recyclerView.setAdapter(adapter1);
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}


	@OnClick ( R.id.startSurvey )
	public void startSurvey() {
		startActivity(new Intent(MainActivity.this, AddNewSurvey.class));
		finish();
	}

}
