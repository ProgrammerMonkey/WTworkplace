package com.wt.bos.service.decidedzone;

import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wt.bos.dao.decidedzone.DecidedZoneDao;
import com.wt.bos.dao.staff.StaffDao;
import com.wt.bos.dao.subarea.SubareaDao;
import com.wt.bos.domain.bc.DecidedZone;
import com.wt.bos.service.staff.impl.StaffServiceImpl;
import com.wt.mavencrm.domain.Customer;
@Service("decidedZoneService")
@Transactional
public class DecidedZoneServiceImpl implements DecidedZoneService {
	static Logger logger = Logger.getLogger(StaffServiceImpl.class);
	@Autowired
	private DecidedZoneDao decidedZoneDao;
	
	@Autowired
	private SubareaDao subareaDao;
	
	@Override
	public void save(DecidedZone model, String[] ids) {
		DecidedZone decidedZone = decidedZoneDao.saveAndFlush(model);
		for (String id : ids) {
			subareaDao.subareaToDecidedZone(model,id);
		}
	}

	@Override
	public Page<DecidedZone> pageQuery(PageRequest pageRequest,
			Specification<DecidedZone> conditons) {
		Page<DecidedZone> page = decidedZoneDao.findAll(conditons, pageRequest);
		for (DecidedZone decidedZone : page.getContent()) {
			Hibernate.initialize(decidedZone.getSubareas());
			Hibernate.initialize(decidedZone.getStaff());
		}
		return page;
	}

	@Override
	public List<Customer> findnoassociationcustomers() {
		String url = ResourceBundle.getBundle("url").getString("url");
		List<Customer> list = (List<Customer>) WebClient.create(url).accept(MediaType.APPLICATION_JSON).getCollection(Customer.class);
		return list;
	}

	@Override
	public List<Customer> findassociationcustomers(String id) {
		String url = ResourceBundle.getBundle("url").getString("url");
		url = url + "/" + id;
		List<Customer> customers = (List<Customer>) WebClient.create(url).accept(MediaType.APPLICATION_JSON).getCollection(Customer.class);
		return customers;
	}

	@Override
	public void assigncustomerstodecidedzone(String id, String[] customerIds) {
		String url = ResourceBundle.getBundle("url").getString("url");
		String cids = "";
		if (customerIds != null && customerIds.length != 0) {
			StringBuffer sbBuffer = new StringBuffer();
			for (String cid : customerIds) {
				sbBuffer.append(cid).append(",");// 代码优化
			}
			// cids = sbBuffer.toString();// 优化掉代码
			cids = sbBuffer.substring(0, sbBuffer.length() - 1);// StringBuffer substring 截取方法完成
		} else {
			cids = "''";
		}
		url = url + "/" + cids + "/" + id;
		// 1,3,4/8a7e859e5948830501594883ab710000";
		WebClient.create(url).put(null);

	}

}
