package com.jiangc.practice.algorithm.stringanalysis;

/**
 * 
 * @author jiangcheng-wb
 *
 */
public class StringUtils2 {

	public static String trimBracket(String exp) {
		// TODO Auto-generated method stub
		while((exp.indexOf(")")>-1&&exp.indexOf("(")==-1)||(exp.indexOf("(")>-1&&exp.indexOf(")")>-1&&exp.indexOf("(")>exp.indexOf(")"))){
			//String bracket = exp.substring(0, exp.indexOf(")")+1);
			exp = exp.substring(exp.indexOf(")")+1);
		}
		//String bracket = exp.indexOf("(")>-1&&exp.indexOf(")")>-1?exp.substring(exp.indexOf("("), exp.indexOf(")")+1):"";
		//return exp.replace(bracket, "");
		return exp;
	}

	public static void main(String[] args) {
		System.out.println(trimBracket("sdsjdskdjd(skdsjldksld"));;
	}
}
