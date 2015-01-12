package com.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public class PathUtil {

	public static String readProjectPath() {
		File file = new File("");
		try {
			return file.getCanonicalPath();
		} catch (IOException e) {
			System.err.println("项目路径获取失败");
			e.printStackTrace();
			return null;
		}
	}

	public static String readProjectWebRootPath() {
		return readProjectPath() + File.separator + "src" + File.separator + "main" + File.separator + "webapp";
	}
	
	public static String getPath(HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath("/");
	}

}
