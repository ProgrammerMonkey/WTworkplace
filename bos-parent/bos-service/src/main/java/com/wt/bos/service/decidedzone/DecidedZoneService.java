package com.wt.bos.service.decidedzone;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import com.wt.bos.domain.bc.DecidedZone;
import com.wt.mavencrm.domain.Customer;

public interface DecidedZoneService {

	void save(DecidedZone model, String[] ids);

	Page<DecidedZone> pageQuery(PageRequest pageRequest,
			Specification<DecidedZone> conditons);

	List<Customer> findnoassociationcustomers();

	List<Customer> findassociationcustomers(String id);

	void assigncustomerstodecidedzone(String id, String[] customerIds);

	
}
