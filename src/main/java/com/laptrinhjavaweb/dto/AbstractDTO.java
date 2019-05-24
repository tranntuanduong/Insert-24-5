package com.laptrinhjavaweb.dto;

import java.sql.Timestamp;

public class AbstractDTO {
	
	private Long id;
	private Timestamp createddate; 
	private Timestamp modifieddate;
	private Timestamp createdby; 
	private Timestamp modifiedby;
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
	public Timestamp getCreatedby() {
		return createdby;
	}
	public void setCreatedby(Timestamp createdby) {
		this.createdby = createdby;
	}
	public Timestamp getModifiedby() {
		return modifiedby;
	}
	public void setModifiedby(Timestamp modifiedby) {
		this.modifiedby = modifiedby;
	}
	
	
}
