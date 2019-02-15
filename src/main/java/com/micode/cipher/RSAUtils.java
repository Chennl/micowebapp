package com.micode.cipher;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;


public class RSAUtils {
    private static String RSA = "RSA";
    private static int KEYSIZE =1024;

    /** *//** 
     * RSA最大加密明文大小 
     */  
    private static final int MAX_ENCRYPT_BLOCK = 117;  
       
    /** *//**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128; 
 
	/**
	 * * 随机生成密码对
	 * @param keysize
	 * *   密钥长度，范围：512～2048 ， 一般长度1024
	 *  
	 */
	private static KeyPair generateRSAKeyPair(int keysize) {
		 /** RSA算法要求有一个可信任的随机数源 */
	 	SecureRandom sr = new SecureRandom();
	 	try {
	 	/** 为RSA算法创建一个KeyPairGenerator对象 */
	 	KeyPairGenerator kpg =  KeyPairGenerator.getInstance(RSA);
	 	kpg.initialize(keysize,sr);
	 	return kpg.genKeyPair();
	 	
	 	}catch(NoSuchAlgorithmException e) {
	 		e.printStackTrace();
	 		return null;
	 	}
	 	
	}
	
	private static void createRSAKeyPairFile() {
			 
			KeyPair kp = generateRSAKeyPair(KEYSIZE);
			//公钥
			Key publicKey = kp.getPublic();
			Key privateKey = kp.getPrivate();
			try {
				ObjectOutputStream oos1= new ObjectOutputStream(new FileOutputStream("publickey.store"));
				ObjectOutputStream oos2= new ObjectOutputStream(new FileOutputStream("privatekey.store"));
				 oos1.writeObject(publicKey);
		         oos2.writeObject(privateKey);
		        /** 清空缓存，关闭文件输出流 */
		        oos1.close();
		        oos2.close();
 
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	public static byte[] encryptData(byte[] data,PublicKey publicKey) {
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE,publicKey);
			int inputLength = data.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet =0;
			int i=0;
			byte[] cache;
			//分段加密
			while(inputLength - offSet >0) {
				if(inputLength  - offSet > MAX_ENCRYPT_BLOCK) {
					cache = cipher.doFinal(data,offSet,MAX_ENCRYPT_BLOCK);
				}else {
					cache = cipher.doFinal(data,offSet,inputLength - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = MAX_ENCRYPT_BLOCK*i;
			}
			byte[] encryptedData = out.toByteArray();
			out.close();
			return encryptedData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String encrypt(String source) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("publickey.store"));
			PublicKey publicKey = (PublicKey)ois.readObject();
			byte [] cache =encryptData(source.getBytes("UTF8"),publicKey);
			Encoder encoder = Base64.getEncoder(); 
			byte[] encode64 = encoder.encode(cache);
			String encryptedString = new String(encode64);
			System.out.println("Original byte string: "+new String(cache));
			System.out.println("Base64 encode string: "+encryptedString);

			ois.close();
			return encryptedString;
		} catch (IOException|ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} 
	}

    /**
     ** 用私钥解密
     *
     * @param encryptedData
     **  经过encryptedData()加密返回的byte数据
     * @param privateKey
     * *           私钥
     * @return
     */
	 
    public static byte[] decryptData(byte[] encryptedData, PrivateKey privateKey)
    {
    	int offSet=0;
    	int inputLength=encryptedData.length;
    	int i=0;
    	byte[] cache;
    	ByteArrayOutputStream out =  new ByteArrayOutputStream();
        try{	
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
        	while(inputLength -offSet>0) {
        		if(inputLength -offSet > MAX_DECRYPT_BLOCK) {
        			cache = cipher.doFinal(encryptedData,offSet,MAX_DECRYPT_BLOCK);
        		}else {
        			cache = cipher.doFinal(encryptedData,offSet,inputLength -offSet);
        		}
        		i++;
        		offSet=MAX_DECRYPT_BLOCK * i;
    			out.write(cache);
        	}
        	byte[] decryptedData = out.toByteArray();
            out.close();
            return decryptedData; 
        } catch (Exception e)
        {	
        	e.printStackTrace();
            return null ;
        }
    }
    
    public static String decrypt(String cryptograph) {
    	try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("privatekey.store"));
			PrivateKey PrivateKey = (PrivateKey)ois.readObject();
			ois.close();
			
			Decoder decoder = Base64.getDecoder();
			byte[] cache =decryptData(decoder.decode(cryptograph),PrivateKey);

			return new String(cache); 
		} catch (IOException|ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}  
    	
    }
    

}
