package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://www.acmicpc.net/problem/2293
 */
public class Q02293 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q02293 m= new Q02293();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 블로그 참고.
	 * 단순히 d[i]+= for(COIN_VAL : coins) d[i-COIN_VAL]
	 * 방식을 사용하면 중복된 값이 포함되어 잘못된 답을 구하게 된다. e.g. 3원을 만들때 1,2와 2,1을 구별 못함.
	 * 때문에 먼저 가장 낮은 금액부터 1~PRICE 까지 쭉 구한 후에 해당 값을 통해 다음 금액을 추가하는 방식으로 처리해야 한다.
	 * 즉,
	 * 5원을 만들고 1, 2원 동전을 사용한다고 하면
	 * 먼저 1원을 기준으로 방법을 구한다.
	 * 1 / 11 / 111 / 1111 / 11111 이렇게 구할 수 있는데 이렇게 구한 후에, 구한 결과에 2원을 추가하여 금액을 구하면 된다.
	 * X / 2  / 1 2 / 11 2 / 111 2, 122
	 * 이런 방식으로 하면 중복된 값 없이 갯수를 구할 수 있다.
	 */
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		String[] row= br.readLine().split(" ");
		int cnt= Integer.parseInt(row[0]);
		int price= Integer.parseInt(row[1]);
		int[] coins= new int[cnt];
		for(int i=0; i<cnt; i++) {
			coins[i]= Integer.parseInt(br.readLine());
		}
		
		int[][] dp= new int[cnt][price+1];
		
		for(int i=0; i<cnt; i++) {
			dp[i][0]= 1;
			int coin= coins[i];
			
			for(int j=1; j<=price; j++) {
				if(j<coin) {
					dp[i][j]= i > 0 ? dp[i-1][j] : 0;
				} else {
					dp[i][j]= dp[i][j-coin] + (i > 0 ? dp[i-1][j] : 0);
				}
			}
		}
		
		bw.write(String.valueOf(dp[cnt-1][price]));
	}
}
