package com.wt.bos.action.subarea;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.wt.bos.action.BaseAction;
import com.wt.bos.domain.bc.DecidedZone;
import com.wt.bos.domain.bc.Region;
import com.wt.bos.domain.bc.Subarea;
import com.wt.bos.utils.MyFileUtils;

@Controller
@ParentPackage("mavenbos")
@Namespace("/")
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
	static Logger logger = Logger.getLogger(SubareaAction.class);
	
	@Action(value = "subareaAction_save", results = { @Result(name = "save", location = "/WEB-INF/pages/base/subarea.jsp")})
	public String save() throws Exception {
		facadeService.getSubareaService().save(model);
		return "save";
	}
	@Action(value = "subareaAction_findnosociation", results = { @Result(name = "findnosociation", type="json")})
	public String findnosociation() throws Exception {
		List<Subarea> list = facadeService.getSubareaService().findnosociation();
		push(list);
		return "findnosociation";
	}
	@Action(value = "subareaAction_pageQuery")
	public String pageQuery() throws Exception {
		Page<Subarea> pageResponse = facadeService.getSubareaService().pageQuery(getPageRequest(),getConditons());
		setPageData(pageResponse);
		return "pageQuery";
	}
	
	
	@Action(value = "subareaAction_download")
	public String download() throws Exception {
		List<Subarea> list = facadeService.getSubareaService().findAll(getConditons());
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("分区数据1");
		HSSFRow row = sheet.createRow(0);//第一行
		row.createCell(0).setCellValue("分区编号");
		row.createCell(1).setCellValue("省");
		row.createCell(2).setCellValue("市");
		row.createCell(3).setCellValue("区");
		row.createCell(4).setCellValue("关键字");
		row.createCell(5).setCellValue("起始号");
		row.createCell(6).setCellValue("详细位置");
		
		for (Subarea s : list) {
			int lastRowNum = sheet.getLastRowNum();
			HSSFRow newrow = sheet.createRow(lastRowNum + 1);
			newrow.createCell(0).setCellValue(s.getId());
			newrow.createCell(1).setCellValue(s.getRegion().getProvince());
			newrow.createCell(2).setCellValue(s.getRegion().getCity());
			newrow.createCell(3).setCellValue(s.getRegion().getDistrict());
			newrow.createCell(4).setCellValue(s.getAddresskey());
			newrow.createCell(5).setCellValue(s.getStartnum());
			newrow.createCell(6).setCellValue(s.getPosition());
		}
		// 下载 一个流 2 个头
		String filename = new Timestamp(System.currentTimeMillis())+"_wt.xls";
		String mimeType = ServletActionContext.getServletContext().getMimeType(filename);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType(mimeType);
		// 2头 附件头 附件名称乱码 由于不同浏览器 决定 ff base64 解码 ie/chrome urlencoder 解码
		response.setHeader("Content-Disposition", "attachment;filename="+MyFileUtils.encodeDownloadFilename(filename, getRequest().getHeader("user-agent")));
		
		try {
			workbook.write(response.getOutputStream());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NONE;
	}
	//条件的封装
	private Specification<Subarea> getConditons() {
		return new Specification<Subarea>() {
			@Override
			public Predicate toPredicate(Root<Subarea> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				
				ArrayList<Predicate> list = new ArrayList<Predicate>();
				if(StringUtils.isNotBlank(model.getAddresskey())){
					Predicate p1 = cb.equal(root.get("addresskey").as(String.class), model.getAddresskey());
					list.add(p1);
				}
				// 连接区域表
				if(model.getRegion()!=null){
					Join<Subarea, Region> regionJoin = root.join(root.getModel().getSingularAttribute("region", Region.class),JoinType.LEFT);
					if (StringUtils.isNotBlank(model.getRegion().getProvince())) {
						Predicate c2 = cb.like(regionJoin.get("province").as(String.class), "%"+model.getRegion().getProvince()+"%");
						list.add(c2);
					}
					if (StringUtils.isNotBlank(model.getRegion().getCity())) {
						Predicate c3 = cb.like(regionJoin.get("city").as(String.class), "%"+model.getRegion().getCity()+"%");
						list.add(c3);
					}
					if (StringUtils.isNotBlank(model.getRegion().getDistrict())) {
						Predicate c4 = cb.like(regionJoin.get("district").as(String.class), "%"+model.getRegion().getDistrict()+"%");
						list.add(c4);
					}
				}
				
				// 连接 定区表 root
				if(model.getDecidedZone()!=null&&StringUtils.isNotBlank(model.getDecidedZone().getId())){
					// 查询定区 编码 oid查询 比较oid 就是比较对象
					Predicate c5 = cb.equal(root.get("decidedZone").as(DecidedZone.class), model.getDecidedZone());
					list.add(c5);
				}
					
				
				Predicate[] predicates = new Predicate[list.size()];
				return cb.and(list.toArray(predicates));//条件用and连接
			}
		};
	}
}
