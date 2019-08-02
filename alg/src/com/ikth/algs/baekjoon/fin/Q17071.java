package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/17071
 */
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
	
	/**
	 * 몰라서 블로그 참고 > https://twogrenade.tistory.com/67
	 * 동생보다 더 뒤의 위치에 있는 경우 +1, -1을 하면(2초 후) 해당 자리에 계속 있을 수 있다는 점을 이용
	 */
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		String[] data= br.readLine().split(" ");
		int from= Integer.valueOf(data[0]);
		int to= Integer.valueOf(data[1]);
		
		int[][] times= new int[2][500004]; // 왜 500004가 최대치 인지는 모르겠음
		for(int i=0; i<500004; i++) {
			times[0][i]= -1;
			times[1][i]= -1;
		}
		
		Queue<Status> queue= new LinkedList<>();
		queue.add(new Status(from, 0));
		times[0][from]= 0;
		
		
		while(queue.size() > 0) {
			
			Status status= queue.poll();
			int idx= status.idx;
			int sec= status.sec + 1;
			
			if(idx > 0 && times[sec%2][idx-1] == -1) {
				queue.add(new Status(idx-1, sec));
				times[sec%2][idx-1]= sec;
			}
			
			if(idx < 500000 && times[sec%2][idx+1] == -1) {
				queue.add(new Status(idx+1, sec));
				times[sec%2][idx+1]= sec;
			}
			
			if(idx <= 250000 && times[sec%2][idx*2] == -1) {
				queue.add(new Status(idx*2, sec));
				times[sec%2][idx*2]= sec;
			}
		}
		
		int min= Integer.MAX_VALUE;
		int sec= 0;
		while(to <= 500000) {
			
			if(times[sec%2][to] >= 0 && times[sec%2][to] <= sec) {
				min= min < sec ? min : sec;
			}
			
			sec++;
			to= to + sec;
		}
		
		if(min == Integer.MAX_VALUE) {
			bw.write("-1");
		} else {
			bw.write(String.valueOf(min));
		}
	}
	
	class Status {
		
		int idx;
		int sec;
		
		Status(int idx, int sec) {
			this.idx= idx;
			this.sec= sec;
		}
	}
}
