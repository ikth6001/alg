package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Q01992 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q01992 m= new Q01992();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		int size= Integer.valueOf(br.readLine());
		int[][] screen= new int[size][size];
		
		for(int i=0; i<size; i++) {
			char[] row= br.readLine().toCharArray();
			for(int j=0; j<size; j++) {
				screen[i][j]= row[j] - '0';
			}
		}
		
		bw.write(compress(screen, 0, 0, size, size));
	}
	
	private String compress(int[][] screen, int sh, int sw, int eh, int ew) {
		
		int prev= screen[sh][sw];
		boolean perfect= true;
		for(int i=sh; i<eh; i++) {
			for(int j=sw; j<ew; j++) {
				if(prev != screen[i][j]) {
					perfect= false;
					break;
				}
			}
		}
		
		if(perfect) {
			return String.valueOf(prev);
		}
		
		StringBuilder compress= new StringBuilder();
		int mid= (eh - sh) / 2;
		
		String lt= compress(screen, sh, sw, sh + mid, sw + mid);
		String rt= compress(screen, sh, sw + mid, sh + mid, ew);
		String lb= compress(screen, sh + mid, sw, eh, sw + mid);
		String rb= compress(screen, sh + mid, sw + mid, eh, ew);
		
		compress.append("(")
				.append(lt)
				.append(rt)
				.append(lb)
				.append(rb)
				.append(")");
		
		return compress.toString();
	}
}
