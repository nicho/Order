package com.omdasoft.orderonline.gwt.order.server;

import java.util.Date;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.omdasoft.orderonline.util.DateUtil;

public class AutoGenerateRewardTask extends TimerTask {
	Logger logger = LoggerFactory.getLogger(this.getClass());


//	private RewardItemService rewardItemService;
	private static AutoGenerateRewardTask instance;


	private AutoGenerateRewardTask() {
	}

	public static AutoGenerateRewardTask getInstance() {
		if (instance == null) {
			instance = new AutoGenerateRewardTask();
		}

		return instance;
	}


	@Override
	public void run() {
		logger.info(" BEGIN to RUN AutoGenerateRewardTask ");
		Date now = DateUtil.getTime();
//		rewardItemService.runAutoRewardGeneratorBatch(now);
	}

//	public void setRewardItemService(RewardItemService rewardItemService) {
//		this.rewardItemService = rewardItemService;
//	}
}
