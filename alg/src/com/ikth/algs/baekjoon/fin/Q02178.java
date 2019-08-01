package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/2178
 */
public class Q02178 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q02178 m= new Q02178();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		String[] mazeSize= br.readLine().split(" ");
		int hLeng= Integer.valueOf(mazeSize[0]);
		int wLeng= Integer.valueOf(mazeSize[1]);
		
		char[][] maze= new char[hLeng][];
		int[][] distances= new int[hLeng][wLeng];
		for(int i=0; i<hLeng; i++) {
			maze[i]= br.readLine().toCharArray();
			for(int j=0; j<wLeng; j++) {
				distances[i][j]= Integer.MAX_VALUE;
			}
		}
		
		Queue<Location> queue= new LinkedList<Location>();
		queue.add(new Location(0, 0));
		distances[0][0]= 1;
		
		while(queue.size() > 0) {
			
			Location loc= queue.poll();
			int h= loc.h;
			int w= loc.w;
			int d= distances[h][w] + 1;
			
			if(h > 0 && maze[h-1][w] == '1' && distances[h-1][w] > d) {
				queue.add(new Location(h-1, w));
				distances[h-1][w]= d;
			}
			if(w < wLeng-1 && maze[h][w+1] == '1' && distances[h][w+1] > d) {
				queue.add(new Location(h, w+1));
				distances[h][w+1]= d;
			}
			if(h < hLeng-1 && maze[h+1][w] == '1' && distances[h+1][w] > d) {
				queue.add(new Location(h+1, w));
				distances[h+1][w]= d;
			}
			if(w > 0 && maze[h][w-1] == '1' && distances[h][w-1] > d) {
				queue.add(new Location(h, w-1));
				distances[h][w-1]= d;
			}
		}
		
		bw.write(String.valueOf(distances[hLeng-1][wLeng-1]));
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
