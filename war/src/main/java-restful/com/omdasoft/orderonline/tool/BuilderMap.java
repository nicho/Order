package com.omdasoft.orderonline.tool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.omdasoft.orderonline.gwt.order.util.StringUtil;
import com.omdasoft.orderonline.model.order.OrderModel;

public class BuilderMap {

	public static Map<String,Object> builderMap(String flag,String exception_code,String exception_msg,List<OrderModel> date)
	{
		Map<String,Object> map=new HashMap<String, Object>();
		if(!StringUtil.isEmpty(flag))
		{
			map.put("flag",flag);
		}
		if(!StringUtil.isEmpty(exception_code))
		{
			map.put("exception_code",exception_code);
		}
		if(!StringUtil.isEmpty(exception_msg))
		{
			map.put("exception_msg",exception_msg);
		}
		if(date!=null && date.size()>0)
		{
			map.put("date",date);
		}
		return map;
	}
}
