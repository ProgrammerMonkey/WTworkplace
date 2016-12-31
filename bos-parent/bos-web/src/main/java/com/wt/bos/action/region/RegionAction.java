package com.wt.bos.action.region;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectOutputStream.PutField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.wt.bos.action.BaseAction;
import com.wt.bos.domain.bc.Region;
import com.wt.bos.utils.PinYin4jUtils;

@Controller
@ParentPackage("mavenbos")
@Namespace("/")
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {
	static Logger logger = Logger.getLogger(RegionAction.class);
	private File upload;

	public void setUpload(File upload) {
		this.upload = upload;
	}

	@Action(value = "regionAction_oneclickupload", results = { @Result(name = "oneclickupload", type = "json") })
	public String oneclickupload() throws Exception {
		try {
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(upload));
			HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
			List<Region> list = new ArrayList<Region>();
			
			for (Row row : sheet) {
				if(row.getRowNum()==0){
					continue;
				}
				Region region = new Region();
				region.setId(row.getCell(0).getStringCellValue());
				region.setProvince(row.getCell(1).getStringCellValue());
				region.setCity(row.getCell(2).getStringCellValue());
				region.setDistrict(row.getCell(3).getStringCellValue());
				region.setPostcode(row.getCell(4).getStringCellValue());
				String shortCode = getShortCode(region.getProvince(),region.getCity(),region.getDistrict());
				String cityCode = getCityCode(region.getCity());
				region.setShortcode(shortCode);
				region.setCitycode(cityCode);
				list.add(region);
			}
			facadeService.getRegionService().save(list);
			push(true);
		} catch (Exception e) {
			e.printStackTrace();
			push(false);
		}
		
		return "oneclickupload";
	}

	private String getCityCode(String city) {
		city=city.substring(0, city.length()-1);
		String pinyin = PinYin4jUtils.hanziToPinyin(city,"");
		return pinyin;
	}

	private String getShortCode(String province, String city, String district) {
		StringBuffer sb = new StringBuffer();
		province=province.substring(0, province.length()-1);
		city=city.substring(0, city.length()-1);
		district=district.substring(0, district.length()-1);
		String[] str = PinYin4jUtils.getHeadByString(province+city+district);
		for (String string : str) {
			sb.append(string);
		}
		return sb.toString();
	}
	@Action(value = "regionAction_save", results = { @Result(name = "save", location = "/WEB-INF/pages/base/region.jsp") })
	public String save() throws Exception {
		String shortCode = getShortCode(model.getProvince(),model.getCity(),model.getDistrict());
		String cityCode = getCityCode(model.getCity());
		model.setShortcode(shortCode);
		model.setCitycode(cityCode);
		facadeService.getRegionService().save(model);
		return "save";
	}
	@Action(value = "regionAction_validId", results = { @Result(name = "validId", type="json") })
	public String validId() throws Exception {
		Region region = facadeService.getRegionService().findById(model.getId());
		if(region!=null){
			push(false);
		}else{
			push(true);
		}
		return "validId";
	}
	@Action(value = "regionAction_ajaxNameList", results = { @Result(name = "ajaxNameList", type="json") })
	public String ajaxNameList() throws Exception {
		try {
			String parameter = getParameter("q");
			List<Region> list =facadeService.getRegionService().findAll(parameter);
			push(list);
		} catch (Exception e) {
			logger.error("查询失败");
			e.printStackTrace();
		}
		
		return "ajaxNameList";
	}
	@Action(value = "regionAction_ajaxListProvience", results = { @Result(name = "ajaxListProvience", type="json") })
	public String ajaxListProvience() throws Exception {
		List<String> list = facadeService.getRegionService().ajaxListProvience();
		push(list);
		return "ajaxListProvience";
	}
	@Action(value = "regionAction_ajaxListCity", results = { @Result(name = "ajaxListCity", type="json") })
	public String ajaxListCity() throws Exception {
		String parameter = getParameter("province");
		List<String> list = facadeService.getRegionService().ajaxListCity(parameter);
		push(list);
		return "ajaxListCity";
	}
	@Action(value = "regionAction_ajaxListDistrict", results = { @Result(name = "ajaxListDistrict", type="json") })
	public String ajaxListDistrict() throws Exception {
		String parameter = getParameter("city");
		List<String> list = facadeService.getRegionService().ajaxListDistrict(parameter);
		push(list);
		return "ajaxListDistrict";
	}
	@Action(value = "regionAction_pageQuery")
	public String pageQuery() throws Exception {
		Specification<Region> specification = getConditons();
		
		String paramSort = getParameter("sort");
		String paramOrder = getParameter("order");
		
		if (StringUtils.isNotBlank(paramOrder)
				&& StringUtils.isNotBlank(paramSort)) {
			Direction direction = Direction.fromString(paramOrder);
			Sort sort = new Sort(direction, paramSort);
			pageRequest = getPageRequest(sort);
		} else {
			pageRequest = getPageRequest();
		}
		
		Page<Region> responsePage = facadeService.getRegionService().pageQuery(
				pageRequest,specification);
		setPageData(responsePage);
		
		
		return "pageQuery";
	}

	private Specification<Region> getConditons() {
		return new Specification<Region>() {
			@Override
			public Predicate toPredicate(Root<Region> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				
				ArrayList<Predicate> list = new ArrayList<Predicate>();
				if(StringUtils.isNotBlank(model.getProvince())){
					Predicate p1 = cb.like(root.get("province").as(String.class), "%"+model.getProvince()+"%");
					list.add(p1);
				}
				if(StringUtils.isNotBlank(model.getCity())){
					Predicate p2 = cb.equal(root.get("city").as(String.class), model.getCity());
					list.add(p2);
				}
				if(StringUtils.isNotBlank(model.getDistrict())){
					Predicate p3 = cb.equal(root.get("district").as(String.class), model.getDistrict());
					list.add(p3);
				}
				Predicate[] predicates = new Predicate[list.size()];
				return cb.and(list.toArray(predicates));//条件用and连接
			}
		};
	}
}
