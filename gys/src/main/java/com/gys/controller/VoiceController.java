package com.gys.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gys.service.impl.IVoiceService;

@RestController
public class VoiceController {

	@Autowired
	private IVoiceService voiceService;

	@RequestMapping(value = "/gys/test", method = RequestMethod.GET)
	public void returnVoice(@RequestParam(value = "orderStr") String order,
			@RequestParam(value = "languageType") String type, HttpServletResponse res) {
		if (!StringUtils.isEmpty(order.trim()) && !StringUtils.isEmpty(type.trim())) {
			try {
				String path = voiceService.getPathByOrderStr(order, type);
				File file = voiceService.getZipFile(path);
				downFile(file, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void downFile(File file, HttpServletResponse res) {
		byte[] buff = new byte[1024];
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			res.setHeader("content-disposition", "attachment;filename="+file.getName());
			res.setContentType("application/ostet-stream");
			os = res.getOutputStream();
			bis = new BufferedInputStream(new FileInputStream(file));
			int i = bis.read(buff);
			while (i != -1) {
				os.write(buff, 0, buff.length);
				os.flush();
				i = bis.read(buff);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("success");
	}
}