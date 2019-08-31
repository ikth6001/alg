package com.ikth.algs.baekjoon.ing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://www.acmicpc.net/problem/1722
 */
public class Q01722 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q01722 m= new Q01722();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 당연히 순열을 하나하나 구하므로 시간초과가 나고 있다...
	 * -> 수학적으로 정리를 해야 하는데 정리가 안되네...
	 */
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		
	}
	
//	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
//		
//		n= Integer.valueOf(br.readLine());
//		
//		String[] row= br.readLine().split(" ");
//		
//		int type= 0;
//		long rank= 0;
//		int[] nums= new int[n];
//		
//		switch(row[0]) {
//			case "1":
//				type= 1;
//				for(int i=0; i<n; i++) {
//					nums[i]= i+1;
//				}
//				rank= Long.valueOf(row[1]);
//				break;
//			case "2":
//				type= 2;
//				for(int i=0; i<n; i++) {
//					nums[i]= Integer.valueOf(row[i+1]);
//				}
//				break;
//		}
//		
//		long[] r= solution(new boolean[n], 0, new long[n], type, rank, nums);
//		
//		if(type == 1) {
//			for(long num : r) {
//				bw.write(String.valueOf(num) + " ");
//			}
//		} else {
//			bw.write(String.valueOf(r[0]));
//		}
//	}
//	
//	private int n= 0;
//	private long rank= 0;
//	
//	private long[] solution(boolean[] used, int idx, long[] currentNum, int type, long rank, int[] nums) {
//		
//		if(idx == this.n) {
//			this.rank++;
//			
//			if(type == 1 && this.rank == rank) {
//				return currentNum;
//			} else if(type == 2) {
//				boolean eq= true;
//				
//				for(int i=0; i<n; i++) {
//					if(currentNum[i] != nums[i]) {
//						eq= false;
//						break;
//					}
//				}
//				
//				if(eq) {
//					return new long[] {this.rank};
//				}
//			}
//			return null;
//		}
//		
//		for(int i=0; i<nums.length; i++) {
//			if(used[i]) {
//				continue;
//			}
//			
//			used[i]= true;
//			currentNum[idx]= i+1;
//			long[] r= solution(used, idx+1, currentNum, type, rank, nums);
//			if(r != null) {
//				return r;
//			}
//			used[i]= false;
//		}
//		return null;
//	}
}
