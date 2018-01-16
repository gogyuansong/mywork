package com.myproject.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtil {

	/**
	 * 
	 * @param path
	 * @return
	 */
    public static String readFile(File file){  
        BufferedReader reader = null;  
        StringBuffer laststr = new StringBuffer();  
        try{  
            FileInputStream fileInputStream = new FileInputStream(file);  
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");  
            reader = new BufferedReader(inputStreamReader);  
            String tempString = null;  
            while((tempString = reader.readLine()) != null){  
            	laststr.append(tempString);  
            }  
            reader.close();  
        }catch(IOException e){  
            e.printStackTrace();  
        }finally{  
            if(reader != null){  
                try {  
                    reader.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }
        return laststr.toString();  
    }  
}
