package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Q2468 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q2468 m= new Q2468();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		int size= Integer.valueOf(br.readLine());
		
		int min= Integer.MAX_VALUE;
		int max= Integer.MIN_VALUE;
		int[][] map= new int[size][size];
		boolean[][] mark;
		
		for(int i=0; i<size; i++) {
			String[] row= br.readLine().split(" ");
			for(int j=0; j<size; j++) {
				int h= Integer.valueOf(row[j]);
				map[i][j]= h;
				min= min > h ? h : min;
				max= max < h ? h : max;
			}
		}
		
		Queue<Location> queue= new LinkedList<>();
		int maxCnt= 0;
		for(int height=min; height<max; height++) {

			int cnt= 0;
			mark= new boolean[size][size];
			for(int i=0; i<size; i++) {
				for(int j=0; j<size; j++) {
					if(map[i][j] <= height || mark[i][j]) {
						continue;
					}

					queue.clear();
					queue.add(new Location(i, j));
					mark[i][j]= true;
					cnt++;
					
					while(queue.size() > 0) {
						
						Location loc= queue.poll();
						int h= loc.h;
						int w= loc.w;
						
						if(h > 0 && map[h-1][w] > height && !mark[h-1][w]) {
							queue.add(new Location(h-1, w));
							mark[h-1][w]= true;
						}
						if(w < size-1 && map[h][w+1] > height && !mark[h][w+1]) {
							queue.add(new Location(h, w+1));
							mark[h][w+1]= true;
						}
						if(h < size-1 && map[h+1][w] > height && !mark[h+1][w]) {
							queue.add(new Location(h+1, w));
							mark[h+1][w]= true;
						}
						if(w > 0 && map[h][w-1] > height && !mark[h][w-1]) {
							queue.add(new Location(h, w-1));
							mark[h][w-1]= true;
						}
					}
				}
			}
			
			System.out.println("height ["+height+"], cnt ["+cnt+"]");
			maxCnt= maxCnt < cnt ? cnt : maxCnt;
		}
		
		if(maxCnt == 0) {
			bw.write("1");
		} else {
			bw.write(String.valueOf(maxCnt));
		}
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
