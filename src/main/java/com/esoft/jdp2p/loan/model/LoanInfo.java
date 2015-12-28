package com.esoft.jdp2p.loan.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "loan_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "entityCache")
public class LoanInfo {

	private String id;
	private String infoOne;
	private String infoTwo;
	private String infoThree;
	private String infoFour;
	private String infoFive;
	private String infoSix;
	private Date infoTime;


	@Id
	@Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return id;
	}

	@Column(name = "info_one", length = 200)
	public String getInfoOne() {
		return infoOne;
	}

	@Column(name = "info_two", length = 200)
	public String getInfoTwo() {
		return infoTwo;
	}

	@Column(name = "info_three", length = 200)
	public String getInfoThree() {
		return infoThree;
	}

	@Column(name = "info_four", length = 200)
	public String getInfoFour() {
		return infoFour;
	}

	@Column(name = "info_five", length = 200)
	public String getInfoFive() {
		return infoFive;
	}

	@Column(name = "info_six", length = 200)
	public String getInfoSix() {
		return infoSix;
	}

	@Column(name = "info_time")
	public Date getInfoTime() {
		return infoTime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setInfoOne(String infoOne) {
		this.infoOne = infoOne;
	}

	public void setInfoTwo(String infoTwo) {
		this.infoTwo = infoTwo;
	}

	public void setInfoThree(String infoThree) {
		this.infoThree = infoThree;
	}

	public void setInfoFour(String infoFour) {
		this.infoFour = infoFour;
	}

	public void setInfoFive(String infoFive) {
		this.infoFive = infoFive;
	}

	public void setInfoSix(String infoSix) {
		this.infoSix = infoSix;
	}

	public void setInfoTime(Date infoTime) {
		this.infoTime = infoTime;
	}
}
