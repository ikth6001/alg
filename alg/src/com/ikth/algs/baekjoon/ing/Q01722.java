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
		
		int n= Integer.valueOf(br.readLine());
		String[] row= br.readLine().split(" ");
		
		
		switch(row[0]) {
			case "1":
				int[] nums= solution(n, Long.valueOf(row[1]));
				for(int num : nums) {
					bw.write(String.valueOf(num) + " ");
				}
				break;
			case "2":
				/**
				 * TODO
				 */
				break;
		}
	}
	
	/**
	 * case 1.
	 * 
	 * 1. boolean[1..n]을 생성하고 idx=1로 초기화 한다.
	 * 1. (n-1)! 값을 구하여 nf 라고 한다.
	 * 2. nf의 배수가 rank보다 클 때까지 idx++을 한다.
	 * 3. idx를 수열의 숫자로 작성하고 boolean[idx]에 해당 숫자가 사용 되었음을 marking 한다.
	 * 4. nf의 배수 - rank를 한다.
	 * 5. nf의 배수 - rank가 0인 경우는 남은 수열의 자리는 최대 숫자가 되므로 boolean을 n부터 방문하며 사용 안 한 숫자를 하나하나 채워준다.
	 * 6. nf의 배수 - rank가 0이 아닌 경우는 위 1~4를 반복한다.
	 */
	private int[] solution(int n, long rank) {
		
		boolean[] used= new boolean[n];
		long[] facts= factorials(n);
		int[] nums= new int[n];
		int idx= 0;
		
		while(rank >= 0) {
			
			long fact= facts[n-idx-1];
			int cnt= 0;
			
			if(rank == 0L) {
				for(int i=idx; i<nums.length; i++) {
					for(int j=used.length-1; j>=0; j--) {
						if(!used[j]) {
							nums[i]= j+1;
							used[j]= true;
							break;
						}
					}
				}
				break;
			}
			
			long ofact= fact;
			while(fact < rank) {
				fact= fact + ofact;
				cnt++;
			}
			
			int num= getNum(used, cnt);
			used[num-1]= true;
			nums[idx++]= num;
			rank= fact - rank;
		}
		
		return nums;
	}
	
	private int getNum(boolean[] used, int skip) {
		int idx= 0;
		while(true) {
			
			if(used[idx % used.length]) {
				idx++;
				continue;
			} else if(skip-- == 0) {
				return idx+1;
			}
			idx++;
		}
	}
	
	private long[] factorials(int n) {
		long[] factorials= new long[n+1];
		factorials[0]= 1L;
		
		for(int i=1; i<=n; i++) {
			factorials[i]= factorials[i-1] * i;
		}
		
		return factorials;
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
