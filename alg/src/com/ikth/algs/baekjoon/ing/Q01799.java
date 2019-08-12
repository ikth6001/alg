package com.ikth.algs.baekjoon.ing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://www.acmicpc.net/problem/1799
 * 
 * -> 현재 시간초과 나고 있음 어떻게 줄일까??
 * -> 비숍은 대각선만 관여하므로 하나의 row를 담기 시작한다면 대각선 제외 전부 담을 수 있다. 굳이 재귀로 하나 row의 모든 행을 탐색할 필요가 있을까??
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
		if(h == n-1 && w == n-1) {
			return (chessMap[h][w] == 0 || ltrb[Math.abs(h-w)] || lbrt[h+w]) ? 0 : 1;
		}
		
		if(w == n-1) {
			w= 0;
			h++;
		}
		
		int max= 0;
		for(int i=h; i<n; i++) {
			
			for(int j=w; j<n; j++) {
				int cnt= 0;
				boolean changed= false;
				
				int ltrbIdx= i-j+n-1;
				int lbrtIdx= i+j;
				
				if(chessMap[i][j] == 1 && !ltrb[ltrbIdx] && !lbrt[lbrtIdx]) {
					ltrb[ltrbIdx]= true;
					lbrt[lbrtIdx]= true;
					cnt= 1;
					changed= true;
				}
				
				cnt+= solution(chessMap, ltrb, lbrt, i, j+1);
				max= max < cnt ? cnt : max;
				
				if(changed) {
					ltrb[ltrbIdx]= false;
					lbrt[lbrtIdx]= false;
				}
			}
		}
		
		return max;
	}
}
