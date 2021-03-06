package com.omdasoft.orderonline.model.vo;

import java.util.ArrayList;
import java.util.List;

import com.omdasoft.orderonline.model.common.PaginationDetail;
import com.omdasoft.orderonline.model.common.SortingDetail;

public class WinnersRecordQueryVo {
	private PaginationDetail paginationDetail;

	private SortingDetail sortingDetail;

	/**
	 * 搜索的内容（name, email, phone, cardNo）
	 */
	private String key;

	/**
	 * 部门id
	 */
	// private String deptId;

	/**
	 * 当传了一个部门后，先通过部门查处它的子部门
	 */
	private List<String> subDeptIds;

	private boolean includeSubDepts;

	// 此参数影响获奖次数，通过员工所获奖励的颁发部门进行过滤
	private List<String> managedDeptIds;


	private boolean filterRewardsParticipants = true;
	// 所有参与着得ID
	private List<String> orgIds = new ArrayList<String>();

	public boolean isFilterRewardsParticipants() {
		return filterRewardsParticipants;
	}

	public void setFilterRewardsParticipants(boolean filterRewardsParticipants) {
		this.filterRewardsParticipants = filterRewardsParticipants;
	}

	public PaginationDetail getPaginationDetail() {
		return paginationDetail;
	}

	public void setPaginationDetail(PaginationDetail paginationDetail) {
		this.paginationDetail = paginationDetail;
	}

	public SortingDetail getSortingDetail() {
		return sortingDetail;
	}

	public void setSortingDetail(SortingDetail sortingDetail) {
		this.sortingDetail = sortingDetail;
	}

	public List<String> getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(List<String> orgNos) {
		this.orgIds = orgNos;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	// public String getDeptId() {
	// return deptId;
	// }
	//
	// public void setDeptId(String deptId) {
	// this.deptId = deptId;
	// }

	public List<String> getSubDeptIds() {
		return subDeptIds;
	}

	public void setSubDeptIds(List<String> childrenIds) {
		this.subDeptIds = childrenIds;
	}

	/**
	 * @return the includeSubDepts
	 */
	public boolean isIncludeSubDepts() {
		return includeSubDepts;
	}

	/**
	 * @param includeSubDepts
	 *            the includeSubDepts to set
	 */
	public void setIncludeSubDepts(boolean includeSubDepts) {
		this.includeSubDepts = includeSubDepts;
	}

	/**
	 * 此参数影响获奖次数，通过员工所获奖励的颁发部门进行过滤
	 * 
	 * @return the managedDeptIds
	 */
	public List<String> getManagedDeptIds() {
		return managedDeptIds;
	}

	/**
	 * 此参数影响获奖次数，通过员工所获奖励的颁发部门进行过滤
	 * 
	 * @param managedDeptIds
	 *            the managedDeptIds to set
	 */
	public void setManagedDeptIds(List<String> managedDeptIds) {
		this.managedDeptIds = managedDeptIds;
	}

	

	@Override
	public String toString() {
		return "WinnersRecordQueryVo [paginationDetail=" + paginationDetail
				+ ", sortingDetail=" + sortingDetail + ", key=" + key
				+ ", subDeptIds=" + subDeptIds + ", includeSubDepts="
				+ includeSubDepts + ", managedDeptIds=" + managedDeptIds
				+ ", filterRewardsParticipants=" + filterRewardsParticipants
				+ ", orgIds=" + orgIds + "]";
	}

}
