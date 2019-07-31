package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Q2146 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q2146 m= new Q2146();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		int size= Integer.valueOf(br.readLine());
		int[][] map= new int[size][size];
		boolean[][] mark= new boolean[size][size];
		
		for(int i=0; i<size; i++) {
			String[] row= br.readLine().split(" ");
			for(int j=0; j<size; j++) {
				map[i][j]= Integer.valueOf(row[j]);
			}
		}
		
		Queue<Location> queue= new LinkedList<>();
		Map<Integer, Set<Location>> enableLocMap= new HashMap<>();
		int id= 2;
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(map[i][j] != 1 || mark[i][j]) {
					continue;
				}
				
				queue.clear();
				queue.add(new Location(i, j));
				mark[i][j]= true;
				
				Set<Location> enableLoc= new HashSet<>();
				while(queue.size() > 0) {
					Location loc= queue.poll();
					int h= loc.h;
					int w= loc.w;
					
					if(h > 0 && map[h-1][w] == 1 && !mark[h-1][w]) {
						queue.add(new Location(h-1, w));
						mark[h-1][w]= true;
					} else if(h > 0 && map[h-1][w] == 0) {
						enableLoc.add(new Location(h-1, w));
//						map[h-1][w]= id;
					}
					if(w < size-1 && map[h][w+1] == 1 && !mark[h][w+1]) {
						queue.add(new Location(h, w+1));
						mark[h][w+1]= true;
					} else if(w < size-1 && map[h][w+1] == 0) {
						enableLoc.add(new Location(h, w+1));
//						map[h][w+1]= id;
					}
					if(h < size-1 && map[h+1][w] == 1 && !mark[h+1][w]) {
						queue.add(new Location(h+1, w));
						mark[h+1][w]= true;
					} else if(h < size-1 && map[h+1][w] == 0) {
						enableLoc.add(new Location(h+1, w));
//						map[h+1][w]= id;
					}
					if(w > 0 && map[h][w-1] == 1 && !mark[h][w-1]) {
						queue.add(new Location(h, w-1));
						mark[h][w-1]= true;
					} else if(w > 0 && map[h][w-1] == 0) {
						enableLoc.add(new Location(h, w-1));
//						map[h][w-1]= id;
					}
				}
				
				enableLocMap.put(id++, enableLoc);
			}
		}
		
		Location minFrom= null;
		Location minTo= null;
		double min= Double.MAX_VALUE;
		
		for(int i=2; i<id; i++) {
			Set<Location> froms= enableLocMap.get(i);
			for(int j=i+1; j<id; j++) {
				Set<Location> tos= enableLocMap.get(j);
				
				for(Location from : froms) {
					for(Location to : tos) {
						double dist= getDistance(from, to);
						if(dist < min) {
							min= dist;
							minFrom= from;
							minTo= to;
						}
					}
				}
			}
		}
		
		int dist= Math.abs(minTo.h - minFrom.h) + Math.abs(minTo.w - minFrom.w) + 1;
		bw.write(String.valueOf(dist));
	}
	
	private double getDistance(Location from, Location to) {
		int fh= from.h;
		int fw= from.w;
		int th= to.h;
		int tw= to.w;
		
		int hDist= Math.abs(th - fh);
		int wDist= Math.abs(tw - fw);
		
		return Math.sqrt(hDist * hDist + wDist * wDist);
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
				return this.h == loc.h && this.w == loc.w;
			}
			
			return false;
		}
		
		@Override
		public int hashCode() {
			return "h".hashCode() + h + "w".hashCode() + w;
		}
	}
}
