package jb.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jb.absx.F;
import jb.interceptors.TokenManage;
import jb.pageModel.DiveOrder;
import jb.pageModel.DiveShopCart;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.pageModel.SessionInfo;
import jb.service.DiveOrderDetailServiceI;
import jb.service.DiveOrderServiceI;
import jb.service.DiveShopCartServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 订单/购物车模块接口
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/apiOrderController")
public class ApiOrderController extends BaseController {
	
	
	@Autowired
	private TokenManage tokenManage;
		
	@Autowired
	private DiveShopCartServiceI diveShopCartService;
	
	@Autowired
	private DiveOrderServiceI diveOrderService;
	
	@Autowired
	private DiveOrderDetailServiceI diveOrderDetailService;
	
	/**
	 * 获取订单数量
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getOrderNumber")
	public Json getOrderNumber(HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			j.setObj(diveOrderService.getOrderNumber(s.getId()));
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	/**
	 * 购物车列表
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/shopCartList")
	public Json shopCartList(HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			List<DiveShopCart> shopCartList = diveShopCartService.findListByAccountId(s.getId());
			j.setObj(shopCartList);
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	/**
	 * 加入购物车
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addShopCart")
	public Json addShopCart(DiveShopCart diveShopCart, HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			diveShopCart.setAccountId(s.getId());
			diveShopCartService.addShopCart(diveShopCart);
			j.setMsg("加入购物车成功");
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	/**
	 * 修改数量
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/editNumber")
	public Json editNumber(DiveShopCart diveShopCart) {
		Json j = new Json();
		try{
			diveShopCartService.edit(diveShopCart);
			j.setMsg("数量变更成功");
			j.success();
		}catch(Exception e){
			j.setMsg("数量变更失败");
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	/**
	 * 购物车删除
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delShopCart")
	public Json delShopCart(String id) {
		Json j = new Json();
		try{
			diveShopCartService.delete(id);
			j.setMsg("购物车删除成功");
			j.success();
		}catch(Exception e){
			j.setMsg("购物车删除失败");
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	/**
	 * 订单列表
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/orderList")
	public Json orderList(PageHelper ph, DiveOrder diveOrder, HttpServletRequest request) {
		Json j = new Json();
		try{
			ph.setSort("addtime");
			ph.setOrder("desc");
			SessionInfo s = getSessionInfo(request);
			diveOrder.setAccountId(s.getId());
			j.setObj(diveOrderService.dataGrid(diveOrder,ph));
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	/**
	 * 订单详情
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getOrderDetail")
	public Json getOrderDetail(DiveOrder diveOrder, HttpServletRequest request) {
		Json j = new Json();
		try{
			diveOrder = diveOrderService.get(diveOrder.getId());
			diveOrder.setDetail_list(diveOrderDetailService.getOrderDetail(diveOrder.getId()));
			j.setObj(diveOrder);
			j.setMsg("操作成功");
			j.success();
		}catch(Exception e){
			j.setMsg("操作失败");
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	/**
	 * 订单详情物品删除
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delDetail")
	public Json delDetail(String detailId) {
		Json j = new Json();
		try{
			diveOrderDetailService.delete(detailId);
			j.setMsg("订单详情物品删除成功");
			j.success();
		}catch(Exception e){
			j.setMsg("订单详情物品删除失败");
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	/**
	 * 订单创建
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/createOrder")
	public Json createOrder(String cardIds, HttpServletRequest request) {
		Json j = new Json();
		try{
			if(!F.empty(cardIds)) {
				SessionInfo s = getSessionInfo(request);
				String orderId = diveOrderService.createOrder(cardIds, s.getId());
				j.setObj(diveOrderDetailService.getOrderDetail(orderId));
				j.setMsg("订单创建成功");
				j.success();
			} else {
				j.setMsg("请选择至少一件购物车物品");
				j.fail();
			}
		}catch(Exception e){
			j.setMsg("订单创建失败");
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	/**
	 * 订单取消
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/cancelOrder")
	public Json cancelOrder(String id, HttpServletRequest request) {
		Json j = new Json();
		try{
			DiveOrder diveOrder = new DiveOrder();
			diveOrder.setId(id);
			diveOrder.setOrderStatus("OS03");
			diveOrderService.edit(diveOrder);
			j.setMsg("订单取消成功");
			j.success();
		}catch(Exception e){
			j.setMsg("订单取消失败");
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	/**
	 * 支付成功
	 * @param ph
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/paySuccess")
	public Json paySuccess(DiveOrder diveOrder) {
		Json j = new Json();
		try{
			diveOrder.setOrderStatus("OS01"); // 已完成
			diveOrder.setPayStatus("PS01"); // 已支付
			diveOrder.setSendStatus("SS02"); // 待发货
			diveOrder.setPaytime(new Date());
			diveOrderService.edit(diveOrder);
			j.setMsg("操作成功");
			j.success();
		}catch(Exception e){
			j.setMsg("操作失败");
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}	
	
	private SessionInfo getSessionInfo(HttpServletRequest request){
		SessionInfo s = tokenManage.getSessionInfo(request);
		return s;		
	}
}
