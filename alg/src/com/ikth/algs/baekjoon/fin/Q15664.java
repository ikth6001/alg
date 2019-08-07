package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Q15664 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q15664 m= new Q15664();
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
		
		Set<String> used= new HashSet<>();
		int[] nums= new int[n];
		String[] numRow= br.readLine().split(" ");
		for(int i=0; i<n; i++) {
			nums[i]= Integer.valueOf(numRow[i]);
		}
		Arrays.sort(nums);
		process(used, new boolean[n], nums, m, "", 0, 0, new int[m], bw);
	}
	
	private void process(Set<String> used
						, boolean[] usedNumIdx
						, int[] nums
						, int m
						, String prevNum
						, int start
						, int seqIdx
						, int[] seq
						, BufferedWriter bw) throws IOException {
		
		if(seqIdx == m) {
			for(int num : seq) {
				bw.write(String.valueOf(num) + " ");
			}
			bw.write("\n");
			return;
		}
		
		for(int i=start; i<nums.length; i++) {
			
			if(usedNumIdx[i]) {
				continue;
			}
			
			seq[seqIdx]= nums[i];
			String prefix= prevNum+String.valueOf(nums[i]);
			if(used.contains(prefix)) {
				continue;
			}
			usedNumIdx[i]= true;
			process(used, usedNumIdx, nums, m, prefix, i+1, seqIdx+1, seq, bw);
			usedNumIdx[i]= false;
			used.add(prefix);
		}
	}
}
