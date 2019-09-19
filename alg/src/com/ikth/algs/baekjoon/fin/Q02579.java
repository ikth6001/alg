package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://www.acmicpc.net/problem/2579
 */
public class Q02579 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q02579 m= new Q02579();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		int n= Integer.parseInt(br.readLine());
		int[] stairs= new int[n];
		
		for(int i=0; i<n; i++) {
			stairs[i]= Integer.parseInt(br.readLine());
		}
		
		int[][] dp= new int[2][n];
		dp[0][0]= stairs[0];
		dp[1][0]= stairs[0];
		dp[0][1]= stairs[1];
		dp[1][1]= stairs[0] + stairs[1];
		
		for(int i=2; i<n; i++) {
			dp[0][i]= max(dp[0][i-2], dp[1][i-2]) + stairs[i];
			dp[1][i]= dp[0][i-1] + stairs[i];
		}
		
		int max= dp[0][n-1] > dp[1][n-1] ? dp[0][n-1] : dp[1][n-1];
		bw.write(String.valueOf(max));
	}
	
	private int max(int...nums) {
		int max= Integer.MIN_VALUE;
		for(int num : nums) {
			max= max < num ? num : max;
		}
		return max;
	}
}
