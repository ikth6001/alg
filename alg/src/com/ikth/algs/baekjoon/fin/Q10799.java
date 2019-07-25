package com.ikth.algs.baekjoon.fin;

import java.util.Scanner;
import java.util.Stack;

/**
 * https://www.acmicpc.net/problem/10799
 */
public class Q10799 {

	public static void main(String[] args) {
		
		try(Scanner scanner= new Scanner(System.in)) {
			
			Q10799 m= new Q10799();
			m.solution(scanner.nextLine());
		}
	}
	
	private void solution(final String data) {
		
		Stack<Character> stack= new Stack<>();
		long sum= 0L; // int를 넘을지 잘 모르겠어서..
		
		for(int i=0; i<data.length(); i++) {
			
			char chr= data.charAt(i);
			
			if(isLazer(data, i)) {
				sum= sum + stack.size();
				i++;
			} else if(chr == '(') {
				stack.push(chr);
			} else if(chr == ')') {
				stack.pop();
				sum++;
			}
		}
		
		System.out.println(sum);
	}
	
	private boolean isLazer(final String data, int start) {
		if(start < data.length() && (start+1) < data.length()) {
			return data.charAt(start) == '(' && data.charAt(start+1) == ')';
		} else {
			return false;
		}
	}
}
