package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/13913
 */
public class Q13913 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q13913 m= new Q13913();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		String[] data= br.readLine().split(" ");
		int from= Integer.valueOf(data[0]);
		int to= Integer.valueOf(data[1]);
		
		if(to <= from) {
			bw.write(String.valueOf(from-to));
			bw.write("\n");
			for(int i=from; i>=to; i--) {
				if(i == from) {
					bw.write(String.valueOf(from));
				} else {
					bw.write(" " + String.valueOf(i));
				}
			}
			return;
		}
		
		int[] distances= new int[to*2+1];
		for(int i=0; i<distances.length; i++) {
			distances[i]= Integer.MAX_VALUE;
		}
		Queue<Status> queue= new LinkedList<>();
		queue.add(new Status(from, String.valueOf(from)));
		distances[from]= 0;
		String trace= null;
		
		while(queue.size() > 0) {
			
			Status status= queue.poll();
			int idx= status.idx;
			String prevTrace= status.trace;
			int d= distances[idx] + 1;
			
			if(idx - 1 == to
					|| idx + 1 == to
					|| idx * 2 == to) {
				trace= prevTrace + " " + String.valueOf(to);
				distances[to]= d;
				break;
			}
			
			if(idx > to) {
				if(d < distances[idx-1]) {
					queue.add(new Status(idx-1, prevTrace + " " + String.valueOf(idx-1)));
					distances[idx-1]= d;
				}
			} else {
				if(idx > 0 && d < distances[idx-1]) {
					queue.add(new Status(idx-1, prevTrace + " " + String.valueOf(idx-1)));
					distances[idx-1]= d;
				}
				
				if(d < distances[idx+1]) {
					queue.add(new Status(idx+1, prevTrace + " " + String.valueOf(idx+1)));
					distances[idx+1]= d;
				}
				
				if(d < distances[idx*2]) {
					queue.add(new Status(idx*2, prevTrace + " " + String.valueOf(idx*2)));
					distances[idx*2]= d;
				}
			}
		}
		
		bw.write(String.valueOf(distances[to]));
		bw.write("\n");
		bw.write(trace);
	}
	
	class Status {
		
		int idx;
		String trace;
		
		Status(int idx, String trace) {
			this.idx= idx;
			this.trace= trace;
		}
	}
}
