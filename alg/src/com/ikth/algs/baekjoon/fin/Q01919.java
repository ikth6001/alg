package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://www.acmicpc.net/problem/1919
 */
public class Q01919 {
	
	public static void main(String[] args) {
		
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q01919 m= new Q01919();
			bw.write(String.valueOf(m.solution(br.readLine(), br.readLine())));
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private int solution(String data01, String data02) {
		
		int[] dp01= new int[26];
		int[] dp02= new int[26];
		
		for(char chr : data01.toCharArray()) {
			dp01[chr-'a']++;
		}
		
		for(char chr : data02.toCharArray()) {
			dp02[chr-'a']++;
		}
		
		int sum= 0;
		for(int i=0; i<26; i++) {
			
			sum= sum + Math.abs(dp01[i]-dp02[i]);
		}
		
		return sum;
	}

}
