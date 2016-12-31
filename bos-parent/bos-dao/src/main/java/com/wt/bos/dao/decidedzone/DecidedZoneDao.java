package com.wt.bos.dao.decidedzone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wt.bos.domain.bc.DecidedZone;

public interface DecidedZoneDao extends JpaRepository<DecidedZone, String>,JpaSpecificationExecutor<DecidedZone>{

}
