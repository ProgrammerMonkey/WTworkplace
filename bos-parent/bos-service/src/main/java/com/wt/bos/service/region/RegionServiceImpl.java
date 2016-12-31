package com.wt.bos.service.region;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wt.bos.dao.region.RegionDao;
import com.wt.bos.domain.bc.Region;
import com.wt.bos.domain.bc.Staff;
@Transactional
@Service("regionService")
public class RegionServiceImpl implements RegionService {
	@Autowired
	private RegionDao regionDao;
	@Override
	public void save(List<Region> list) {
		regionDao.save(list);
	}
	@Override
	public void save(Region model) {
		regionDao.save(model);
	}
	@Override
	public Region findById(String id) {
		return regionDao.findOne(id);
	}
	@Override
	public Page<Region> pageQuery(PageRequest pageRequest,
			Specification<Region> specification) {
		Page<Region> list = regionDao.findAll(specification, pageRequest);
		return list;
	}
	@Override
	public List<Region> findAll() {
		
		return regionDao.findAll();
	}
	@Override
	public List<Region> findAll(String parameter) {
		if (StringUtils.isNotBlank(parameter)) {
			return regionDao.findByProvinceOrCityOrDistrict("%" + parameter
					+ "%");
		} else {
			return findAll();
		}
	}
	@Override
	public List<String> ajaxListProvience() {
		
		return regionDao.ajaxListProvience();
	}
	@Override
	public List<String> ajaxListCity(String parameter) {
		return regionDao.ajaxListCity(parameter);
	}
	@Override
	public List<String> ajaxListDistrict(String parameter) {
		
		return regionDao.ajaxListDistrict(parameter);
	}

}
