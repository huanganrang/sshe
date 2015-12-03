package jb.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jb.pageModel.DataGrid;
import jb.pageModel.DiveAccount;
import jb.pageModel.DiveOrder;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.pageModel.SessionInfo;
import jb.service.DiveAccountServiceI;
import jb.service.DiveOrderDetailServiceI;
import jb.service.DiveOrderServiceI;
import jb.util.ConfigUtil;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * DiveOrder管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/diveOrderController")
public class DiveOrderController extends BaseController {

	@Autowired
	private DiveOrderServiceI diveOrderService;
	
	@Autowired
	private DiveOrderDetailServiceI diveOrderDetailService;
	
	@Autowired
	private DiveAccountServiceI accountService;


	/**
	 * 跳转到DiveOrder管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/diveorder/diveOrder";
	}

	/**
	 * 获取DiveOrder数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(DiveOrder diveOrder, PageHelper ph, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo)session.getAttribute(ConfigUtil.getSessionInfoName());
		if(checkRoleMark("RL01", sessionInfo)) {
			diveOrder.setAddUserId_travel(sessionInfo.getId());
		}
		if(checkRoleMark("RL03", sessionInfo)) {
			diveOrder.setAddUserId_equip(sessionInfo.getId());
		}
		
		return diveOrderService.dataGridComplex(diveOrder, ph);
	}
	/**
	 * 获取DiveOrder数据表格excel
	 * 
	 * @param user
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws IOException 
	 */
	@RequestMapping("/download")
	public void download(DiveOrder diveOrder, PageHelper ph,String downloadFields,HttpServletResponse response, HttpSession session) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
//		DataGrid dg = dataGrid(diveOrder,ph,session);		
//		downloadFields = downloadFields.replace("&quot;", "\"");
//		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
//		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
//		downloadTable(colums, dg, response);
		List<Map> lm = diveOrderService.queryImportData(diveOrder);
		String[] colums = {"订单号_orderNo", "商品号_rank","商品名称_name","商品类型_type","单价_price","数量_number","总价_totalAmount","商家名称_merchant",
				"下单人账号_userName","地址_address","快递公司_expressName","快递单号_expressNo","支付方式_payWay","备注_remark",
				"支付状态_payStatus","订单状态_orderStatus","支付时间_paytime","下单时间_addtime"};
		
		XSSFWorkbook xs=new XSSFWorkbook();
		XSSFSheet sheet=xs.createSheet("exprot data");
		XSSFRow row=sheet.createRow((short)0);
		short i = 0;
		for(String colum: colums){
			row.createCell(i).setCellValue(colum.split("_")[0]); 
			i++;
		}
		
		short j = 1;
		Object invObj = null;
		String fileName = "order";
		
		for(Map m : lm){					
			row = sheet.createRow(j);
			
			i = 0;
			for(String colum: colums){
				invObj=m.get(colum.split("_")[1]); 
				if(invObj==null)
					row.createCell(i).setCellValue("");
				else
					row.createCell(i).setCellValue(invObj.toString());	
	        	i++;
			}
			j++;
		}
		
		String mimetype = "application/x-msdownload";
		response.setContentType(mimetype);
		String downFileName = fileName+".xlsx";
		String inlineType = "attachment"; // 是否内联附件
		response.setHeader("Content-Disposition", inlineType
		    + ";filename=\"" + downFileName + "\"");
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			xs.write(out);
		} catch (IOException e) {		
			e.printStackTrace();
		} finally {			
			if (out != null)
				out.flush();
			if (out != null)
				out.close();			
		}	
	}
	/**
	 * 跳转到添加DiveOrder页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		DiveOrder diveOrder = new DiveOrder();
		diveOrder.setId(UUID.randomUUID().toString());
		return "/diveorder/diveOrderAdd";
	}

	/**
	 * 添加DiveOrder
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(DiveOrder diveOrder) {
		Json j = new Json();		
		diveOrderService.add(diveOrder);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到DiveOrder查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		DiveOrder diveOrder = diveOrderService.get(id);
		if(diveOrder != null) {
			DiveAccount account = accountService.get(diveOrder.getAccountId());
			diveOrder.setUserName(account.getUserName());
			diveOrder.setNickname(account.getNickname());
			
			diveOrder.setDetail_list(diveOrderDetailService.getOrderDetail(id));
		}
		request.setAttribute("diveOrder", diveOrder);
		return "/diveorder/diveOrderView";
	}

	/**
	 * 跳转到DiveOrder修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		DiveOrder diveOrder = diveOrderService.get(id);
		request.setAttribute("diveOrder", diveOrder);
		return "/diveorder/diveOrderEdit";
	}

	/**
	 * 修改DiveOrder
	 * 
	 * @param diveOrder
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(DiveOrder diveOrder) {
		Json j = new Json();		
		diveOrderService.edit(diveOrder);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除DiveOrder
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		diveOrderService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
