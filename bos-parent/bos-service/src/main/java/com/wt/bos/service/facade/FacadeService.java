package com.wt.bos.service.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.bos.service.UserService;
import com.wt.bos.service.decidedzone.DecidedZoneService;
import com.wt.bos.service.qp.NoticeBillService;
import com.wt.bos.service.region.RegionService;
import com.wt.bos.service.staff.StaffService;
import com.wt.bos.service.standard.StandardService;
import com.wt.bos.service.subarea.SubareaService;

@Service
public class FacadeService {
	@Autowired
	private UserService userService;
	@Autowired
	public StandardService StandardService;
	@Autowired
	private StaffService staffService;
	@Autowired
	private RegionService regionService;
	@Autowired
	private SubareaService subareaService;
	@Autowired
	private DecidedZoneService decidedZoneService;
	@Autowired
	private NoticeBillService noticeBillService;
	

	public UserService getUserService() {
		return userService;
	}

	public StandardService getGetStandardService() {
		return StandardService;
	}

	public StaffService getStaffService() {
		// TODO Auto-generated method stub
		return staffService;
	}

	public RegionService getRegionService() {
		// TODO Auto-generated method stub
		return regionService;
	}

	public SubareaService getSubareaService() {
		// TODO Auto-generated method stub
		return subareaService;
	}

	public DecidedZoneService getDecidedZoneService() {
		// TODO Auto-generated method stub
		return decidedZoneService;
	}

	public NoticeBillService getNoticeBillService() {
		// TODO Auto-generated method stub
		return noticeBillService;
	}
	
	
}
