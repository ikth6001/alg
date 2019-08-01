package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Q02493 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			Q02493 m= new Q02493();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		int cnt= Integer.valueOf(br.readLine());
		int[] bf= new int[cnt];
		String[] tops= br.readLine().split(" ");
		int[] nTops= new int[cnt];
		
		for(int i=0; i<cnt; i++) {
			nTops[i]= Integer.valueOf(tops[i]);
		}
		
		for(int i=tops.length-1; i>=0; i--) {
			for(int j=i-1; j>=0; j--) {
				if(nTops[j] > nTops[i]) {
					bf[i]= j+1;
					break;
				}
			}
		}
		
		for(int n : bf) {
			bw.write(String.valueOf(n));
			bw.write(" ");
		}
	}
}
