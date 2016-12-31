package com.wt.bos.service.standard;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.wt.bos.domain.bc.Standard;
import com.wt.bos.domain.user.User;

public interface StandardService {

	void save(Standard model);

	Page<Standard> pageQuery(PageRequest pageRequest);

	void batchDel(String[] arr);

	List<Standard> ajaxList();
	
}
