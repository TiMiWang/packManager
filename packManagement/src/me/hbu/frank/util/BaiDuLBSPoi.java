package me.hbu.frank.util;

import java.util.HashMap;
import java.util.Map;

//import com.internet.cms.controller.ShopController;

public class BaiDuLBSPoi {
	private static String ak = "pqu7UIPh8922kaSLmL6frxna";
	private static String geotable_id = "125172";	
//	private static String geotable_id = "119234";	
//	private static String geotable_id = "118168";	
	
	private static BaiduLBSUtil baiduLBSUtil = new BaiduLBSUtil();
	public static String getAk() {
		return ak;
	}
	public static void setAk(String ak) {
		BaiDuLBSPoi.ak = ak;
	}
	public static String getGeotable_id() {
		return geotable_id;
	}
	public static void setGeotable_id(String geotableId) {
		geotable_id = geotableId;
	}
	
	@SuppressWarnings("unchecked")
	public void createPOI(String lat,String longit){
		Map params = new HashMap();
		params.put("ak", ak);
		params.put("geotable_id",geotable_id);
		params.put("title", "大合仓");
		params.put("address", "四川省成都市青羊区草堂路");
		//纬度
		params.put("latitude",lat );
		//经度,
		params.put("longitude", longit);
		params.put("tags", "4s");
		params.put("coord_type", "3");
		//自定义列,30.616752
		params.put("tedi", "tt");
		params.put("phone", "132568956565");
		params.put("mendianid", "19");
		
		System.out.println(baiduLBSUtil.createPOI(params));
	}
	
	@SuppressWarnings("unchecked")
	public static void createPOI1(String id,String shopName,String shopAddr,String wei,
			String jing,String phone,String url,String cartypes,String sortvip){
		Map params = new HashMap();
		params.put("ak", ak);
		params.put("geotable_id",geotable_id);
		params.put("title", shopName);
		params.put("address", shopAddr);
		//纬度
		params.put("latitude",wei );
		//经度,
		params.put("longitude", jing);
		params.put("tags", cartypes);
		params.put("coord_type", "3");
		//自定义列,30.616752
		params.put("tedi", "4s");
		params.put("phone", phone);
		params.put("mendianid",id);
		params.put("jibie","20");
		params.put("image",url);
		
		params.put("carTypes",cartypes);
		params.put("sortVip",sortvip);
		baiduLBSUtil.createPOI(params);
	}
	
	public static void deletePOI(String name,String id){
		Map<String ,String > params = new HashMap<String, String>();
		params.put("ak", ak);
		params.put("geotable_id", geotable_id);
		params.put("title", name);
		params.put("mendianid",id);
		baiduLBSUtil.deletePOI(params);
	}
	public static String listPOI(){
		Map params = new HashMap();
		params.put("ak", ak);
		params.put("geotable_id",geotable_id);
		return baiduLBSUtil.listPOI(params);
	}
	public static void updatePOI(String id,String shopName,String shopAddr,String wei,
			String jing,String phone,String url){
		Map<String ,String > params = new HashMap<String, String>();
		params.put("ak", ak);
		params.put("geotable_id", geotable_id);
		params.put("title", shopName);
		params.put("address", shopAddr);
		//纬度
		params.put("latitude",wei );
		//经度,
		params.put("longitude", jing);
		params.put("tags", "4s");
		params.put("coord_type", "3");
		//自定义列,30.616752
		params.put("tedi", "4s");
		params.put("phone", phone);
		params.put("mendianid",id);
		params.put("jibie","20");
		params.put("image",url);
		baiduLBSUtil.updatePOI(params);
	}
	
}
