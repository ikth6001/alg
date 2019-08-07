package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://www.acmicpc.net/problem/15651
 */
public class Q15651 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q15651 m= new Q15651();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		String[] row= br.readLine().split(" ");
		int n= Integer.valueOf(row[0]);
		int m= Integer.valueOf(row[1]);
		
		process(n, m, 0, new int[m], bw);
	}
	
	private void process(int n, int m, int idx, int[] nums, BufferedWriter bw) throws IOException {
		
		if(idx == m) {
			for(int num : nums) {
				bw.write(String.valueOf(num) + " ");
			}
			bw.write("\n");
			return;
		}
		
		for(int i=1; i<=n; i++) {
			
			nums[idx]= i;
			process(n, m, idx+1, nums, bw);
		}
	}
}
