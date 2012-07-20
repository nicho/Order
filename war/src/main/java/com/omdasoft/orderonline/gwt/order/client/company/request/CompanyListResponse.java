package com.omdasoft.orderonline.gwt.order.client.company.request;

 
import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.omdasoft.orderonline.gwt.order.client.company.model.CompanyListClient;

public class CompanyListResponse implements Result {
	private List<CompanyListClient> result;
	private int total;


	/**
	 * @return the result
	 */
	public List<CompanyListClient> getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(List<CompanyListClient> result) {

		this.result = result;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

}
