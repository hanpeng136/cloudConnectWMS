package com.hanpeng.utils;

import java.security.MessageDigest;

public class MD5 {
	
	public static  void main(String args[]){
		try {
			System.out.println(getMD5String("0"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getMD5String(String pwd) throws Exception{
		//明文
		//pwd="a12345";
		//生成原始密文
		String md5=MD5Encode(pwd);
		//二次混淆明文
		String pwd2=md5+"1qaz@WSX3edc"+md5;
		//生成二次混淆密文
		String md52=MD5Encode(pwd2);
		return md52;
	}
	
	private final static String[] hexDigits = {
		     "0", "1", "2", "3", "4", "5", "6", "7",
		     "8", "9", "a", "b", "c", "d", "e", "f"};

		  /**
		   * 转换字节数组为16进制字串
		   * @param b 字节数组
		   * @return 16进制字串
		   */
		  private static String byteArrayToHexString(byte[] b)
		  {
		      StringBuffer resultSb = new StringBuffer();
		      for (int i = 0; i < b.length; i++)
		      {
		         resultSb.append(byteToHexString(b[i]));
		      }
		      return resultSb.toString();
		  }
	
	/**
	   * 转换字节为16进制字符串
	   * @param b byte
	   * @return String
	   */
	  private static String byteToHexString(byte b)
	  {
	      int n = b;
	      if (n < 0)
	         n = 256 + n;
	      int d1 = n / 16;
	      int d2 = n % 16;
	      return hexDigits[d1] + hexDigits[d2];
	  }
	  
	/**
	   * 得到MD5的秘文密码
	   * @param origin String
	   * @throws Exception
	   * @return String
	   */
	  private static String MD5Encode(Object origin) throws Exception
	  {
	       String resultString = null;
	       try
	       {
	           resultString=new String(origin.toString());
	           MessageDigest md = MessageDigest.getInstance("MD5");
	           resultString=byteArrayToHexString(md.digest(resultString.getBytes()));
	           return resultString;
	       }
	       catch (Exception ex)
	       {
	          throw ex;
	       }
	  }	
}
