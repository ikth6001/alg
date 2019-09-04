package com.ikth.algs.baekjoon.ing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://www.acmicpc.net/problem/13460
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
	
	private int minCnt= 11;
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		String[] row= br.readLine().split(" ");
		int h= Integer.valueOf(row[0]);
		int w= Integer.valueOf(row[1]);
		
		char[][] board= new char[h][];
		int[] rLoc= null;
		int[] bLoc= null;
		int[] oLoc= null;
		for(int i=0; i<h; i++) {
			board[i]= br.readLine().toCharArray();
			for(int j=0; j<w; j++) {
				if(rLoc != null && bLoc != null && oLoc != null) {
					break;
				}
				switch(board[i][j]) {
					case 'R':
						rLoc= new int[] {i, j};
						break;
					case 'B':
						bLoc= new int[] {i, j};
						break;
					case 'O':
						oLoc= new int[] {i, j};
						break;
				}
			}
		}
		
		solution(board, rLoc, bLoc, oLoc, 0, new boolean[h][w], new boolean[h][w]);
		if(minCnt == 11) {
			bw.write("-1");
		} else {
			bw.write(String.valueOf(minCnt));
		}
	}
	
	private char[] moveDirs= new char[] {'U', 'R', 'D', 'L'};
	
	private void solution(char[][] board, int[] rLoc, int[] bLoc, int[] oLoc, int cnt, boolean[][] rMoveHist, boolean[][] bMoveHist) {
//		if(cnt == 10) {
//			return;
//		}
		
		/**
		 * 1. R이 위로 이동 가능한지 확인
		 * 2. 가능하다면 B가 위로 이동하면서 O를 만나는지 확인하고 만나면 위로 이동은 연산 안함
		 * 3. 2번이 아닌 상태에서, 가능하다면 R이 위로 이동하면서 O를 만나는지 확인. 만난다면 cnt 저장후 메소드 return;
		 * 4. B가 O를 안 만난다면 R과 B의 위치 갱신 후 solution 재귀호출
		 * 5. 1~4번을 우,아래,좌 방향을 적용하여 반복
		 */
		for(char dir : moveDirs) {
			if(canMove(board, rLoc, bLoc, dir)) {
				
				if(canMeetHole(board, bLoc, oLoc, dir)) {
					continue;
				}
				if(canMeetHole(board, rLoc, oLoc, dir)) {
					this.minCnt= this.minCnt > (cnt+1) ? (cnt+1) : minCnt;
					break;
				}
				
				int rh= rLoc[0], rw= rLoc[1];
				int bh= bLoc[0], bw= bLoc[1];
				
//				System.out.println("move before : " + dir + ", red(" + rLoc[0] + ", " + rLoc[1] + "), blue(" + bLoc[0] + ", " + bLoc[1] + ")");
				move(board, rLoc, bLoc, dir);
//				System.out.println("move to : " + dir + ", red(" + rLoc[0] + ", " + rLoc[1] + "), blue(" + bLoc[0] + ", " + bLoc[1] + ")");
//				for(int i=0; i<board.length; i++) {
//					for(int j=0; j<board[i].length; j++) {
//						System.out.print(board[i][j]);
//					}
//					System.out.println();
//				}
//				System.out.println("\n");
				
				if(rMoveHist[rLoc[0]][rLoc[1]] && bMoveHist[bLoc[0]][bLoc[1]]) {
					rLoc[0]= rh;
					rLoc[1]= rw;
					bLoc[0]= bh;
					bLoc[1]= bw;
					continue;
				}
				rMoveHist[rLoc[0]][rLoc[1]]= true;
				bMoveHist[bLoc[0]][bLoc[1]]= true;
				solution(board, rLoc, bLoc, oLoc, cnt+1, rMoveHist, bMoveHist);
				
				rMoveHist[rLoc[0]][rLoc[1]]= false;
				bMoveHist[bLoc[0]][bLoc[1]]= false;
				rLoc[0]= rh;
				rLoc[1]= rw;
				bLoc[0]= bh;
				bLoc[1]= bw;
			}
		}
	}
	
	private void move(char[][] board, int[] rLoc, int[] bLoc, char dir) {
		int rh= rLoc[0], rw= rLoc[1];
		int bh= bLoc[0], bw= bLoc[1];
		
		boolean rMove= true;
		boolean bMove= true;
		while(rMove || bMove) {
			rMove= false;
			bMove= false;
			int pRh= rh;
			int pRw= rw;
			int pBh= bh;
			int pBw= bw;
			
			switch(dir) {
				case 'U':
					if(board[rh-1][rw] != '#') {
						rh--;
						rMove= true;
					}
					if(board[bh-1][bw] != '#') {
						bh--;
						bMove= true;
					}
					break;
				case 'R':
					if(board[rh][rw+1] != '#') {
						rw++;
						rMove= true;
					}
					if(board[bh][bw+1] != '#') {
						bw++;
						bMove= true;
					}
					break;
				case 'D':
					if(board[rh+1][rw] != '#') {
						rh++;
						rMove= true;
					}
					if(board[bh+1][bw] != '#') {
						bh++;
						bMove= true;
					}
					break;
				case 'L':
					if(board[rh][rw-1] != '#') {
						rw--;
						rMove= true;
					}
					if(board[bh][bw-1] != '#') {
						bw--;
						bMove= true;
					}
					break;
			}
			
			if(rh == bh && rw == bw) {
				if(rMove) {
					rh= pRh;
					rw= pRw;
					rMove= false;
				} else {
					bh= pBh;
					bw= pBw;
					bMove= false;
				}
			}
		}
		
		board[rLoc[0]][rLoc[1]]= '.';
		board[bLoc[0]][bLoc[1]]= '.';
		rLoc[0]= rh;
		rLoc[1]= rw;
		bLoc[0]= bh;
		bLoc[1]= bw;
		board[rh][rw]= 'R';
		board[bh][bw]= 'B';
	}
	
	private boolean canMeetHole(char[][] board, int[] loc, int[] oLoc, char dir) {
		int h= loc[0], w= loc[1];
		
		switch(dir) {
			case 'U':
				if(oLoc[0] <= h && oLoc[1] == w) {
					while(h >= 0 && board[h][w] != '#') {
						if(oLoc[0] == h) {
							return true;
						}
						h--;
					}
				}
				return false;
			case 'R':
				if(oLoc[0] == h && oLoc[1] >= w) {
					while(w < board[h].length && board[h][w] != '#') {
						if(oLoc[1] == w) {
							return true;
						}
						w++;
					}
				}
				return false;
			case 'D':
				if(oLoc[0] >= h && oLoc[1] == w) {
					while(h < board.length && board[h][w] != '#') {
						if(oLoc[0] == h) {
							return true;
						}
						h++;
					}
				}
				return false;
			case 'L':
				if(oLoc[0] == h && oLoc[1] <= w) {
					while(w >= 0 && board[h][w] != '#') {
						if(oLoc[1] == w) {
							return true;
						}
						w--;
					}
				}
				return false;
		}
		
		return false;
	}
	
	
	private boolean canMove(char[][] board, int[] rLoc, int[] bLoc, char dir) {
		
		int rh= rLoc[0], rw= rLoc[1], bh= bLoc[0], bw= bLoc[1];
		switch(dir) {
			case 'U':
				rh--;
				bh--;
				break;
			case 'R':
				rw++;
				bw++;
				break;
			case 'D':
				rh++;
				bh++;
				break;
			case 'L':
				rw--;
				bw--;
				break;
		}
		
		if(rh == bLoc[0] && rw == bLoc[1]) {
			return board[bh][bw] != '#';
		}

		return board[rh][rw] != '#';
	}
}
