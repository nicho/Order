/**
 * 
 */
package com.omdasoft.orderonline.domain.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

/**
 * Stores the role of a user.
 * 
 * @author cyril
 * @since 0.2.0
 */
@Entity
public class Token implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2017666688884913104L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	private Date expirationTime;
	@ManyToOne
	private SysUser user;
//	@ManyToOne
//	private SysRole role;
//	@ManyToOne
//	private Corporation corporation;
//	@ManyToOne
//	private Department subsidiary;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getExpirationTime() {
		return expirationTime;
	}
	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}
	public SysUser getUser() {
		return user;
	}
	public void setUser(SysUser user) {
		this.user = user;
	}

	
	
	
}
