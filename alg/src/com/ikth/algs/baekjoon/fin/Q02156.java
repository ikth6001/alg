package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://www.acmicpc.net/problem/2156
 */
public class Q02156 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q02156 m= new Q02156();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		int n= Integer.parseInt(br.readLine());
		int[] wines= new int[n];
		for(int i=0; i<n; i++) {
			wines[i]= Integer.parseInt(br.readLine());
		}
		
		if(n < 2) {
			bw.write(String.valueOf(wines[0]));
			return;
		}
		
		long[][] dp= new long[n][2];
//		long max= wines[0] + wines[1];
		long[] max= new long[2];
		max[0]= wines[0];
		max[1]= wines[0] + wines[1];
		dp[0][0]= wines[0];
		dp[1][0]= wines[1];
		dp[1][1]= max[1];
		
		for(int i=2; i<n; i++) {
			dp[i][0]= max(max[0], dp[i-2][0], dp[i-2][1]) + wines[i];
			dp[i][1]= dp[i-1][0] + wines[i];
			max[0]= max[1];
			max[1]= max(max[0], dp[i][0], dp[i][1]);
		}
		
		bw.write(String.valueOf(max[1]));
	}
	
	private long max(long... nums) {
		long max= 0;
		for(long n : nums) {
			max= max < n ? n : max;
		}
		return max;
	}
}
