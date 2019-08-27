package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/11559
 */
public class Q11559 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q11559 m= new Q11559();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		char[][] map= new char[12][];
		for(int i=0; i<12; i++) {
			String row= br.readLine();
			map[i]= row.toCharArray();
		}
		
//		bomb(map);
//		for(int i=0; i<12; i++) {
//			for(int j=0; j<6; j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
//		System.out.println("---------------------");
//		compaction(map);
//		for(int i=0; i<12; i++) {
//			for(int j=0; j<6; j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
		
		int cnt= 0;
		while(bomb(map)) {
			compaction(map);
			cnt++;
		}
		
		bw.write(String.valueOf(cnt));
	}
	
	private boolean bomb(char[][] map) {
		boolean isBombed= false;
		boolean[][] visit= new boolean[12][6];
		Queue<int[]> queue= new LinkedList<>();
		Queue<int[]> tmp= new LinkedList<>();
		
		for(int i=0; i<12; i++) {
			for(int j=0; j<6; j++) {
				if(map[i][j] == '.' || visit[i][j]) {
					continue;
				}
				
				char color= map[i][j];
				visit[i][j]= true;
				queue.add(new int[] {i, j} );
				
				while(queue.size() > 0) {
					int[] loc= queue.poll();
					tmp.add(loc);
					int h= loc[0];
					int w= loc[1];
					
					if(h > 0 && map[h-1][w] == color && !visit[h-1][w]) {
						visit[h-1][w]= true;
						queue.add(new int[] {h-1, w});
					}
					if(h < 11 && map[h+1][w] == color && !visit[h+1][w]) {
						visit[h+1][w]= true;
						queue.add(new int[] {h+1, w});
					}
					if(w > 0 && map[h][w-1] == color && !visit[h][w-1]) {
						visit[h][w-1]= true;
						queue.add(new int[] {h, w-1});
					}
					if(w < 5 && map[h][w+1] == color && !visit[h][w+1]) {
						visit[h][w+1]= true;
						queue.add(new int[] {h, w+1});
					}
				}
				
				if(tmp.size() > 3) {
					isBombed= true;
					while(tmp.size() > 0) {
						int[] loc= tmp.poll();
						int h= loc[0];
						int w= loc[1];
						map[h][w]= '.';
					}
				}
				tmp.clear();
			}
		}
		
		return isBombed;
	}
	
	private void compaction(char[][] map) {
		for(int j=0; j<6; j++) {
			int h=11;
			
			boolean empty= false;
			int emptyH= 0;
			
			while(h >= 0) {
				if(map[h][j] == '.' && !empty) {
					empty= true;
					emptyH= h;
				} else if(empty && map[h][j] != '.') {
					int dif= emptyH - h;
					
					for(int i=h; i>=0; i--) {
						map[i+dif][j]= map[i][j];
					}
					for(int i=dif-1; i>=0; i--) {
						map[i][j]= '.';
					}
					empty= false;
				}
				
				h--;
			}
		}
	}
}
