package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Q07576 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q07576 m= new Q07576();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		String[] boxSize= br.readLine().split(" ");
		int wLeng= Integer.valueOf(boxSize[0]);
		int hLeng= Integer.valueOf(boxSize[1]);
		
		int[][] box= new int[hLeng][wLeng];
		boolean[][] ripe= new boolean[hLeng][wLeng];
		int[][] days= new int[hLeng][wLeng];

		Queue<Location> queue= new LinkedList<>();
		for(int i=0; i<hLeng; i++) {
			String[] row= br.readLine().split(" ");
			for(int j=0; j<wLeng; j++) {
				box[i][j]= Integer.valueOf(row[j]);
				
				if(box[i][j] == 1) {
					days[i][j]= 0;
					ripe[i][j]= true;
					queue.add(new Location(i, j));
				} else {
					if(box[i][j] == -1) {
						ripe[i][j]= true;
					}
					days[i][j]= -1;
				}
			}
		}
		
				
		while(queue.size() > 0) {
			Location loc= queue.poll();
			int h= loc.h;
			int w= loc.w;
			int d= days[h][w] + 1;
			ripe[h][w]= true;
			
			if(h > 0 && box[h-1][w] == 0 && (days[h-1][w] == -1 || days[h-1][w] > d)) {
				queue.add(new Location(h-1, w));
				days[h-1][w]= d;
			}
			if(w < wLeng-1 && box[h][w+1] == 0 && (days[h][w+1] == -1 || days[h][w+1] > d)) {
				queue.add(new Location(h, w+1));
				days[h][w+1]= d;
			}
			if(h < hLeng-1 && box[h+1][w] == 0 && (days[h+1][w] == -1 || days[h+1][w] > d)) {
				queue.add(new Location(h+1, w));
				days[h+1][w]= d;
			}
			if(w > 0 && box[h][w-1] == 0 && (days[h][w-1] == -1 || days[h][w-1] > d)) {
				queue.add(new Location(h, w-1));
				days[h][w-1]= d;
			}
		}
		
		int maxDay= 0;
		boolean isRipe= true;
		for(int i=0; i<hLeng; i++) {
			for(int j=0; j<wLeng; j++) {
				if(days[i][j] > maxDay) {
					maxDay= days[i][j];
				}
				isRipe= ripe[i][j] & isRipe;
			}
		}
		
		if(isRipe) {
			bw.write(String.valueOf(maxDay));
		} else {
			bw.write("-1");
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
