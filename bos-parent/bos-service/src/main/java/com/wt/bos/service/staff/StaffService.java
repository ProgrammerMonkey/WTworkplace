package com.wt.bos.service.staff;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import com.wt.bos.domain.bc.Staff;
import com.wt.bos.domain.bc.Standard;

public interface StaffService {

	Staff findStaffByTelephone(String telephone);

	void save(Staff model);

	Page<Staff> pageQuery(PageRequest pageRequest);

	Page<Staff> pageQuery(PageRequest pageRequest,
			Specification<Staff> specification);

	void bathDel(String[] ids);

	List<Staff> ajaxList();

	
	
}
