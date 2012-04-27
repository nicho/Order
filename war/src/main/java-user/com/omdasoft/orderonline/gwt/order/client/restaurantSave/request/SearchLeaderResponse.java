package com.omdasoft.orderonline.gwt.order.client.restaurantSave.request;

import net.customware.gwt.dispatch.shared.Result;

import com.omdasoft.orderonline.gwt.order.client.restaurantList.choose.SearchLeaderResult;

public class SearchLeaderResponse implements Result {

	private SearchLeaderResult result;

	public SearchLeaderResponse() {
	}

	public SearchLeaderResponse(SearchLeaderResult result) {
		this.result = result;
	}

	public SearchLeaderResult getResult() {
		return result;
	}

	public void setResult(SearchLeaderResult result) {
		this.result = result;
	}

}
