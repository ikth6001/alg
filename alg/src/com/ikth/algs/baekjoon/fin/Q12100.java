package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/12100
 */
public class Q12100 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q12100 m= new Q12100();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		int n= Integer.valueOf(br.readLine());
		int[][] board= new int[n][n];
		
		for(int i=0; i<n; i++) {
			String[] row= br.readLine().split(" ");
			for(int j=0; j<n; j++) {
				board[i][j]= Integer.valueOf(row[j]);
			}
		}
		
		int max= 0;
		BoardContext start= new BoardContext(board, 0);
		Queue<BoardContext> queue= new LinkedList<>();
		queue.add(start);
		
		while(queue.size() > 0) {
			BoardContext context= queue.poll();
			board= context.board;
			int cnt= context.cnt;
			
			if(cnt == 5) {
				int candidate= getMax(board);
				if(max < candidate) {
					max= candidate;
				}
				continue;
			}
			
			for(int i=1; i<5; i++) {
				context= getMovedContext(board, cnt, i);
				if(context.cnt == cnt + 1) {
					queue.add(context);
				} else { /** 만약 4방향 중 한번도 안움직인 경우에 max값 갱신을 누락시키지 않기 위해서 */
					int candidate= getMax(board);
					if(max < candidate) {
						max= candidate;
					}
				}
			}
		}
		
		bw.write(String.valueOf(max));
	}
	
	private BoardContext getMovedContext(int[][] board, int cnt, int dir) {
		
		BoardContext context= new BoardContext(board, cnt);
		boolean isMove= false;
		switch(dir) { /** 상, 우, 하, 좌 */
			case 1:
				isMove= up(context.board);
				break;
			case 2:
				isMove= right(context.board);
				break;
			case 3:
				isMove= down(context.board);
				break;
			case 4:
				isMove= left(context.board);
				break;
		}
		
		if(isMove) {
			context.cnt++;
		}
		return context;
	}
	
	private boolean left(int[][] board) {
		int n= board.length;
		boolean hasBeenMoved= false;
		boolean[][] mergeRecord= new boolean[n][n];
		
		for(int i=0; i<n; i++) {
			boolean moved= true;
			while(moved) {
				moved= false;
				for(int j=1; j<n; j++) {
					if(board[i][j-1] == 0 && board[i][j] != 0) {
						board[i][j-1]= board[i][j];
						board[i][j]= 0;
						moved= true;
						mergeRecord[i][j-1]= mergeRecord[i][j];
						mergeRecord[i][j]= false;
					} else if(board[i][j-1] == board[i][j] && !mergeRecord[i][j-1] && !mergeRecord[i][j]) {
						board[i][j-1]*= 2;
						board[i][j]= 0;
						mergeRecord[i][j-1]= true;
						moved= true;
					}
				}
				
				hasBeenMoved= hasBeenMoved | moved;
			}
		}
		
		return hasBeenMoved;
	}
	
	private boolean down(int[][] board) {
		int n= board.length;
		boolean hasBeenMoved= false;
		boolean[][] mergeRecord= new boolean[n][n];
		
		for(int i=0; i<n; i++) {
			boolean moved= true;
			while(moved) {
				moved= false;
				for(int j=n-2; j>=0; j--) {
					if(board[j+1][i] == 0 && board[j][i] != 0) {
						board[j+1][i]= board[j][i];
						board[j][i]= 0;
						moved= true;
						mergeRecord[j+1][i]= mergeRecord[j][i];
						mergeRecord[j][i]= false;
					} else if(board[j+1][i] == board[j][i] && !mergeRecord[j+1][i] && !mergeRecord[j][i]) {
						board[j+1][i]*= 2;
						board[j][i]= 0;
						mergeRecord[j+1][i]= true;
						moved= true;
					}
				}
				
				hasBeenMoved= hasBeenMoved | moved;
			}
		}
		
		return hasBeenMoved;
	}
	
	private boolean right(int[][] board) {
		int n= board.length;
		boolean hasBeenMoved= false;
		boolean[][] mergeRecord= new boolean[n][n];
		
		for(int i=0; i<n; i++) {
			boolean moved= true;
			while(moved) {
				moved= false;
				for(int j=n-2; j>=0; j--) {
					if(board[i][j+1] == 0 && board[i][j] != 0) {
						board[i][j+1]= board[i][j];
						board[i][j]= 0;
						moved= true;
						mergeRecord[i][j+1]= mergeRecord[i][j];
						mergeRecord[i][j]= false;
					} else if(board[i][j+1] == board[i][j] && !mergeRecord[i][j+1] && !mergeRecord[i][j]) {
						board[i][j+1]*= 2;
						board[i][j]= 0;
						mergeRecord[i][j+1]= true;
						moved= true;
					}
				}
				
				hasBeenMoved= hasBeenMoved | moved;
			}
		}
		
		return hasBeenMoved;
	}
	
	private boolean up(int[][] board) {
		int n= board.length;
		boolean hasBeenMoved= false;
		boolean[][] mergeRecord= new boolean[n][n];
		
		for(int i=0; i<n; i++) {
			boolean moved= true;
			while(moved) {
				moved= false;
				for(int j=1; j<n; j++) {
					if(board[j-1][i] == 0 && board[j][i] != 0) {
						board[j-1][i]= board[j][i];
						board[j][i]= 0;
						moved= true;
						mergeRecord[j-1][i]= mergeRecord[j][i];
						mergeRecord[j][i]= false;
					} else if(board[j-1][i] == board[j][i] && !mergeRecord[j-1][i] && !mergeRecord[j][i]) {
						board[j-1][i]*= 2;
						board[j][i]= 0;
						mergeRecord[j-1][i]= true;
						moved= true;
					}
				}
				
				hasBeenMoved= hasBeenMoved | moved;
			}
		}
		
		return hasBeenMoved;
	}
	
	private int getMax(int[][] board) {
		int max= 0;
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++) {
				if(max < board[i][j]) {
					max= board[i][j];
				}
			}
		}
		return max;
	}
	
	class BoardContext {
		
		int[][] board;
		int cnt;
		
		public BoardContext(int[][] board, int cnt) {
			int[][] clone= new int[board.length][];
			for(int i=0; i<board.length; i++) {
				int[] row= Arrays.copyOf(board[i], board[i].length);
				clone[i]= row;
			}
			
			this.board= clone;
			this.cnt= cnt;
		}
	}
}
