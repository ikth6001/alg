package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/1926
 */
public class Q01926 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q01926 m= new Q01926();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		String[] size= br.readLine().split(" ");
		int hLeng= Integer.valueOf(size[0]);
		int wLeng= Integer.valueOf(size[1]);
		
		/** 도화지 */
		int[][] paper= new int[hLeng][wLeng];
		
		/** 방문여부 마킹 */
		int[][] mark= new int[hLeng][wLeng];
		
		for(int i=0; i<hLeng; i++) {
			String[] line= br.readLine().split(" ");
			
			for(int j=0; j<wLeng; j++) {
				paper[i][j]= Integer.valueOf(line[j]);
			}
		}
		
		int colCnt= 0;
		int maxColSize= 0;
		Queue<Location> queue= new LinkedList<Location>();
		for(int i=0; i<hLeng; i++) {
			for(int j=0; j<wLeng; j++) {
				if(paper[i][j] == 0
						|| mark[i][j] == 1) {
					continue;
				}
				
				queue.clear();
				queue.add(new Location(i, j));
				mark[i][j]= 1;
				colCnt++;
				
				int colSize= 1;
				while( queue.size() > 0 ) {
					Location loc= queue.poll();
					int h= loc.h;
					int w= loc.w;
					
					if(h > 0 && paper[h-1][w] == 1 && mark[h-1][w] != 1) {
						queue.add(new Location(h-1, w));
						mark[h-1][w]= 1;
						colSize++;
					}
					if(w < wLeng-1 && paper[h][w+1] == 1 && mark[h][w+1] != 1) {
						queue.add(new Location(h, w+1));
						mark[h][w+1]= 1;
						colSize++;
					}
					if(h < hLeng-1 && paper[h+1][w] == 1 && mark[h+1][w] != 1) {
						queue.add(new Location(h+1, w));
						mark[h+1][w]= 1;
						colSize++;
					}
					if(w > 0 && paper[h][w-1] == 1 && mark[h][w-1] != 1) {
						queue.add(new Location(h, w-1));
						mark[h][w-1]= 1;
						colSize++;
					}
				}
				
				maxColSize= maxColSize < colSize ? colSize : maxColSize;
			}
		}
		
		bw.write(String.valueOf(colCnt));
		bw.write("\n");
		bw.write(String.valueOf(maxColSize));
	}
	
	class Location {
		
		int h;
		int w;
		
		public Location(int h, int w) {
			this.h= h;
			this.w= w;
		}
	}
}
