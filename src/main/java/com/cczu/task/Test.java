package com.cczu.task;

import com.cczu.sys.comm.utils.security.Digests;
import com.cczu.sys.comm.utils.security.Encodes;

public class Test {
public static void main(String[] args) {
	byte[] salt = Encodes.decodeHex("1095c26e771ad409");
	String pass="000000";
	byte[] hashPassword = Digests.sha1(pass.getBytes(),salt, 1024);
	System.out.println(Encodes.encodeHex(hashPassword));
//	if("f2d12368bd5fb8354ea12d7bbd41f14511409802".equals(Encodes.encodeHex(hashPassword)))
//		System.out.println("true");
//	else
//		System.out.println("false");
}
}
