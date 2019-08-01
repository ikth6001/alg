package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Q01874 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q01874 m= new Q01874();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		int cnt= Integer.valueOf(br.readLine());
		
		Stack<Integer> intStack= new Stack<>();
		Queue<Character> charQueue= new LinkedList<Character>();
		int last= 0;
		
		/** num의 최소값은 1 */
		for(int i=1; i<=cnt; i++) {
			
			int num= Integer.valueOf(br.readLine());
			
			if(last < num) {
				
				for(int j=last+1; j<=num; j++) {
					
					intStack.push(j);
					charQueue.add('+');
				}
				last= num;
				intStack.pop();
				charQueue.add('-');
			} else if(last > num
					&& !intStack.isEmpty()
					&& intStack.peek() >= num) {
				
				while(!intStack.isEmpty()) {
					int n= intStack.pop();
					charQueue.add('-');
					if(n == num) {
						break;
					}
				}
			} else {
				System.out.println("NO");
				return;
			}
		}
		
		while(charQueue.size() > 0) {
			
			System.out.println(charQueue.poll());
		}
	}
}
