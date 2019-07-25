package com.ikth.algs.baekjoon.ing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Q1874 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q1874 m= new Q1874();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		int cnt= Integer.valueOf(br.readLine());
		
		Stack<Integer> intStack= new Stack<>();
		Stack<Character> charStack= new Stack<>();
		int last= 0;
		
		for(int i=1; i<=cnt; i++) {
			
			int num= Integer.valueOf(br.readLine());
			if(num > last) {
				for(int j= last+1; j<= num; j++) {
					intStack.push(j);
					charStack.push("+\n");
					intStack.pop();
					bw.write("-\n");
				}
				last= num;
			} else if(num == stack.peek()) {
				stack.pop();
				bw.write("-\n");
			} else {
				
			}
		}
	}
}
