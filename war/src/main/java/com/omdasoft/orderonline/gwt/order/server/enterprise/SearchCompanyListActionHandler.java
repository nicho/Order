package com.omdasoft.orderonline.gwt.order.server.enterprise;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.google.inject.Inject;
import com.omdasoft.orderonline.dao.org.CorporationDao.QueryCompanyPageActionResult;
import com.omdasoft.orderonline.domain.org.Corporation;
import com.omdasoft.orderonline.gwt.order.client.company.model.CompanyListClient;
import com.omdasoft.orderonline.gwt.order.client.company.request.CompanyListRequest;
import com.omdasoft.orderonline.gwt.order.client.company.request.CompanyListResponse;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.gwt.order.server.logger.InjectLogger;
import com.omdasoft.orderonline.model.common.PaginationDetail;
import com.omdasoft.orderonline.model.common.SortingDetail;
import com.omdasoft.orderonline.model.company.CompanySearchCriteria;
import com.omdasoft.orderonline.service.org.CorporationService;

/**
 * The handler to correspond the CompanyListRequest.
 * 
 * @author sunny
 */
public class SearchCompanyListActionHandler extends
		BaseActionHandler<CompanyListRequest, CompanyListResponse> {

	@InjectLogger
	Logger logger;
	CorporationService corporationService;


	@Inject
	public SearchCompanyListActionHandler(CorporationService corporationService) {
		this.corporationService = corporationService;
	}

	@Override
	public CompanyListResponse execute(CompanyListRequest request,
			ExecutionContext response) throws DispatchException {

		CompanyListResponse companyResponse = new CompanyListResponse();
		CompanySearchCriteria criteria=new CompanySearchCriteria();
		if (request.getCompanyVo().getPagination() != null) {
			PaginationDetail detail = new PaginationDetail();
			detail.setLimit(request.getCompanyVo().getPagination().getLimit());
			detail.setStart(request.getCompanyVo().getPagination().getStart());

			criteria.setPaginationDetail(detail);
		}

		if (request.getCompanyVo().getSorting() != null) {
			SortingDetail sortingDetail = new SortingDetail();
			sortingDetail.setSort(request.getCompanyVo().getSorting().getSort());
			sortingDetail.setDirection(request.getCompanyVo().getSorting().getDirection());
			criteria.setSortingDetail(sortingDetail);
		}
		if(request.getCompanyVo().getName()!=null)
			criteria.setName(request.getCompanyVo().getName());
		
		QueryCompanyPageActionResult result=corporationService.findCorporationByCompanyListCriteria(criteria);
		
		List<CompanyListClient> lt=new ArrayList<CompanyListClient>();
		int i=0;
		for (Corporation corporation:result.getResultList()) {
			i++;
			CompanyListClient client=new CompanyListClient();
			client.setCompanyNo(String.valueOf(i));
			client.setId(corporation.getId());
			client.setName(corporation.getName());
			client.setCompanyAccountAddress("");
			client.setAddress(corporation.getAddress());
			client.setLinkman(corporation.getLinkman());
			client.setCrearteAt(corporation.getCreatedAt());
			client.setIsCreateHrAccount(corporation.getIsCreateHrAccount());
			client.setCid(corporation.getCid());
			if(corporation.getStaff()!=null)
			{
			client.setStaffId(corporation.getStaff().getId());
			client.setStaffName(corporation.getStaff().getName());
			}
			lt.add(client);
		}
		companyResponse.setResult(lt);
		companyResponse.setTotal(result.getTotal());
		
		return companyResponse;
	}

	@Override
	public Class<CompanyListRequest> getActionType() {
		return CompanyListRequest.class;
	}

	@Override
	public void rollback(CompanyListRequest request,
			CompanyListResponse response, ExecutionContext context)
			throws DispatchException {
	}

}
