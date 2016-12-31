package com.wt.bos.service.qp;

import com.wt.bos.domain.qp.NoticeBill;
import com.wt.mavencrm.domain.Customer;

public interface NoticeBillService {

	Customer findCustomerByTelephone(String telephone);

	void save(NoticeBill model, String province, String city, String district);

}
