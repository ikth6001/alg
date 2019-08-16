package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://www.acmicpc.net/problem/1799
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
	
	/**
	 * 블로그 참고
	 * - https://pangsblog.tistory.com/84
	 * - https://saurus2.tistory.com/entry/1799-%EB%B0%B1%EC%A4%80-%EB%B9%84%EC%88%8D-%EB%B0%B1%ED%8A%B8%EB%9E%98%ED%82%B9
	 * 핵심은 대각선만 탐색 하면서 탐색 횟수를 줄이는 것.
	 */
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		int n= Integer.valueOf(br.readLine());
		int[][] chessMap= new int[n][n];
		
		for(int i=0; i<n; i++) {
			String[] row= br.readLine().split(" ");
			for(int j=0; j<n; j++) {
				chessMap[i][j]= Integer.valueOf(row[j]);
			}
		}
		
		int cnt= solution(chessMap, new boolean[n*2], 0) + solution(chessMap, new boolean[n*2], 1);
		bw.write(String.valueOf(cnt));
	}
	
	private int solution(int[][] chessMap, boolean[] v, int idx) {
		
		int n= chessMap.length;
		
		if(idx >= 2*n-1) {
			return 0;
		}
		
		int max= -1;
		int h= idx < n ? idx : n-1;
		int w= idx < n ? 0 : idx-n+1;
		
		while(h >= 0 && w < n) {
			int vIdx= h-w+n;
			if(chessMap[h][w] == 1 && !v[vIdx]) {
				v[vIdx]= true;
				int cnt= 1 + solution(chessMap, v, idx+2);
				max= max < cnt ? cnt : max;
				v[vIdx]= false;
			}
			w++;
			h--;
		}
		
		if(max < 0) {
			max= solution(chessMap, v, idx+2);
		}
		
		return max;
	}
	
//	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
//		
//		int n= Integer.valueOf(br.readLine());
//		int[][] chessMap= new int[n][n];
//		
//		for(int i=0; i<n; i++) {
//			String[] row= br.readLine().split(" ");
//			for(int j=0; j<n; j++) {
//				chessMap[i][j]= Integer.valueOf(row[j]);
//			}
//		}
//		
//		int max= solution(chessMap, new boolean[n*2], new boolean[n*2], 0, 0);
//		bw.write(String.valueOf(max));
//	}
//	
//	private int solution(int[][] chessMap, boolean[] ltrb, boolean[] lbrt, int h, int w) {
//
//		int n= chessMap.length;
//		
//		if(w == n) {
//			w= 0;
//			h++;
//		}
//		
//		int max= 0;
//		for(int i=h; i<n; i++) {
//			
//			for(int j=w; j<n; j++) {
//				w= 0; // 이걸 안해서 답이 틀렸었음. 아무튼 계속 시간초과는 나므로 방법 생각 필요.
//				int ltrbIdx= i-j+n-1;
//				int lbrtIdx= i+j;
//				
//				if(chessMap[i][j] == 0 || ltrb[ltrbIdx] || lbrt[lbrtIdx]) {
//					continue;
//				}
//				
//				ltrb[ltrbIdx]= true;
//				lbrt[lbrtIdx]= true;
//				
//				int cnt= 1 + solution(chessMap, ltrb, lbrt, i, j+1);
//				max= max < cnt ? cnt : max;
//				
//				ltrb[ltrbIdx]= false;
//				lbrt[lbrtIdx]= false;
//			}
//		}
//		
//		return max;
//	}
}
