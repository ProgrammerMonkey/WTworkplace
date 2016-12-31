package com.wt.bos.service.staff.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wt.bos.dao.staff.StaffDao;
import com.wt.bos.domain.bc.Staff;
import com.wt.bos.domain.bc.Standard;
import com.wt.bos.service.staff.StaffService;
@Service("staffService")
@Transactional
public class StaffServiceImpl implements StaffService {
	static Logger logger = Logger.getLogger(StaffServiceImpl.class);
	@Autowired
	private StaffDao staffDao;
	@Override
	public Staff findStaffByTelephone(String telephone) {
		Staff s ;
		List<Staff> list = staffDao.findStaffByTelephone(telephone);
		if(list.size()==0||list==null){
			s=null;
		}else{
		s= list.get(0);
		}return s;
	}
	@Override
	public void save(Staff model) {
		staffDao.save(model);
	}
	@Override
	public Page<Staff> pageQuery(PageRequest pageRequest) {
		Page<Staff> list = staffDao.findAll(pageRequest);
		return list;
	}
	@Override
	public Page<Staff> pageQuery(PageRequest pageRequest,
			Specification<Staff> specification) {
		
		return staffDao.findAll(specification, pageRequest);
	}
	@Override
	public void bathDel(String[] ids) {
		for (String string : ids) {
			try {
				staffDao.bathDel(string);
				logger.info("批量删除中...id为："+string);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e+"批量删除错误，id为："+string);
				throw new RuntimeException(e);
			}
		}
	}
	@Override
	public List<Staff> ajaxList() {
		
		return staffDao.findOnLine();
	}

}
