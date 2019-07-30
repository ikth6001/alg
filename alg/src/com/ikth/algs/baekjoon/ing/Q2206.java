package com.ikth.algs.baekjoon.ing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Q2206 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q2206 m= new Q2206();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		String[] mapSize= br.readLine().split(" ");
		int hLeng= Integer.valueOf(mapSize[0]);
		int wLeng= Integer.valueOf(mapSize[1]);
		
		int[][] map= new int[hLeng][wLeng];
		for(int i=0; i<hLeng; i++) {
			char[] row= br.readLine().toCharArray();
			for(int j=0; j<wLeng; j++) {
				map[i][j]= row[j]-'0';
			}
		}
		
		for(int i=0; i<hLeng; i++) {
			for(int j=0; j<wLeng; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
