package com.ikth.algs.baekjoon.ing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/1697
 */
public class Q1697 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q1697 m= new Q1697();
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
			return;
		}
		
		int[] distances= new int[to*2+1];
		for(int i=0; i<distances.length; i++) {
			distances[i]= Integer.MAX_VALUE;
		}
		Queue<Integer> queue= new LinkedList<>();
		queue.add(from);
		distances[from]= 0;
		
		while(queue.size() > 0) {
			
			int pos= queue.poll();
			int d= distances[pos] + 1;
			
			if(pos > to) {
				queue.add(pos-1);
				distances[pos-1]= d < distances[pos-1] ? d : distances[pos-1];
			} else {
				if(pos > 0) {
					queue.add(pos-1);
					distances[pos-1]= d < distances[pos-1] ? d : distances[pos-1];
				}

				queue.add(pos+1);
				distances[pos+1]= d < distances[pos+1] ? d : distances[pos+1];
				
				queue.add(pos*2);
				distances[pos*2]= d < distances[pos*2] ? d : distances[pos*2];
			}
		}
		
		bw.write(String.valueOf(distances[to]));
	}
}
