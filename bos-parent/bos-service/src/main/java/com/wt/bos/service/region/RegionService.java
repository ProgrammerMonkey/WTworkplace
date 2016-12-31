package com.wt.bos.service.region;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import com.wt.bos.domain.bc.Region;
import com.wt.bos.domain.bc.Staff;

public interface RegionService {

	void save(List<Region> list);

	void save(Region model);

	Region findById(String id);

	Page<Region> pageQuery(PageRequest pageRequest,
			Specification<Region> specification);

	List<Region> findAll();

	List<Region> findAll(String parameter);

	List<String> ajaxListProvience();

	List<String> ajaxListCity(String parameter);

	List<String> ajaxListDistrict(String parameter);

}
