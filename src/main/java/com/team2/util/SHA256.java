package com.team2.util;

import java.security.MessageDigest;

public class SHA256 {
	
//	private static String Salt ="hamburger"; //임시 소금 
	private final static String salt = "햄버거좋아";

	public String encodSha256(String plain) {
		String result = "";

		byte[] bytePlain = plain.getBytes(); // 들어온 문자들을 바이트 단위로 쪼갠다.

//		for (byte b : bytePlain) {
////			System.out.println(b);
//		}

		byte[] bytesalt = salt.getBytes();

		for (byte b : bytesalt) {

//			System.out.println(b);
		}

		byte[] bytePlainAndSalt = new byte[bytePlain.length + bytesalt.length];

		for (int i = 0; i < bytePlainAndSalt.length; i++) { // 비밀번호와 솔트 넣기

			if (i < bytePlain.length) { // 바이트 플레인 길이만큼 채워넣고

				bytePlainAndSalt[i] = bytePlain[i];

			} else { // 플레인 길이를 오버하면 그 뒤에 솔트를 채워넣는다.

				// i - bytePlain.length은 예를들어 i가 6이면 6-6이 되어서 0번째 배열 내용을 소환한다.
				bytePlainAndSalt[i] = bytesalt[i - bytePlain.length];

			}

		}

		System.arraycopy(bytePlain, 0, bytePlainAndSalt, 0, bytePlain.length);
		// 매개변수 ; 첫번째 넣을배열, 두번째 넣을배열 시작위치, 세번째 받을배열, 네번째 받을배열 시작위치, 다섯번째 넣을길이

		System.arraycopy(bytesalt, 0, bytePlainAndSalt, bytePlain.length, bytesalt.length);

		for (byte b : bytePlainAndSalt) {

//			System.out.print(b + " ");

		}

		try {

			MessageDigest md = MessageDigest.getInstance("SHA-256"); // SHA-256암호화 객체 생성
			md.update(bytePlainAndSalt); // md 객체 안에 세팅

			byte[] bytedata = md.digest(); // md 객체에서 데이터 받아오기

			// StringBuffer 동기화 기능이 있어서 여러 객체가 접근하지 못한다. 임계구역
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < bytedata.length; i++) {
				sb.append(Integer.toHexString((bytedata[i] & 0xFF) + 256).substring(1));
			}

//			System.out.println(sb.toString());

			return sb.toString();

		} catch (Exception e) {

		}
		return"";
	}

	

}
