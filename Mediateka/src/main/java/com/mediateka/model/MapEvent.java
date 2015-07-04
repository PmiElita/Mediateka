package com.mediateka.model;

import com.mediateka.annotation.Column;
import com.mediateka.model.enums.State;

public class MapEvent {
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name ="description")
	private String description;
	
	@Column(name = "adress")
	private String adress;
	
	@Column(name = "state")
	private State state;
	
	@Column(name = "admin_id")
	private Integer adminId;
   
	@Column(name = "latitude")
	private Double latitude;
	
	@Column(name = "longitude")
	private Double longitude;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "MapEvent [id=" + id + ", name=" + name + ", description="
				+ description + ", adress=" + adress + ", state=" + state
				+ ", adminId=" + adminId + "]";
	}
	
	
}
