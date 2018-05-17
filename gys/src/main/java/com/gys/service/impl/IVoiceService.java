package com.gys.service.impl;

import java.io.File;

public interface IVoiceService {

	public String getPathByOrderStr(String orderStr, String languageType);
	
	public File getZipFile(String path) throws Exception;
	
}
