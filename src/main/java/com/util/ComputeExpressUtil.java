package com.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import net.java.dev.eval.Expression;

/**
 * 公式计算
 * @author wcy
 *
 */
public class ComputeExpressUtil {
	
	/**
	 * 计算字符串型的公式(可以输入三目运算符)
	 * @param express
	 * @return
	 */
	public static BigDecimal stringExpress(String express) {
		String replaceSign = express.replaceAll("sign", ">0?1:0");
		Expression expression = new Expression(replaceSign);
		Map<String, BigDecimal> variables = new HashMap<String, BigDecimal>();
		variables.put("e", new BigDecimal(Math.E));
		BigDecimal result = expression.eval(variables);
		return result;
	}
	
	

}
