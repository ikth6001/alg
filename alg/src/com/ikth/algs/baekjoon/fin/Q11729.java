package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * https://www.acmicpc.net/problem/11729
 */
public class Q11729 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q11729 m= new Q11729();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<String> traces;
	private int cnt;
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		int size= Integer.valueOf(br.readLine());
		traces= new ArrayList<>();
		doHanoi(1, 2, 3, size);
		
		bw.write(String.valueOf(cnt) + "\n");
		for(String trace : traces) {
			bw.write(trace + "\n");
		}
	}
	
	private void doHanoi(int from, int mid, int to, int size) {
		if(size == 1) {
			traces.add(from + " " + to);
		} else {
			doHanoi(from, to, mid, size-1);
			traces.add(from + " " + to);
			doHanoi(mid, from, to, size-1);
		}
		cnt++;
	}
}
