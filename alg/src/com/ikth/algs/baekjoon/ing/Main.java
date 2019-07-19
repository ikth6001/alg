package com.ikth.algs.baekjoon.ing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/1020
 */
public class Main {
	
	public static void main(String[] args) {
		
		try (Scanner scanner = new Scanner(System.in)) {

			String time = scanner.nextLine();
			Main m = new Main();
			System.out.println(m.solution(time));
		}
	}
	
	private final Integer[] LENGTHS= new Integer[] { 6, 2, 5, 5, 4, 5, 6, 3, 7, 5 };
	
	/**
	 * @param time 시간을 나타냄. 길이 N <= 15 이고, 시간이 N자리보다 작으면 0으로 채워진다. e.g. 007 (N이 3인 경우)
	 * @return
	 */
	private long solution(String time) {

		/**
		 * - 시간의 길이를 N이라고 할 때, 각 시간의 자릿수 K는 2 * N <= K <= 7 * N 이고, N <= 15 이다.
		 * - 이전 시간 패턴에 각 숫자를 하나씩 추가하여 각 패턴의 새로운 자릿수를 구할 수 있다.
		 * 
		 * - dp는 배열이다. dp[i]는 길이가 i인 패턴들의 List를 의미한다.
		 * - 시간의 길이를 1부터 최대 길이까지 Loop을 돌면서 이전 데이터를 통해 새로운 데이터를 빠르게 구한다.
		 */
		
		final int timeLeng= time.length();
		final int maxLeng= timeLeng * 7;
		
		@SuppressWarnings("unchecked")
		List<Integer>[] dp= new ArrayList[maxLeng];
		
		for(int i=0; i<timeLeng; i++) {
			
			if(i == 0) {
				for(int j=0; j<LENGTHS.length; j++) {
					add(dp, LENGTHS[j], j);
				}
			} else {
				for(int j=0; j<maxLeng; j++) {
					if(dp[j] == null) {
						continue;
					}
					
					for(int k=0; k<dp[j].size(); k++) {
						for(int z=0; z<LENGTHS.length; z++) {
							int val= dp[j].get(k);
							add(dp, j + LENGTHS[z], val * 10 + LENGTHS[z]);
						}
					}
				}
			}
		}
		
		return 0L;
	}
	
	private void add(List<Integer>[] dp, int idx, int val) {
		
		if(dp[idx] == null) {
			dp[idx]= new ArrayList<>();
		}
		dp[idx].add(val);
	}
}
