package com.ikth.algs.baekjoon.ing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Q16684 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q16684 m= new Q16684();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private int total;
	private int[] context;
	private int time;
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		String[] data= br.readLine().split(" ");
		int m= Integer.valueOf(data[0]);
		this.total= Integer.valueOf(data[1]);
		this.time= Integer.valueOf(data[2]);
		
		this.context= new int[this.total];
		for(int i=0; i<this.total; i++) {
			context[i]= 1;
		}
		
		switch(m) {
			case 1:
				doHanoi01(this.total, 0, this.total-1, 1, 2, 3);
				break;
			case 2:
				doHanoi02(this.total, 0, this.total-1, 1, 3);
				break;
			case 3:
				doHanoi03(this.total, 0, this.total-1, 1, 3);
		}
		
		for(int i=0; i<total; i++) {
			bw.write(String.valueOf(context[i]) + " ");
		}
	}
	
	private boolean isNext(int from, int to) {
		if(to == from + 1
				|| to == from - 1) {
			return true;
		} else if(from == this.total
					&& to == 1) {
			return true;
		}
		return false;
	}
	
	private void doHanoi03(int size, int upIdx, int botIdx, int from, int to) {
		if(time == 0 || size == 0 || from == to) {
			return;
		} else if(size == 1) {
			if(from == 2) {
				context[upIdx]= to;
				time--;
			} else {
				context[upIdx]= 2;
				time--;
				doHanoi02(size, upIdx, upIdx, 2, to);
			}
		} else {
			if(from == 2) {
				doHanoi02(size-1, upIdx, botIdx-1, 2, to == 1 ? 3 : 1);
				doHanoi02(1, botIdx, botIdx, 2, to);
				doHanoi02(size-1, upIdx, botIdx-1, to == 1 ? 3 : 1, to);
			} else if(to == 2) {
				doHanoi02(size-1, upIdx, botIdx-1, from, from == 1 ? 3 : 1);
				doHanoi02(1, botIdx, botIdx, from, 2);
				doHanoi02(size-1, upIdx, botIdx-1, from == 1 ? 3 : 1, 2);
			} else {
				doHanoi02(size, upIdx, botIdx, from, 2);
				doHanoi02(size, upIdx, botIdx, 2, to);
			}
		}
	}
	
	private void doHanoi02(int size, int upIdx, int botIdx, int from, int to) {
		if(time == 0 || size == 0 || from == to) {
			return;
		} else if(size == 1) {
			if(from == 2) {
				context[upIdx]= to;
				time--;
			} else {
				context[upIdx]= 2;
				time--;
				doHanoi02(size, upIdx, upIdx, 2, to);
			}
		} else {
			if(from == 2) {
				doHanoi02(size-1, upIdx, botIdx-1, 2, to == 1 ? 3 : 1);
				doHanoi02(1, botIdx, botIdx, 2, to);
				doHanoi02(size-1, upIdx, botIdx-1, to == 1 ? 3 : 1, to);
			} else if(to == 2) {
				doHanoi02(size-1, upIdx, botIdx-1, from, from == 1 ? 3 : 1);
				doHanoi02(1, botIdx, botIdx, from, 2);
				doHanoi02(size-1, upIdx, botIdx-1, from == 1 ? 3 : 1, 2);
			} else {
				doHanoi02(size, upIdx, botIdx, from, 2);
				doHanoi02(size, upIdx, botIdx, 2, to);
			}
		}
	}
	
	private void doHanoi01(int size, int upIdx, int botIdx, int from, int mid, int to) {
		if(time == 0 || size == 0) {
			return;
		} else if(size == 1) {
			context[upIdx]= to;
			time--;
		} else {
			doHanoi01(size-1, upIdx, botIdx-1, from, to, mid);
			doHanoi01(1, botIdx, botIdx, from, mid, to);
			doHanoi01(size-1, upIdx, botIdx-1, mid, from, to);
		}
	}
}
