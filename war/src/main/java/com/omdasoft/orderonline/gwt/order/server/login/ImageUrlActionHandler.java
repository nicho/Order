package com.omdasoft.orderonline.gwt.order.server.login;

import java.io.IOException;
import java.util.Properties;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.inject.Inject;
import com.omdasoft.orderonline.gwt.order.client.core.request.ImageUrlInitRequest;
import com.omdasoft.orderonline.gwt.order.client.core.request.ImageUrlInitResponse;
import com.omdasoft.orderonline.gwt.order.server.BaseActionHandler;
import com.omdasoft.orderonline.gwt.order.util.StringUtil;
import com.omdasoft.orderonline.service.org.CorporationService;

public class ImageUrlActionHandler extends
		BaseActionHandler<ImageUrlInitRequest, ImageUrlInitResponse> {
	CorporationService corporationService;
	@Inject
	public ImageUrlActionHandler(CorporationService corporationService) {
		this.corporationService = corporationService;

	}

	public ImageUrlActionHandler() {

	}

	@Override
	public Class<ImageUrlInitRequest> getActionType() {
		return ImageUrlInitRequest.class;
	}

	@Override
	public ImageUrlInitResponse execute(ImageUrlInitRequest action,
			ExecutionContext context) throws DispatchException {
		ImageUrlInitResponse resp = new ImageUrlInitResponse();
		Properties properties = new Properties();
		try {
			properties.load(ImageUrlActionHandler.class
					.getResourceAsStream("configuration.properties"));
			resp.setUrl(properties.getProperty("imageUrl"));
			resp.setJbossname(properties.getProperty("jbossName"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(!StringUtil.isEmpty(action.getCid()))
		{
			String corpId=corporationService.findCorporationBycId(action.getCid());
			if(corpId!=null)
				resp.setCorpId(corpId);
		}
		return resp;

	}

	@Override
	public void rollback(ImageUrlInitRequest action,
			ImageUrlInitResponse result, ExecutionContext context)
			throws DispatchException {

	}

}
