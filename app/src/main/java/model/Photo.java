package model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by Bit on 9/11/2015.
 */
public class Photo extends SugarRecord implements Serializable {
	private String imagename;
	private String imagepath;
	private String surveyid;
	private int issync;

	public String getImagename() {
		return imagename;
	}

	public void setImagename( String imagename ) {
		this.imagename = imagename;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath( String imagepath ) {
		this.imagepath = imagepath;
	}

	public String getSurveyid() {
		return surveyid;
	}

	public void setSurveyid( String surveyid ) {
		this.surveyid = surveyid;
	}

	public int getIssync() {
		return issync;
	}

	public void setIssync( int issync ) {
		this.issync = issync;
	}
}
