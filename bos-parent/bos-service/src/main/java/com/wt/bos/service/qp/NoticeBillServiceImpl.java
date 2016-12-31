package com.wt.bos.service.qp;

import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wt.bos.dao.decidedzone.DecidedZoneDao;
import com.wt.bos.dao.qp.NoticeBillDao;
import com.wt.bos.dao.qp.WorkBillDao;
import com.wt.bos.dao.region.RegionDao;
import com.wt.bos.dao.staff.StaffDao;
import com.wt.bos.domain.bc.DecidedZone;
import com.wt.bos.domain.bc.Region;
import com.wt.bos.domain.bc.Staff;
import com.wt.bos.domain.bc.Subarea;
import com.wt.bos.domain.qp.NoticeBill;
import com.wt.bos.domain.qp.WorkBill;
import com.wt.mavencrm.domain.Customer;
@Transactional
@Service("noticeBillService")
public class NoticeBillServiceImpl implements NoticeBillService {
	@Autowired
	private NoticeBillDao noticeBillDao;
	@Autowired
	private DecidedZoneDao decidedZoneDao;
	@Autowired
	private RegionDao regionDao;
	@Autowired
	private WorkBillDao workBillDao;
	
	
	@Override
	public Customer findCustomerByTelephone(String telephone) {
		String url = ResourceBundle.getBundle("url").getString("url");
		// /customer/tel/{tel}
		url = url+"/tel/"+telephone;
		Customer customer = WebClient.create(url).accept(MediaType.APPLICATION_JSON).get(Customer.class);
		return customer;
	}
	@Override
	public void save(NoticeBill model, String province, String city,
			String district) {
		//Crm系统返回添加客户信息的对象实体类-  mavenbos接受客户端信息 获取添加客户端的int主键!
		//1.根据客户电话查询客户信息  如果客户不存在 Bos 录入客户信息到crm  
		Customer crmCustomer = findCustomerByTelephone(model.getTelephone());
		if(crmCustomer==null){
			String url = ResourceBundle.getBundle("url").getString("url");
			url = url + "/save";
			crmCustomer = new Customer();
			crmCustomer.setAddress(model.getPickaddress());
			crmCustomer.setDecidedzoneId(null);
			crmCustomer.setName(model.getCustomerName());
			crmCustomer.setStation(model.getStation());
			crmCustomer.setTelephone(model.getTelephone());
			Response response = WebClient.create(url).accept(MediaType.APPLICATION_JSON).post(crmCustomer);
			Customer customer = response.readEntity(Customer.class);
			model.setCustomerId(customer.getId()+"");
		}else{
			//.如果客户信息存在  判断客户地址是否一致 
			//.如果不一致 更新crm 地址库信息
			model.setCustomerId(crmCustomer.getId()+"");
			model.setCustomerName(crmCustomer.getName());
		}
//		2.业务通知单的保存
		model = noticeBillDao.saveAndFlush(model);
		//3.自动分单，需要完成业务通知单，去派员分配以及分配类型的设置：人工/自动
		//3.1完全匹配地址  /customer/addr/{addr}
		String url = ResourceBundle.getBundle("url").getString("url");
		url= url +"/addr/"+ model.getPickaddress();
		Customer customerAddress = WebClient.create(url).accept(MediaType.APPLICATION_JSON).get(Customer.class);
		if(customerAddress!=null){
			String decidedzoneId = customerAddress.getDecidedzoneId();
			if(StringUtils.isNotBlank(decidedzoneId)){
				DecidedZone zone = decidedZoneDao.findOne(decidedzoneId);
				Staff staff = zone.getStaff();
				model.setStaff(staff);
				model.setOrdertype("自动");
				//工单生成
				generateWorkBill(model, staff);
				return;
			}
		}
//		3.2 管理分区匹配法，region中的省市县，匹配关键字 region-->subarea-->关键字-->subarea-->decidedZone-->staff
		Region region =regionDao.findRegionByProvinceAndCityAndDistrict(province,city,district);
		
		Set<Subarea> subareas = region.getSubareas();
		for (Subarea subarea : subareas) {
			if(model.getPickaddress().contains(subarea.getAddresskey())){
				DecidedZone decidedZone = subarea.getDecidedZone();
				if(decidedZone !=null){
					//允许添加定区，不关联分区
					Staff staff = decidedZone.getStaff();
					model.setStaff(staff);
					model.setOrdertype("自动");
					generateWorkBill(model, staff);
					//更新远程客户端的地址
					String url1 = ResourceBundle.getBundle("url").getString("url");
//					/customer/update/{addr}/{cid}
					url1= url1 +"/update/"+ model.getPickaddress()+"/"+model.getCustomerId();
					WebClient.create(url).put(null);
					return;
				}
			}
			model.setOrdertype("人工");
		}
		
		
		
	}
	private void generateWorkBill(NoticeBill model, Staff staff) {
		WorkBill workBill = new WorkBill();
		workBill.setAttachbilltimes(0);
		workBill.setBuildtime(new Timestamp(System.currentTimeMillis()));
		workBill.setNoticeBill(model);
		workBill.setPickstate("新单");
		workBill.setRemark(model.getRemark());
		workBill.setStaff(staff);
		workBill.setType("新");
		workBillDao.save(workBill);
	}

}
