package com.ikth.algs.baekjoon.ing;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/1023
 * 
 * 1020 < 1023 < 1040
 */
public class Q1023 {

	public static void main(String[] args) {
//		System.out.println("1");
//		System.out.println(Integer.MAX_VALUE);
//		System.out.println(Long.MAX_VALUE);
//		System.out.println(new Double(Math.pow(2, 49)).longValue());
//		System.out.println("2");
		
		try(Scanner scanner= new Scanner(System.in)) {
			Q1023 m= new Q1023();
			
			int n= scanner.nextInt();
			long k= scanner.nextLong();
			
			System.out.println(m.solution(n, k));
		}
		
	}
	
	/**
	 * n 길이의 괄호ㄴㄴ인 k번째 문자열을 찾는 메소드
	 * @param n 50보다 작은 자연수
	 * @param k 2^(n-1)보다 작은 자연수
	 * @return
	 */
	private String solution(int n, long k) {
		
		/**
		 * dp[i][j] : i길이인 문자열의 j번째 괄호 값
		 */
		
		return null;
	}
	
//	private final char OPEN= '0';
//	private final char CLSE= '1';
//	
//	/**
//	 * n 길이의 괄호ㄴㄴ인 k번째 문자열을 찾는 메소드
//	 * @param n 50보다 작은 자연수
//	 * @param k 2^(n-1)보다 작은 자연수
//	 * @return
//	 */
//	private String solution(int n, long k) {
//		
//		long max= ((Double) Math.pow(2, n)).longValue();
//		long cnt= 0;
//		
//		for(long i=0; i<max; i++) {
//			String binary= toFixedLengthBinary(n, i);
//			
//			if(isNoBracketNum(binary)) {
//				
//				if(cnt++ == k) {
//					return toBracketString(binary);
//				} else if(cnt > k) {
//					break;
//				}
//			}
//		}
//		
//		return "-1";
//	}
//	
//	private boolean isNoBracketNum(String binary) {
//		int openCnt= 0;
//		int clseCnt= 0;
//		
//		for(char n : binary.toCharArray()) {
//			
//			if(n == OPEN) {
//				openCnt++;
//			} else if(n == CLSE) {
//				clseCnt++;
//			}
//			
//			if(openCnt > binary.length() / 2
//					|| clseCnt > openCnt) {
//				return true;
//			}
//		}
//		
//		return false;
//	}
//	
//	private String toFixedLengthBinary(int leng, long num) {
//		return String.format("%" + leng + "s", Long.toBinaryString(num)).replace(' ', '0');
//	}
//	
//	private String toBracketString(String binary) {
//		return binary.replace(OPEN, '(').replace(CLSE, ')');
//	}
}
