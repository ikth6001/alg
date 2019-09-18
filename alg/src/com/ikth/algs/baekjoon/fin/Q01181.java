package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

/**
 * https://www.acmicpc.net/problem/1181
 */
public class Q01181 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q01181 m= new Q01181();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		int n= Integer.parseInt(br.readLine());
		
		Set<String> set= new HashSet<String>();
		for(int i=0; i<n; i++) {
			set.add(br.readLine());
		}
		
		String[] words= set.toArray(new String[set.size()]);
		divConqSort(words);
		for(String word : words) {
			bw.write(word);
			bw.newLine();
		}
	}
	
	private void divConqSort(String[] words) {
		if(words.length == 1) {
			return;
		} else if(words.length == 2) {
			if(gt(words[0], words[1])) {
				String tmp= words[0];
				words[0]= words[1];
				words[1]= tmp;
			}
			return;
		}
		
		int leng= words.length;
		int mid= leng/2;
		
		String[] left= new String[mid];
		String[] right= new String[leng-mid];
		System.arraycopy(words, 0, left, 0, mid);
		System.arraycopy(words, mid, right, 0, leng-mid);
		
		divConqSort(left);
		divConqSort(right);

		int lIdx= 0;
		int rIdx= 0;
		
		for(int i=0; i<leng; i++) {
			
			String word;
			if(gt(left[lIdx], right[rIdx])) {
				word= right[rIdx++];
			} else {
				word= left[lIdx++];
			}
			words[i]= word;
			
			if(lIdx == left.length) {
				for(int j=i+1; j<leng; j++) {
					words[j]= right[rIdx++];
				}
				break;
			} else if(rIdx == right.length) {
				for(int j=i+1; j<leng; j++) {
					words[j]= left[lIdx++];
				}
				break;
			}
		}
	}
	
	/**
	 * 왼쪽이 더 크면 true 아니면 false
	 */
	private boolean gt(String left, String right) {
		
		if(left.length() == right.length()) {
			return left.compareTo(right) > 0 ? true : false;
		} else {
			return left.length() > right.length() ? true : false;
		}
	}
}
