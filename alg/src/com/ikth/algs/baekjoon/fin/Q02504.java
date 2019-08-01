package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Q02504 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q02504 m= new Q02504();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		String data= br.readLine();
		if( !isCorrectBracket(data) ) {
			bw.write("0");
			return;
		}
		
		Stack<Long> stack= new Stack<>();
		for(int i=0; i<data.length(); i++) {
			
			char chr= data.charAt(i);
			long sum= 0L;
			
			switch(chr) {
				case '(':
					stack.add(0L);
					break;
				case '[':
					stack.add(1L);
					break;
				case ')':
					sum= 0L;
					while(true) {
						long num= stack.pop();
						if(num == 0) {
							break;
						} else {
							sum= sum + num;
						}
					}
			
					sum= sum == 0L ? 1L : sum;
					stack.push(sum * 2);
					break;
				case ']':
					sum= 0L;
					while(true) {
						long num= stack.pop();
						if(num == 1) {
							break;
						} else {
							sum= sum + num;
						}
					}
			
					sum= sum == 0L ? 1L : sum;
					stack.push(sum * 3);
					break;
			}
		}
		
		long sum= 0L;
		while(stack.size() > 0) {
			sum= sum + stack.pop();
		}
		
		bw.write(String.valueOf(sum));
	}
	
	private boolean isCorrectBracket(String data) {
		
		Stack<Character> stack= new Stack<>();
		for(int i=0; i<data.length(); i++) {
			
			char chr= data.charAt(i);
			switch(chr) {
				case '(':
				case '[':
					stack.add(chr);
					break;
				case ')':
					if(stack.isEmpty()
							|| stack.pop() != '(') {
						return false;
					}
					break;
				case ']':
					if(stack.isEmpty()
							|| stack.pop() != '[') {
						return false;
					}
					break;
			}
		}
		
		return stack.isEmpty();
	}
}
