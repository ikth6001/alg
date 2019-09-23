package com.ikth.algs.baekjoon.ing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://www.acmicpc.net/problem/2293
 */
public class Main {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Main m= new Main();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		String[] row= br.readLine().split(" ");
		int cnt= Integer.parseInt(row[0]);
		int price= Integer.parseInt(row[1]);
		int[] coins= new int[cnt];
		for(int i=0; i<cnt; i++) {
			coins[i]= Integer.parseInt(br.readLine());
		}
		
		boolean[] calc= null;
		int[] dp= new int[price+1];
		dp[0]= 1;
		
		for(int i=1; i<=price; i++) {
			
			calc= new boolean[price+1];
			for(int coin : coins) {
				if(i < coin
						|| calc[i-coin]) {
					continue;
				}
				
				dp[i]+= dp[i-coin];
				calc[coin]= true;
			}
		}
		
		bw.write(String.valueOf(dp[price]));
	}
}
