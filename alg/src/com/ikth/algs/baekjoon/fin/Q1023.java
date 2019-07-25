package com.ikth.algs.baekjoon.fin;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/1023
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
	
	private final char OPEN= '0';
	private final char CLSE= '1';
	
	/**
	 * 블로그 참고 : https://blog.encrypted.gg/264
	 */
	private String solution(int n, long k) {
		
		return null;
	}
	
	/**
	 * 내 풀이는 실패... 메모리 초과...
	 */
	@SuppressWarnings("unused")
	private String mine(int n, long k) {
		
		/**
		 * dp[i] : i길이인 문자열의 괄호 문자열 Set
		 */
//		long[][] dp= new long[n+1][];
		@SuppressWarnings("unchecked")
		HashSet<Long>[] dp= new HashSet[n+1];
		HashSet<Long> fst= new HashSet<Long>();
		fst.add(1L);
		dp[2]= fst;
		
		for(int i=4; i<=n; i+=2) {
			
			dp[i]= new HashSet<Long>();
			for(int j=0; j<dp[i-2].size(); j++) {
				for(Long prev : dp[i-2]) {
					// 괄호 감싸기
					long next= prev << 1 | 1;
					dp[i].add(next);
					
					// 왼쪽에 괄호 더하기
					next= 1L;
					next= next << i-2 | prev;
					dp[i].add(next);
					
					// 오른쪽에 괄호 더하기
					next= prev << 2 | 1;
					dp[i].add(next);
				}
			}
		}
		
		List<Long> brackets= new ArrayList<Long>(dp[n]);
		brackets.sort(new Comparator<Long>() {
			@Override
			public int compare(Long o1, Long o2) {
				return o1.compareTo(o2);
			}
		});
		
		long toAdd= 0L;
		long last= 0L;
		
		for(Long bracket : brackets) {
			if(bracket >= k) {
				last= bracket;
				break;
			} else {
				toAdd++;
			}
		}
		
		long ans= 0L;
		
		if(last == k) {
			ans= last + toAdd + 2L;
		} else {
			ans= k + toAdd + 1L;
		}
		
		return String.format("%" + n + "s",  Long.toBinaryString(ans)).replace(' ', OPEN).replace(OPEN, '(').replace(CLSE, ')');
	}
}
