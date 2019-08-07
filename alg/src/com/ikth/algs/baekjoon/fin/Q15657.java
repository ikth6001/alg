package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * https://www.acmicpc.net/problem/15657
 */
public class Q15657 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q15657 m= new Q15657();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		String[] cntRow= br.readLine().split(" ");
		int n= Integer.valueOf(cntRow[0]);
		int m= Integer.valueOf(cntRow[1]);
		
		int[] nums= new int[n];
		String[] numRow= br.readLine().split(" ");
		for(int i=0; i<n; i++) {
			nums[i]= Integer.valueOf(numRow[i]);
		}
		
		Arrays.sort(nums);
		process(nums, m, 0, 0, new int[m], bw);
	}
	
	private void process(int[] nums, int m, int start, int seqIdx, int[] seq, BufferedWriter bw) throws IOException {
		
		if(seqIdx == m) {
			for(int num : seq) {
				bw.write(String.valueOf(num) + " ");
			}
			bw.write("\n");
			return;
		}
		
		for(int i=start; i<nums.length; i++) {
			
			seq[seqIdx]= nums[i];
			process(nums, m, i, seqIdx+1, seq, bw);
		}
	}
}
