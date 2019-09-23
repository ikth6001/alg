package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://www.acmicpc.net/problem/11726
 */
public class Q11726 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q11726 m= new Q11726();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		int n= Integer.parseInt(br.readLine());
		bw.write(solution(n));
	}
	
	private String solution(int n) {
		
		if(n == 1) {
			return "1";
		}
		
		int[] dp= new int[n+1];
		dp[1]= 1;
		dp[2]= 2;
		
		for(int i=3; i<=n; i++) {
			dp[i]= (dp[i-2] + dp[i-1]) % 10007;
		}
		
		return String.valueOf(dp[n] % 10007);
	}
}
