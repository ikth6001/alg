package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://www.acmicpc.net/problem/1912
 */
public class Q01912 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q01912 m= new Q01912();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		int n= Integer.parseInt(br.readLine());
		int[] nums= new int[n];
		String[] row= br.readLine().split(" ");
		for(int i=0; i<n; i++) {
			nums[i]= Integer.parseInt(row[i]);
		}
		
		int[][] dp= new int[2][n];
		dp[0][0]= nums[0];
		dp[1][0]= nums[0];
		
		for(int i=1; i<n; i++) {
			dp[0][i]= max(dp[0][i-1] + nums[i], nums[i]);
			dp[1][i]= max(dp[1][i-1], dp[0][i]);
		}
		
		bw.write(String.valueOf(dp[1][n-1]));
	}

	private int max(int... nums) {
		int max= Integer.MIN_VALUE;
		for(int num : nums) {
			max= max < num ? num : max;
		}
		return max;
	}
}
