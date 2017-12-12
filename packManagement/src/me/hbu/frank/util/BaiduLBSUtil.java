package me.hbu.frank.util;

import java.util.Map;

public class BaiduLBSUtil {
	
	/**
	 * 返回数据的编码格式
	 */
	private static String recEncoding = "UTF-8";
	public static String getRecEncoding() {
		return recEncoding;
	}
	public static void setRecEncoding(String recEncoding) {
		BaiduLBSUtil.recEncoding = recEncoding;
	}
	
	@SuppressWarnings("unchecked")
	public String loginPlat(Map params){
		String url = "http://222.211.79.114:8181/pubPlatformRest/rest/user/login.do?";
		return HttpRequestProxy.doPost(url,params,getRecEncoding());
	}
	
	public String getDeviceInfo(Map params){
		String url = "http://222.211.79.114:8181/pubPlatformRest/rest/organ/searchLogicOrganNode.do?";
		return HttpRequestProxy.doPost(url,params,getRecEncoding());
	}
	@SuppressWarnings("unchecked")
	public String createGeotable(Map params){
		String url = "http://api.map.baidu.com/geodata/v3/geotable/create";
		return HttpRequestProxy.doPost(url, params, getRecEncoding());
	}
	@SuppressWarnings("unchecked")
	public String listGeotable(Map params){
		String url = "http://api.map.baidu.com/geodata/v3/geotable/list";
		return HttpRequestProxy.doGet(url, params , getRecEncoding());
	}
	@SuppressWarnings("unchecked")
	public String detailGeotable(Map params){
		String url = "http://api.map.baidu.com/geodata/v3/geotable/detail";
		return HttpRequestProxy.doGet(url,params,getRecEncoding());
	}
	@SuppressWarnings("unchecked")
	public String updateGeotable(Map params){
		String url = "http://api.map.baidu.com/geodata/v3/geotable/update";
		return HttpRequestProxy.doPost(url,params,getRecEncoding());
	}
	@SuppressWarnings("unchecked")
	public String deleteGeotable(Map params){
		//注：当geotable里面没有有效数据时，才能删除geotable
		String url = "http://api.map.baidu.com/geodata/v3/geotable/delete";
		return HttpRequestProxy.doPost(url,params,getRecEncoding());
	}
	@SuppressWarnings("unchecked")
	public String createColumn(Map params){
		String url = "http://api.map.baidu.com/geodata/v3/column/create";
		return HttpRequestProxy.doPost(url,params,getRecEncoding());
	}
	@SuppressWarnings("unchecked")
	public String listColumn(Map params){
		String url = "http://api.map.baidu.com/geodata/v3/column/list";
		return HttpRequestProxy.doGet(url,params,getRecEncoding());
	}
	@SuppressWarnings("unchecked")
	public String detailColumn(Map params){
		String url = "http://api.map.baidu.com/geodata/v3/column/detail";
		return HttpRequestProxy.doGet(url,params,getRecEncoding());
	}
	@SuppressWarnings("unchecked")
	public String updateColumn(Map params){
		String url = "http://api.map.baidu.com/geodata/v3/column/update";
		return HttpRequestProxy.doPost(url,params,getRecEncoding());
	}
	@SuppressWarnings("unchecked")
	public String deleteColumn(Map params){
		String url = "http://api.map.baidu.com/geodata/v3/column/delete";
		return HttpRequestProxy.doPost(url,params,getRecEncoding());
	}
	@SuppressWarnings("unchecked")
	public String createPOI(Map params){
		String url = "http://api.map.baidu.com/geodata/v3/poi/create";
		return HttpRequestProxy.doPost(url,params,getRecEncoding());
	}
	@SuppressWarnings("unchecked")
	public String listPOI(Map params){
		String url = "http://api.map.baidu.com/geodata/v3/poi/list";
		return HttpRequestProxy.doGet(url,params,getRecEncoding());
	}
	@SuppressWarnings("unchecked")
	public String detailPOI(Map params){
		String url = "http://api.map.baidu.com/geodata/v3/poi/detail";
		return HttpRequestProxy.doGet(url,params,getRecEncoding());
	}
	@SuppressWarnings("unchecked")
	public String updatePOI(Map params){
		String url = "http://api.map.baidu.com/geodata/v3/poi/update";
		return HttpRequestProxy.doGet(url,params,getRecEncoding());
	}
	@SuppressWarnings("unchecked")
	public String deletePOI(Map params){
		String url = "http://api.map.baidu.com/geodata/v3/poi/delete";
		return HttpRequestProxy.doPost(url,params,getRecEncoding());
	}
	@SuppressWarnings("unchecked")
	public String postPOIsCSVFile(Map params){
		String url = "http://api.map.baidu.com/geodata/v3/poi/upload";
		return HttpRequestProxy.doPost(url,params,getRecEncoding());
	}
	@SuppressWarnings("unchecked")
	public String listImportStatus(Map params){
		String url = "http://api.map.baidu.com/geodata/v3/job/listimportdata";
		return HttpRequestProxy.doGet(url,params,getRecEncoding());
	}
	@SuppressWarnings("unchecked")
	public String listJob(Map params){
		String url = "http://api.map.baidu.com/geodata/v3/job/list";
		return HttpRequestProxy.doGet(url,params,getRecEncoding());
	}
	//http://api.map.baidu.com/geosearch/v3/local?ak=您的ak&geotable_id=****&region=北京&tags=酒店&sortby=distance:1|price:1 &filter=price:200,300s
	@SuppressWarnings("unchecked")
	public String getlocaldetail(Map params){
		String url = "http://api.map.baidu.com/geosearch/v3/local";
		return HttpRequestProxy.doPost(url,params,getRecEncoding());
	}
	
	//http://api.map.baidu.com/geosearch/v3/local?ak=您的ak&geotable_id=****&region=北京&tags=酒店&sortby=distance:1|price:1 &filter=price:200,300s
		@SuppressWarnings("unchecked")
		public String getNearBydetail(Map params){
			String url = "http://api.map.baidu.com/geosearch/v3/nearby";
			return HttpRequestProxy.doGet(url,params,getRecEncoding());
		}
	
	
}
