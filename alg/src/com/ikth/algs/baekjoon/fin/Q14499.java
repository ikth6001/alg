package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Q14499 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q14499 m= new Q14499();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		int h;
		int w;
		int startH;
		int startW;
		int cmdCnt;
		
		int[][] map;
		int[] cmds;
		{
			String[] row= br.readLine().split(" ");
			h= Integer.valueOf(row[0]);
			w= Integer.valueOf(row[1]);
			startH= Integer.valueOf(row[2]);
			startW= Integer.valueOf(row[3]);
			cmdCnt= Integer.valueOf(row[4]);
			
			map= new int[h][w];
			cmds= new int[cmdCnt];
		}
		
		for(int i=0; i<h; i++) {
			String[] row= br.readLine().split(" ");
			for(int j=0; j<w; j++) {
				map[i][j]= Integer.valueOf(row[j]);
			}
		}
		
		{
			String[] row= br.readLine().split(" ");
			for(int i=0; i<cmdCnt; i++) {
				cmds[i]= Integer.valueOf(row[i]);
			}
		}
		
		/**
		 * X0X
		 * 000
		 * X0X
		 * X0X 형태이고 가운데인 1, 1이 주사위의 밑이라고 가정
		 */
		int[][] dice= new int[4][3];
		int currH= startH;
		int currW= startW;
		
		for(int i=0; i<cmds.length; i++) {
			int cmd= cmds[i];
			
			boolean ignore= false;
			switch(cmd) {
				case 1: // 동쪽
					if(currW == w-1) {
						ignore= true;
					} else {
						currW++;
					}
					break;
				case 2: // 서쪽
					if(currW == 0) {
						ignore= true;
					} else {
						currW--;
					}
					break;
				case 3: // 북쪽
					if(currH == 0) {
						ignore= true;
					} else {
						currH--;
					}
					break;
				case 4: // 남쪽
					if(currH == h-1) {
						ignore= true;
					} else {
						currH++;
					}
					break;
			}
			
			if(ignore) {
				continue;
			}
			
			moveDice(dice, cmd);
			if(map[currH][currW] == 0) {
				map[currH][currW]= dice[1][1];
			} else {
				dice[1][1]= map[currH][currW];
				map[currH][currW]= 0;
			}
			
			bw.write(String.valueOf(dice[3][1]));
			bw.newLine();
		}
	}
	
	private void moveDice(int[][] dice, int cmd) {
		
		int tmp;
		switch(cmd) {
			case 1: // 동쪽
				tmp= dice[1][2];
				dice[1][2]= dice[3][1];
				dice[3][1]= dice[1][0];
				dice[1][0]= dice[1][1];
				dice[1][1]= tmp;
				break;
			case 2: // 서쪽
				tmp= dice[1][0];
				dice[1][0]= dice[3][1];
				dice[3][1]= dice[1][2];
				dice[1][2]= dice[1][1];
				dice[1][1]= tmp;
				break;
			case 3: // 북쪽
				tmp= dice[3][1];
				dice[3][1]= dice[2][1];
				dice[2][1]= dice[1][1];
				dice[1][1]= dice[0][1];
				dice[0][1]= tmp;
				break;
			case 4: // 남쪽
				tmp= dice[0][1];
				dice[0][1]= dice[1][1];
				dice[1][1]= dice[2][1];
				dice[2][1]= dice[3][1];
				dice[3][1]= tmp;
				break;
		}
	}
}
