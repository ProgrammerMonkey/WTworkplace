package com.wt.bos.dao.qp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wt.bos.domain.qp.WorkBill;

public interface WorkBillDao extends JpaRepository<WorkBill, String>,JpaSpecificationExecutor<WorkBill>{

}
