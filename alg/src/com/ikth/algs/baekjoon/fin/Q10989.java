package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://www.acmicpc.net/problem/10989
 */
public class Q10989 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q10989 m= new Q10989();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		int n= Integer.valueOf(br.readLine());
		int[] nums= new int[n];
		
		for(int i=0; i<n; i++) {
			nums[i]= Integer.valueOf(br.readLine());
		}
		
//		divConqSort(nums);
		countingSort(nums);
		for(int num : nums) {
			bw.write(String.valueOf(num));
			bw.newLine();
		}
	}
	
	/**
	 * 검색할 생각 없었는데.. 질문글 읽다보니 기수정렬 알아버렸네..
	 * 값의 범위가 10000 미만이란 점을 활용한 방법
	 */
	private void countingSort(int[] nums) {
		
		int[] tmp= new int[10001];
		
		for(int i=0; i<nums.length; i++) {
			tmp[nums[i]]++;
		}
		
		int idx= 0;
		for(int i=0; i<10001; i++) {
			for(int j=0; j<tmp[i]; j++) {
				nums[idx++]= i;
			}
		}
	}
	
	/**
	 * 단순히 병합정렬을 사용하면 ln(N)인데.. 시간초과 난다.
	 */
	@SuppressWarnings("unused")
	private void divConqSort(int[] nums) {
		if(nums.length == 1) {
			return;
		} else if(nums.length == 2) {
			if(nums[0] > nums[1]) {
				int tmp= nums[0];
				nums[0]= nums[1];
				nums[1]= tmp;
			}
			return;
		}
		
		int leng= nums.length;
		int mid= leng/2;
		
		int[] left= new int[mid];
		int[] right= new int[leng-mid];
		System.arraycopy(nums, 0, left, 0, mid);
		System.arraycopy(nums, mid, right, 0, leng-mid);
		
		divConqSort(left);
		divConqSort(right);

		int lIdx= 0;
		int rIdx= 0;
		
		for(int i=0; i<leng; i++) {
			
			int num= 0;
			if(left[lIdx] < right[rIdx]) {
				num= left[lIdx++];
			} else {
				num= right[rIdx++];
			}
			nums[i]= num;
			
			if(lIdx == left.length) {
				for(int j=i+1; j<leng; j++) {
					nums[j]= right[rIdx++];
				}
				break;
			} else if(rIdx == right.length) {
				for(int j=i+1; j<leng; j++) {
					nums[j]= left[lIdx++];
				}
				break;
			}
		}
	}
}
