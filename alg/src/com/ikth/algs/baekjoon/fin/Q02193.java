package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://www.acmicpc.net/problem/2193
 */
public class Q02193 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q02193 m= new Q02193();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		int n= Integer.parseInt(br.readLine());
		
		long[][] dp= new long[2][n];
		dp[1][0]= 1;
		
		for(int i=1; i<n; i++) {
			dp[0][i]= dp[0][i-1] + dp[1][i-1];
			dp[1][i]= dp[0][i-1];
		}
		
		long cnt= dp[0][n-1] + dp[1][n-1];
		bw.write(String.valueOf(cnt));
	}
}
