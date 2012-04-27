package com.omdasoft.orderonline.domain.dictionary;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.omdasoft.orderonline.domain.org.Corporation;

@Entity
public class Dictionary implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 4076124377833291323L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	private String name;
	private int dictionaryType;
	@ManyToOne
	private Corporation corporation;
	private Integer deleted;

	public Integer getDeleted() {
	return deleted;
}
public void setDeleted(Integer deleted) {
	this.deleted = deleted;
}
	public Corporation getCorporation() {
		return corporation;
	}
	public void setCorporation(Corporation corporation) {
		this.corporation = corporation;
	}

	public int getDictionaryType() {
		return dictionaryType;
	}
	public void setDictionaryType(int dictionaryType) {
		this.dictionaryType = dictionaryType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
