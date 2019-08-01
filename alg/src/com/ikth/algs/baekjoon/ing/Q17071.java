package com.ikth.algs.baekjoon.ing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Q17071 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q17071 m= new Q17071();
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
		
		int[] distances= new int[to*2+1];
		for(int i=0; i<distances.length; i++) {
			distances[i]= -1;
		}
		
		Queue<Status> queue= new LinkedList<>();
		queue.add(new Status(from, 0, to, 1));
		
		while(queue.size() > 0) {
			
			Status status= queue.poll();
			int idx= status.idx;
			int sec= status.sec;
			int incTo= status.incTo;
			int nextInc= status.nextInc;
			int nextIncTo= incTo + nextInc;
			
			if(idx == incTo) {
				bw.write(String.valueOf(sec));
				return;
			}
			
			if(nextIncTo > 500000) {
				continue;
			}
			
			if(idx > 0) {
				queue.add(new Status(idx-1, sec+1, nextIncTo, nextInc + 1));
			}
			
			if(idx < 500000) {
				queue.add(new Status(idx+1, sec+1, nextIncTo, nextInc + 1));
			}
			
			if(idx <= 250000) {
				queue.add(new Status(idx*2, sec+1, nextIncTo, nextInc + 1));
			}
		}
		
		bw.write("-1");
	}
	
	class Status {
		
		int idx;
		int sec;
		int incTo;
		int nextInc;
		
		Status(int idx, int sec, int incTo, int nextInc) {
			this.idx= idx;
			this.sec= sec;
			this.incTo= incTo;
			this.nextInc= nextInc;
		}
	}
}
