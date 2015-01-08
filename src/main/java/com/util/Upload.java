package com.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Upload {
	
	public static String uploadFile(InputStream uploadFile, String destPath, String filename) throws Exception {
		String time = "(" + DateUtil.formatTimestampToStringByFmt(DateUtil.getNowTimestamp(), DateUtil.ADC_MSG_SID) + ")";
		String newName = filename.substring(0, filename.lastIndexOf(".")) + time + ".xls";
		File file = new File(destPath + newName);
		int available = uploadFile.available();
		byte[] b = new byte[available];
		FileOutputStream foutput = new FileOutputStream(file);
		uploadFile.read(b);
		foutput.write(b);
		foutput.flush();
		foutput.close();
		uploadFile.close();
		return newName;
	}
}
