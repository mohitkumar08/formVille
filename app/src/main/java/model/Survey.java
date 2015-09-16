package model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by Bit on 9/10/2015.
 */
public class Survey extends SugarRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	public static Survey surveyData = null;
	private String city;
	private String plotno;
	private String completeaddress;
	private String locality;
	private String typeofconstruction;
	private String category;
	private String staytype;
	private String plotarea;
	private String floors;
	private String stageofconstruction;
	private String whenconstruction;
	private String detailconstruction;
	private String slabs;
	private String rooms;
	private String bathroom;
	private String kitchen;
	private String emailid;
	private String ownername;
	private String ownercontact;
	private String buildername;
	private String buildercontact;
	private String architectname;
	private String architectcontact;
	private String surveyorname;
	private String remarks;
	private String image;
	private String latitude;
	private String longitude;
	private String createddate;
	private String issync;
	private String imagepath;

	public Survey() {
		super();
	}

	public static Survey newInstance() {
		if ( surveyData == null ) {
			surveyData = new Survey();
		}
		return surveyData;
	}

	public static Survey deleteInstance() {
		surveyData = null;
		return surveyData;
	}

	public static Survey getSurveyData() {
		return surveyData;
	}

	public static void setSurveyData( Survey surveyData ) {
		Survey.surveyData = surveyData;
	}

	public static long getSerialVersionUID() {

		return serialVersionUID;
	}

	public String getIssync() {
		return issync;
	}

	public void setIssync( String issync ) {
		this.issync = issync;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude( String latitude ) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude( String longitude ) {
		this.longitude = longitude;
	}

	public String getCity() {
		return city;
	}

	public void setCity( String city ) {
		this.city = city;
	}

	public String getPlotno() {
		return plotno;
	}

	public void setPlotno( String plotno ) {
		this.plotno = plotno;
	}

	public String getCompleteaddress() {
		return completeaddress;
	}

	public void setCompleteaddress( String completeaddress ) {
		this.completeaddress = completeaddress;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality( String locality ) {
		this.locality = locality;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory( String category ) {
		this.category = category;
	}

	public String getStaytype() {
		return staytype;
	}

	public void setStaytype( String staytype ) {
		this.staytype = staytype;
	}

	public String getPlotarea() {
		return plotarea;
	}

	public void setPlotarea( String plotarea ) {
		this.plotarea = plotarea;
	}

	public String getFloors() {
		return floors;
	}

	public void setFloors( String floors ) {
		this.floors = floors;
	}

	public String getStageofconstruction() {
		return stageofconstruction;
	}

	public void setStageofconstruction( String stageofconstruction ) {
		this.stageofconstruction = stageofconstruction;
	}

	public String getWhenconstruction() {
		return whenconstruction;
	}

	public void setWhenconstruction( String whenconstruction ) {
		this.whenconstruction = whenconstruction;
	}

	public String getDetailconstruction() {
		return detailconstruction;
	}

	public void setDetailconstruction( String detailconstruction ) {
		this.detailconstruction = detailconstruction;
	}

	public String getSlabs() {
		return slabs;
	}

	public void setSlabs( String slabs ) {
		this.slabs = slabs;
	}

	public String getRooms() {
		return rooms;
	}

	public void setRooms( String rooms ) {
		this.rooms = rooms;
	}

	public String getBathroom() {
		return bathroom;
	}

	public void setBathroom( String bathroom ) {
		this.bathroom = bathroom;
	}

	public String getKitchen() {
		return kitchen;
	}

	public void setKitchen( String kitchen ) {
		this.kitchen = kitchen;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid( String emailid ) {
		this.emailid = emailid;
	}

	public String getOwnername() {
		return ownername;
	}

	public void setOwnername( String ownername ) {
		this.ownername = ownername;
	}

	public String getOwnercontact() {
		return ownercontact;
	}

	public void setOwnercontact( String ownercontact ) {
		this.ownercontact = ownercontact;
	}

	public String getBuildername() {
		return buildername;
	}

	public void setBuildername( String buildername ) {
		this.buildername = buildername;
	}

	public String getBuildercontact() {
		return buildercontact;
	}

	public void setBuildercontact( String buildercontact ) {
		this.buildercontact = buildercontact;
	}

	public String getArchitectname() {
		return architectname;
	}

	public void setArchitectname( String architectname ) {
		this.architectname = architectname;
	}

	public String getArchitectcontact() {
		return architectcontact;
	}

	public void setArchitectcontact( String architectcontact ) {
		this.architectcontact = architectcontact;
	}

	public String getSurveyorname() {
		return surveyorname;
	}

	public void setSurveyorname( String surveyorname ) {
		this.surveyorname = surveyorname;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks( String remarks ) {
		this.remarks = remarks;
	}

	public String getImage() {
		return image;
	}

	public void setImage( String image ) {
		this.image = image;
	}

	public String getTypeofconstruction() {
		return typeofconstruction;
	}

	public void setTypeofconstruction( String typeofconstruction ) {
		this.typeofconstruction = typeofconstruction;
	}

	public String getCreateddate() {
		return createddate;
	}

	public void setCreateddate( String createddate ) {
		this.createddate = createddate;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath( String imagepath ) {
		this.imagepath = imagepath;
	}
}
