package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://www.acmicpc.net/problem/11053
 */
public class Q11053 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q11053 m= new Q11053();
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
		
		int[] dp= new int[n];
		dp[0]= 1;
		
		for(int i=1; i<n; i++) {
			dp[i]= 1;
			for(int j=0; j<i; j++) {
				if(nums[j] < nums[i]) {
					dp[i]= max(dp[i], dp[j]+1);
				}
			}
		}

		bw.write(String.valueOf(max(dp)));
	}
	
	private int max(int... nums) {
		int max= Integer.MIN_VALUE;
		for(int num : nums) {
			max= max < num ? num : max;
		}
		return max;
	}
}
