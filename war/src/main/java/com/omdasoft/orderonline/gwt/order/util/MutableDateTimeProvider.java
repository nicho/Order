/**
 * 
 */
package com.omdasoft.orderonline.gwt.order.util;

import java.util.Date;

/**
 * 
 * 
 * @author yanxin
 * 
 */
public class MutableDateTimeProvider implements DateTimeProvider {

	private Date time;
	
	public Date getTime() {
		return time;
	}
	
	public void setTime(Date time) {
		this.time = time;
	}

}
