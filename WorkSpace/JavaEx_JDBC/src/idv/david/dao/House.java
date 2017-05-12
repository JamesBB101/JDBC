package idv.david.dao;

import java.io.Serializable;

public class House implements Serializable {
	private static final long serialVersionUID = 1L;

	private int _id;
	private String case_t;
	private String district;
	private String case_f;
	private String location;

	public House() {

	}

	public House(int _id, String case_t, String district, String case_f, String location) {
		this._id = _id;
		this.case_t = case_t;
		this.district = district;
		this.case_f = case_f;
		this.location = location;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getCase_t() {
		return case_t;
	}

	public void setCase_t(String case_t) {
		this.case_t = case_t;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCase_f() {
		return case_f;
	}

	public void setCase_f(String case_f) {
		this.case_f = case_f;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
