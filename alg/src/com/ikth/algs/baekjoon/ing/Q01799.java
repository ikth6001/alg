package com.ikth.algs.baekjoon.ing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://www.acmicpc.net/problem/1799
 * -> 맞왜틀..? ㅜㅜ
 */
public class Q01799 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q01799 m= new Q01799();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		int n= Integer.valueOf(br.readLine());
		int[][] chessMap= new int[n][n];
		
		for(int i=0; i<n; i++) {
			String[] row= br.readLine().split(" ");
			for(int j=0; j<n; j++) {
				chessMap[i][j]= Integer.valueOf(row[j]);
			}
		}
		
		int max= solution(chessMap, new boolean[n*2], new boolean[n*2], 0, 0);
		bw.write(String.valueOf(max));
	}
	
	private int solution(int[][] chessMap, boolean[] ltrb, boolean[] lbrt, int h, int w) {

		int n= chessMap.length;
		
		if(w == n) {
			w= 0;
			h++;
		}
		
		int max= 0;
		for(int i=h; i<n; i++) {
			
			for(int j=w; j<n; j++) {
				w= 0; // 이걸 안해서 답이 틀렸었음. 아무튼 계속 시간초과는 나므로 방법 생각 필요.
				int ltrbIdx= i-j+n-1;
				int lbrtIdx= i+j;
				
				if(chessMap[i][j] == 0 || ltrb[ltrbIdx] || lbrt[lbrtIdx]) {
					continue;
				}
				
				ltrb[ltrbIdx]= true;
				lbrt[lbrtIdx]= true;
				
				int cnt= 1 + solution(chessMap, ltrb, lbrt, i, j+1);
				max= max < cnt ? cnt : max;
				
				ltrb[ltrbIdx]= false;
				lbrt[lbrtIdx]= false;
			}
		}
		
		return max;
	}
}
