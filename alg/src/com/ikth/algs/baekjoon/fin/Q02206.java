package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Q02206 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q02206 m= new Q02206();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		String[] mapSize= br.readLine().split(" ");
		int hLeng= Integer.valueOf(mapSize[0]);
		int wLeng= Integer.valueOf(mapSize[1]);
		
		int[][] map= new int[hLeng][wLeng];
		boolean[][][] status= new boolean[hLeng][wLeng][2];
		for(int i=0; i<hLeng; i++) {
			char[] row= br.readLine().toCharArray();
			for(int j=0; j<wLeng; j++) {
				map[i][j]= row[j]-'0';
				status[i][j][1]= true;
			}
		}
		
		Queue<Context> queue= new LinkedList<>();
		queue.add(new Context(0, 0, 1, true));
		status[0][0][0]= true;
		int distance= Integer.MAX_VALUE;
		
		while(queue.size() > 0) {
			
			Context context= queue.poll();
			
			int h= context.h;
			int w= context.w;
			int d= context.d;
			
			if(h == hLeng-1
					&& w == wLeng -1
					&& distance > d) {
				distance= d;
			}
			
			boolean hasChance= context.chance;
			
			if(h > 0) {
				if(status[h-1][w][0]) {
					if(hasChance && !status[h-1][w][1]) {
						queue.add(new Context(h-1, w, d+1, hasChance));
						status[h-1][w][1]= hasChance;
					}
				} else if(map[h-1][w] == 1) {
					if(hasChance) {
						queue.add(new Context(h-1, w, d+1, false));
						status[h-1][w][1]= false;
					}
				} else {
					queue.add(new Context(h-1, w, d+1, hasChance));
					status[h-1][w][0]= true;
					status[h-1][w][1]= hasChance;
				}
			}
			if(w < wLeng-1) {
				if(status[h][w+1][0]) {
					if(hasChance && !status[h][w+1][1]) {
						queue.add(new Context(h, w+1, d+1, hasChance));
						status[h][w+1][1]= hasChance;
					}
				} else if(map[h][w+1] == 1) {
					if(hasChance) {
						queue.add(new Context(h, w+1, d+1, false));
						status[h][w+1][1]= false;
					}
				} else {
					queue.add(new Context(h, w+1, d+1, hasChance));
					status[h][w+1][0]= true;
					status[h][w+1][1]= hasChance;
				}
			}
			if(h < hLeng-1) {
				if(status[h+1][w][0]) {
					if(hasChance && !status[h+1][w][1]) {
						queue.add(new Context(h+1, w, d+1, hasChance));
						status[h+1][w][1]= hasChance;
					}
				} else if(map[h+1][w] == 1) {
					if(hasChance) {
						queue.add(new Context(h+1, w, d+1, false));
						status[h+1][w][1]= false;
					}
				} else {
					queue.add(new Context(h+1, w, d+1, hasChance));
					status[h+1][w][0]= true;
					status[h+1][w][1]= hasChance;
				}
			}
			if(w > 0) {
				if(status[h][w-1][0]) {
					if(hasChance && !status[h][w-1][1]) {
						queue.add(new Context(h, w-1, d+1, hasChance));
						status[h][w-1][1]= hasChance;
					}
				} else if(map[h][w-1] == 1) {
					if(hasChance) {
						queue.add(new Context(h, w-1, d+1, false));
						status[h][w-1][1]= false;
					}
				} else {
					queue.add(new Context(h, w-1, d+1, hasChance));
					status[h][w-1][0]= true;
					status[h][w-1][1]= hasChance;
				}
			}
		}
		
		if(status[hLeng-1][wLeng-1][0]) {
			bw.write(String.valueOf(distance));
		} else {
			bw.write("-1");
		}
	}
	
	class Context {
		
		int h;
		int w;
		int d;
		boolean chance;
		
		public Context(int h, int w, int d, boolean chance) {
			this.h= h;
			this.w= w;
			this.d= d;
			this.chance= chance;
		}
	}
}
