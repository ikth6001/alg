package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://www.acmicpc.net/problem/1149
 */
public class Q01149 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q01149 m= new Q01149();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		int homeCnt= Integer.parseInt(br.readLine());
		int[][] prices= new int[3][homeCnt];
		
		for(int i=0; i<homeCnt; i++) {
			String[] row= br.readLine().split(" ");
			prices[0][i]= Integer.parseInt(row[0]);
			prices[1][i]= Integer.parseInt(row[1]);
			prices[2][i]= Integer.parseInt(row[2]);
		}
		
		int[][] dp= new int[3][homeCnt];
		dp[0][0]= prices[0][0];
		dp[1][0]= prices[1][0];
		dp[2][0]= prices[2][0];
		
		for(int i=1; i<homeCnt; i++) {
			dp[0][i]= min(dp[1][i-1], dp[2][i-1]) + prices[0][i];
			dp[1][i]= min(dp[0][i-1], dp[2][i-1]) + prices[1][i];
			dp[2][i]= min(dp[0][i-1], dp[1][i-1]) + prices[2][i];
		}
		
		int min= min(dp[0][homeCnt-1], dp[1][homeCnt-1], dp[2][homeCnt-1]);
		bw.write(String.valueOf(min));
	}
	
	private int min(int... scores) {
		int min= Integer.MAX_VALUE;
		
		for(int score : scores) {
			min= min > score ? score : min;
		}
		
		return min;
	}
	
}
