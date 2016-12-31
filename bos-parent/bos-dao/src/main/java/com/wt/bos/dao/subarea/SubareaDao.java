package com.wt.bos.dao.subarea;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.wt.bos.domain.bc.DecidedZone;
import com.wt.bos.domain.bc.Subarea;

public interface SubareaDao extends JpaRepository<Subarea, String>,JpaSpecificationExecutor<Subarea>{
	
	@Query("from Subarea where decidedZone is null")
	List<Subarea> findnosociation();
	@Modifying
	@Query("update Subarea set decidedZone = ?1 where id = ?2")
	void subareaToDecidedZone(DecidedZone model, String id);

}
