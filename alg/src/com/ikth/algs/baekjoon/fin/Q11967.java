package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Q11967 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q11967 m= new Q11967();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		String[] data= br.readLine().split(" ");
		int size= Integer.valueOf(data[0]);
		int cnt= Integer.valueOf(data[1]);
		
		int[][] room= new int[size+1][size+1];
		boolean[][] mark= new boolean[size+1][size+1];
		Map<Location, List<Location>> switchMap= new HashMap<>();
		
		for(int i=0; i<cnt; i++) {
			String[] row= br.readLine().split(" ");
			int a= Integer.valueOf(row[0]);
			int b= Integer.valueOf(row[1]);
			int c= Integer.valueOf(row[2]);
			int d= Integer.valueOf(row[3]);
			
			Location from= new Location(a, b);
			if(switchMap.containsKey(from)) {
				switchMap.get(from).add(new Location(c, d));
			} else {
				switchMap.put(from, new ArrayList<>());
				switchMap.get(from).add(new Location(c, d));
			}
		}

		Queue<Location> queue= new LinkedList<>();
		boolean[][] preMark= new boolean[size+1][size+1];
		
		while(true) {
			queue.add(new Location(1, 1));
			mark= new boolean[size+1][size+1];
			room[1][1]= 1;
			mark[1][1]= true;
			boolean visitNew= false;
			
			while(queue.size() > 0) {
				Location loc= queue.poll();
				List<Location> switches= switchMap.get(loc);
				if(switches != null) {
					for(Location s : switches) {
						int h= s.h;
						int w= s.w;
						room[h][w]= 1;
					}
				}
				
				int h= loc.h;
				int w= loc.w;
				
				if(h > 1 && room[h-1][w] == 1 && !mark[h-1][w]) {
					queue.add(new Location(h-1, w));
					mark[h-1][w]= true;
					if(!preMark[h-1][w]) {
						visitNew= true;
					}
				}
				if(w < size && room[h][w+1] ==1  && !mark[h][w+1]) {
					queue.add(new Location(h, w+1));
					mark[h][w+1]= true;
					if(!preMark[h][w+1]) {
						visitNew= true;
					}
				}
				if(h < size && room[h+1][w] == 1 && !mark[h+1][w]) {
					queue.add(new Location(h+1, w));
					mark[h+1][w]= true;
					if(!preMark[h+1][w]) {
						visitNew= true;
					}
				}
				if(w > 1 && room[h][w-1] == 1 && !mark[h][w-1]) {
					queue.add(new Location(h, w-1));
					mark[h][w-1]= true;
					if(!preMark[h][w-1]) {
						visitNew= true;
					}
				}
			}
			if(visitNew) {
				preMark= mark;
			} else {
				break;
			}
		}
		
		cnt= 0;
		for(int i=1; i<=size; i++) {
			for(int j=1; j<=size; j++) {
				if(room[i][j] == 1) {
					cnt++;
				}
			}
		}
		
		bw.write(String.valueOf(cnt));
	}
	
	class Location {
		
		int h;
		int w;
		
		public Location(int h, int w) {
			this.h= h;
			this.w= w;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Location) {
				Location loc= (Location) obj;
				return this.h==loc.h&&this.w==loc.w;
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			return h*10+w;
		}
	}
}
