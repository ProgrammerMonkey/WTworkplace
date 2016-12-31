package com.wt.bos.service.subarea;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wt.bos.dao.subarea.SubareaDao;
import com.wt.bos.domain.bc.Subarea;
@Service("subareaService")
@Transactional
public class SubareaServiceImpl implements SubareaService {
	static Logger logger = Logger.getLogger(SubareaServiceImpl.class);
	@Autowired
	private SubareaDao subareaDao;
	@Override
	public void save(Subarea model) {
		subareaDao.save(model);
	}
	@Override
	public Page<Subarea> pageQuery(PageRequest pageRequest) {
		Page<Subarea> page = subareaDao.findAll(pageRequest);
		for (Subarea subarea : page.getContent()) {
			Hibernate.initialize(subarea.getRegion());
		}
		return page;
	}
	@Override
	public Page<Subarea> pageQuery(PageRequest pageRequest,
			Specification<Subarea> conditons) {
		Page<Subarea> page = subareaDao.findAll(conditons, pageRequest);
		for (Subarea subarea : page.getContent()) {
			Hibernate.initialize(subarea.getRegion());
		}
		return page;
	}
	@Override
	public List<Subarea> findAll(
			Specification<Subarea> conditons) {
		List<Subarea> list = subareaDao.findAll(conditons);
		for (Subarea subarea : list) {
			Hibernate.initialize(subarea.getRegion());
		}
		return list;
	}
	@Override
	public List<Subarea> findnosociation() {
		List<Subarea> list = subareaDao.findnosociation();
		for (Subarea subarea : list) {
			Hibernate.initialize(subarea.getRegion());
		}
		return list;
	}

}
