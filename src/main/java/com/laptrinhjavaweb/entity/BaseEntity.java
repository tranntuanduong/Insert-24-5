 package com.laptrinhjavaweb.entity;

import java.sql.Timestamp;

import com.laptrinhjavaweb.annotation.Column;


public class BaseEntity {
	@Column(name = "id")
	private Long id;
	
	@Column(name = "createdDate")
	private Timestamp createddate; 
	
	@Column(name = "modifiedDate")
	private Timestamp modifieddate;
	
	@Column(name = "createdBy")	
	private String createdby; 
	
	@Column(name = "modifiedBy")
	private String modifiedby;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Timestamp getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}
	public Timestamp getModifieddate() {
		return modifieddate;
	}
	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getModifiedby() {
		return modifiedby;
	}
	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}
	
	
	
}
