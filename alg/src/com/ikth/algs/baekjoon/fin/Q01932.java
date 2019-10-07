package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://www.acmicpc.net/problem/1932
 */
public class Q01932 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q01932 m= new Q01932();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		int n= Integer.parseInt(br.readLine());
		int[][]	triangle= new int[n][];
		
		for(int i=0; i<n; i++) {
			String[] row= br.readLine().split(" ");
			triangle[i]= new int[row.length];
			
			for(int j=0; j<row.length; j++) {
				triangle[i][j]= Integer.parseInt(row[j]);
			}
		}
		
		int[][] dp= new int[n][];
		dp[0]= new int[] { triangle[0][0] };
		
		for(int i=1; i<n; i++) {
			dp[i]= new int[triangle[i].length];
			
			for(int j=0; j<dp[i-1].length; j++) {
				dp[i][j]= max(dp[i][j], dp[i-1][j] + triangle[i][j]);
				dp[i][j+1]= max(dp[i][j+1], dp[i-1][j] + triangle[i][j+1]);
			}
		}
		
		int max= max(dp[n-1]);
		bw.write(String.valueOf(max));
	}
	
	private int max(int... nums) {
		int max= 0;
		for(int n : nums) {
			max= max < n ? n : max;
		}
		return max;
	}
}
