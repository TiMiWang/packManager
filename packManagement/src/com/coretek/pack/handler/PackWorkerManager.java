package com.coretek.pack.handler;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.coretek.pack.model.PackMode;
import com.coretek.pack.model.Person;
import com.coretek.pack.service.IPackModeService;

public class PackWorkerManager implements IPackWorkerManager{
	
	public static String packUtilsPath = "F:/dsp/dabao/packUtils";

	private static PackWorkerManager packworkermanager = null;
	
	
	private Map<Integer,IPackWorker> packwokerMap = new ConcurrentHashMap<Integer, IPackWorker>();
	
	private ExecutorService executor = Executors.newCachedThreadPool();
	
	public static PackWorkerManager getInstance()
	{
		if(packworkermanager ==null){
			packworkermanager = new PackWorkerManager();
		}
		return packworkermanager;
		
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public IPackWorker getPackWorker(Integer id){
		if(packwokerMap.containsKey(id)){
			return packwokerMap.get(id);
		}
		return null;
	}
	
	
	@Override
	public void removePackWorker(Integer id){
		if(packwokerMap.containsKey(id)){
			packwokerMap.remove(id);
		}
	}

	@Override
	public IPackWorker createPackWorker(PackMode packmode,Person person,IPackModeService packModeService, String resourceRootPath) {
		
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");//可以方便地修改日期格式
//		String datestr = dateFormat.format(new Date());
		String resourcePath = resourceRootPath+"/platfrom_"+packmode.getId();
		File file = new File(resourcePath);
		if(!file.exists()){
			file.mkdirs();
		}
		IPackWorker packworker = null;
		if(packmode.getStructureType().equals("dsp")){
			packworker = new DSPPackWorker(packmode, person,packModeService, resourcePath);
		}
		if(packworker!=null){
			packwokerMap.put(packmode.getId(), packworker);
		}
		return packworker;
	}

	@Override
	public void packWorkerWorking(IPackWorker packworker) {
		if(packwokerMap.containsValue(packworker)){
			executor.execute(packworker);
		}
	}
	

}
