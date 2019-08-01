package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://www.acmicpc.net/problem/5430
 */
public class Q05430 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q05430 m= new Q05430();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		int cnt= Integer.valueOf(br.readLine());
		for(int i=0; i<cnt; i++) {
			
			String func= br.readLine();
			int leng= Integer.valueOf(br.readLine());
			String data= br.readLine();
			data= data.substring(1, data.length()-1);
			String[] split= data.split(",");
			String[] buf= new String[ leng ];
			
			for(int j=0; j<leng; j++) {
				buf[j]= split[j];
			}
			

			boolean error= false;
			boolean reverse= false;
			int start= 0;
			int end= buf.length-1;
			
			if(leng == 0 && func.contains("D")) {
				error= true;
			} else {
				for(int j=0; j<func.length(); j++) {
					if(func.charAt(j) == 'R') {
						reverse= !reverse;
					} else if(func.charAt(j) == 'D') {
						if(end < start) {
							error= true;
							break;
						} else {
							if(reverse) {
								end--;
							} else {
								start++;
							}
						}
					} else {
						error= true;
						break;
					}
				}
			}
			
			
			if(error) {
				bw.write("error\n");
				continue;
			}

			bw.write('[');
			if(reverse) {
				for(int j=end; j>=start; j--) {
					bw.write(buf[j]);
					if(j > start) {
						bw.write(',');
					}
				}
			} else {
				for(int j=start; j<=end; j++) {
					if(j > start) {
						bw.write(',');
					}
					bw.write(buf[j]);
				}
			}
			bw.write("]\n");
		}
	}
}
