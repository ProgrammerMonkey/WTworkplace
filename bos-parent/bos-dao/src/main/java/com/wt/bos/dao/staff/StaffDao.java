package com.wt.bos.dao.staff;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.wt.bos.domain.bc.Staff;

public interface StaffDao extends JpaRepository<Staff, String>,JpaSpecificationExecutor<Staff> {
	@Query("from Staff where telephone = ?1")
	List<Staff> findStaffByTelephone(String telephone);
	@Modifying
	@Query("update Staff set deltag = '1' where id = ?")
	void bathDel(String string);
	@Query("from Staff where deltag = '0'")
	List<Staff> findOnLine();
}
