package com.ikth.algs.baekjoon.ing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

/**
 * https://www.acmicpc.net/problem/5397
 */
public class Q05397 {

	/**
	 * Scanner로 하면 시간 초과가 뜸. 앞으로는 BufferedReader 및 BufferedWriter를 쓰도록 습관을 갖자..
	 */
	public static void main(String[] args) {
		
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q05397 m= new Q05397();
			
			int cnt= Integer.valueOf(br.readLine());
			for(int i=0; i<cnt; i++) {
				
				bw.write(m.solution(br.readLine()));
				bw.write("\n");
			}
			bw.flush();
		} catch (IOException e) {
			/**
			 * ignore
			 */
		}
	}
	
	private String solution(String data) {
		
		int cursor= 0;
		
		final int LEFT= '<';
		final int RIGHT= '>';
		final int BACK= '-';
		
		List<Character> password= new LinkedList<>();
		for(char chr : data.toCharArray()) {
			
			if(chr == LEFT) {
				cursor= cursor==0?0:--cursor;
			} else if(chr == RIGHT) {
				cursor= cursor>=password.size()?cursor:cursor+1;
			} else if(chr == BACK) {
				if(cursor > 0) {
					password.remove(cursor-1);
					cursor--;
				}
			} else {
				password.add(cursor++, chr);
			}
		}
		
		StringBuilder sb= new StringBuilder();
		for(char chr : password) {
			sb.append(chr);
		}
		
		return sb.toString();
	}
}
