package com.ikth.algs.baekjoon.fin;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/1475
 */
public class Q1475 {

	public static void main(String[] args) {
		
		try(Scanner scanner= new Scanner(System.in)) {
			
			Q1475 m= new Q1475();
			m.solution(scanner.nextLine());
		}
	}
	
	private void solution(String data) {
		
		int[] cnt= new int[10];
		
		for(int i=0; i<data.length(); i++) {
			cnt[data.charAt(i) - '0']++;
		}
		
		cnt[6]+= cnt[9];
		cnt[9]= 0;
		cnt[6]= cnt[6]%2==0?cnt[6]/2:cnt[6]/2+1;
		
		int max= 0;
		for(int i=0; i<cnt.length; i++) {
			if(max < cnt[i]) {
				max= cnt[i];
			}
		}
		
		System.out.println(max);
	}
}
