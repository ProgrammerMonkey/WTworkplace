package com.wt.bos.dao.region;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.wt.bos.domain.bc.Region;

public interface RegionDao extends JpaRepository<Region, String>,JpaSpecificationExecutor<Region> {
	@Query("from Region where province like ?1 or city like ?1 or district like ?1")
	List<Region> findByProvinceOrCityOrDistrict(String parameter);
	@Query("select distinct province from Region")
	List<String> ajaxListProvience();
	@Query("select distinct city from Region where province = ?1")
	List<String> ajaxListCity(String parameter);
	@Query("select district from Region where city = ?1")
	List<String> ajaxListDistrict(String parameter);
	@Query("from Region where province = ?1 and city = ?2 and district = ?3")
	Region findRegionByProvinceAndCityAndDistrict(String province, String city,
			String district);
}
