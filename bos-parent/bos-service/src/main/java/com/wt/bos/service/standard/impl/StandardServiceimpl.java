package com.wt.bos.service.standard.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wt.bos.dao.standard.StandardDao;
import com.wt.bos.domain.bc.Standard;
import com.wt.bos.domain.user.User;
import com.wt.bos.service.standard.StandardService;
@Service("standardService")
@Transactional
public class StandardServiceimpl implements StandardService {
@Autowired
private StandardDao standardDao;

	@Override
	public void save(Standard model) {
		// TODO Auto-generated method stub
		standardDao.save(model);
	}

	@Override
	public Page<Standard> pageQuery(PageRequest pageRequest) {
		Page<Standard> page = standardDao.findAll(pageRequest);
		return page;
	}

	@Override
	public void batchDel(String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			standardDao.delete(Integer.parseInt(arr[i]));
		}
	}

	@Override
	public List<Standard> ajaxList() {
		List<Standard> list = standardDao.findAll();
		return list;
	}


}
