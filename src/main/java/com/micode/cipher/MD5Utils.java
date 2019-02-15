package com.micode.cipher;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.tomcat.util.buf.HexUtils;

public class MD5Utils {

	public static String stringMD5(String input) {
		  try {
		     // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
		     MessageDigest messageDigest =MessageDigest.getInstance("MD5");
		     // 输入的字符串转换成字节数组
		     byte[] inputByteArray = input.getBytes();
		     // inputByteArray是输入字符串转换得到的字节数组
		     messageDigest.update(inputByteArray);
		     // 转换并返回结果，也是字节数组，包含16个元素
		     byte[] resultByteArray = messageDigest.digest();
		     // 字符数组转换成字符串返回
		     return HexUtils.toHexString(resultByteArray);
		  } catch (NoSuchAlgorithmException e) {

		     return null;

		  }
		}
	public static String fileMD5(String inputFile) throws IOException {

		  // 缓冲区大小（这个可以抽出一个参数）
		  int bufferSize = 256 * 1024;

		  FileInputStream fileInputStream = null;

		  DigestInputStream digestInputStream = null;

		  try {
		     // 拿到一个MD5转换器（同样，这里可以换成SHA1）
		     MessageDigest messageDigest =MessageDigest.getInstance("MD5");
		     // 使用DigestInputStream
		     fileInputStream = new FileInputStream(inputFile);
		     digestInputStream = new DigestInputStream(fileInputStream,messageDigest);
		     // read的过程中进行MD5处理，直到读完文件
		     byte[] buffer =new byte[bufferSize];
		     while (digestInputStream.read(buffer) > 0);
		     // 获取最终的MessageDigest
		     messageDigest= digestInputStream.getMessageDigest();
		     // 拿到结果，也是字节数组，包含16个元素
		     byte[] resultByteArray = messageDigest.digest();
		     // 同样，把字节数组转换成字符串
		     return HexUtils.toHexString(resultByteArray);

		  } catch (NoSuchAlgorithmException e) {

		     return null;

		  } finally {

		     try {

		        digestInputStream.close();

		     } catch (Exception e) {

		     }

		     try {

		        fileInputStream.close();

		     } catch (Exception e) {

		     }

		  }
		}
}
