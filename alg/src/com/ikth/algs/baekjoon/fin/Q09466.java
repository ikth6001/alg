package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Q09466 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q09466 m= new Q09466();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		int testCnt= Integer.valueOf(br.readLine());
		Queue<Integer> queue= new LinkedList<>();
		
		for(int i=0; i<testCnt; i++) {
			
			int studentCnt= Integer.valueOf(br.readLine());
			int[] group= new int[studentCnt+1];
			boolean[] decide= new boolean[studentCnt+1];
			boolean[] inGrp= new boolean[studentCnt+1];
			
			String[] data= br.readLine().split(" ");
			for(int j=1; j<=studentCnt; j++) {
				group[j]= Integer.valueOf(data[j-1]);
			}
			
			int cnt= 0;
			for(int j=1; j<=studentCnt; j++) {
				
				if( decide[j] || inGrp[j] ) {
					continue;
				}
				
				queue.clear();
				int student= j;
				while(true) {
					if(decide[student] || inGrp[student]) {
						break;
					}
					
					queue.add(student);
					inGrp[student]= true;
					student= group[student];
				}

				while(queue.size() > 0 && queue.peek() != student) {
					int idx= queue.poll();
					inGrp[idx]= false;
					decide[idx]= true;
					cnt++;
				}
			}
			
			bw.write(String.valueOf(cnt));
			bw.write("\n");
		}
	}
}
