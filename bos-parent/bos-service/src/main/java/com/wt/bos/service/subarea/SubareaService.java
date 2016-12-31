package com.wt.bos.service.subarea;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import com.wt.bos.domain.bc.Subarea;

public interface SubareaService {

	void save(Subarea model);

	Page<Subarea> pageQuery(PageRequest pageRequest);

	Page<Subarea> pageQuery(PageRequest pageRequest,
			Specification<Subarea> conditons);

	List<Subarea> findAll(
			Specification<Subarea> conditons);

	List<Subarea> findnosociation();

}
