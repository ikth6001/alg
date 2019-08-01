package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/5014
 */
public class Q05014 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q05014 m= new Q05014();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * f : 건물 총 층수
	 * s : 시작 층
	 * g : 목표 층
	 * u : 한번에 올라갈 수 있는 층 수
	 * d : 한번에 내려갈 수 있는 층 수
	 */
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		String[] data= br.readLine().split(" ");
		
		int f= Integer.valueOf(data[0]);
		int s= Integer.valueOf(data[1]);
		int g= Integer.valueOf(data[2]);
		int u= Integer.valueOf(data[3]);
		int d= Integer.valueOf(data[4]);
		
		boolean[] mark= new boolean[f+1];
		Queue<Status> queue= new LinkedList<>();
		queue.add(new Status(s, 0));
		mark[s]= true;
		
		int result= 0;
		while(queue.size() > 0) {
			Status status= queue.poll();
			int idx= status.idx;
			int cnt= status.cnt + 1;
			
			if(idx + u == g
					|| idx - d == g) {
				mark[g]= true;
				result= cnt;
				break;
			}
			if(idx + u <= f && !mark[idx + u]) {
				queue.add(new Status(idx + u, cnt));
				mark[idx + u]= true;
			}
			if(idx - d > 0 && !mark[idx - d]) {
				queue.add(new Status(idx - d, cnt));
				mark[idx - d]= true;
			}
		}
		
		if(mark[g]) {
			bw.write(String.valueOf(result));
		} else {
			bw.write("use the stairs");
		}
	}
	
	class Status {
		int idx;
		int cnt;
		
		public Status(int idx, int cnt) {
			this.idx= idx;
			this.cnt= cnt;
		}
	}
}
