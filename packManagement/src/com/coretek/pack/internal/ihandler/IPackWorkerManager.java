package com.coretek.pack.internal.ihandler;

import com.coretek.pack.model.PackMode;
import com.coretek.pack.model.Person;
import com.coretek.pack.service.IPackModeService;

public interface IPackWorkerManager {

	IPackWorker getPackWorker(Integer id);
	
	void removePackWorker(Integer id);
	
	IPackWorker createPackWorker(PackMode packmode,Person person,IPackModeService packModeService,String resourcePath);
	
	void packWorkerWorking(IPackWorker packworker);
}
