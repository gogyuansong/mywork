package com.gys.service;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.gys.constant.Constant;
import com.gys.service.impl.IVoiceService;
import com.gys.utils.ZipUtil;

@Service
public class VoiceServiceImpl implements IVoiceService{

	@Value("${custom.baseDir}")
	private String baseDir;
	
	@Value("${custom.tempDir}")
	private String tempDir;
	
	@Override
	public String getPathByOrderStr(String orderStr, String languageType) {
		
		String matter = getMatter(orderStr, languageType);
		//TODO
		//这里的path 需要从数据库中查询返回，这里太简单了，后续优化
		String path = matter;
		if(!StringUtils.isEmpty(matter)) {
			//查询数据库pash
		}
		return path;
	}
	
	@Override
	public File getZipFile(String path) throws Exception {
		//读取出path下的文件
		File file = new File(baseDir + path);
		String zipFileName = UUID.randomUUID().toString() + ".zip";
		if(file.exists() && !file.isDirectory()) {
			ZipUtil.zipFiles(file, new File(tempDir + zipFileName));
			return new File(tempDir + zipFileName);
		}else {
			return null;
		}
	}
	
	private String getMatter(String str, String languageType) {
		if(languageType.equals(Constant.CH)) {
			if(str.startsWith("播放")) {
				return str.substring(2);
			}
		}else if(languageType.equals(Constant.EN)) {
			if(str.startsWith("play ")) {
				return str.substring(5);
			}
		}
		return "";
	}
}