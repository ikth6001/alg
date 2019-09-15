package com.ikth.algs.baekjoon.ing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://www.acmicpc.net/problem/15684
 */
public class Main {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Main m= new Main();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		String[] row= br.readLine().split(" ");
		int n= Integer.valueOf(row[0]); /** 세로 수 */
		int m= Integer.valueOf(row[1]); /** 놓인 가로 수 */
		int h= Integer.valueOf(row[2]); /** 세로선마다 놓을 수 있는 가로선의 갯수 */
		
		int[][] sadaries= new int[h][n];
		for(int i=0; i<m; i++) {
			row= br.readLine().split(" ");
			int height= Integer.valueOf(row[0])-1;
			int no= Integer.valueOf(row[1])-1;
			
			sadaries[height][no]= 1;
			sadaries[height][no+1]= -1; /** 값 범위 제한에 의해 out of index가 안남 */
		}
		
		int cnt= solution(sadaries, 0, 0);
		bw.write(String.valueOf(cnt));
	}
	
	private int solution(int[][] sadaries, int start, int cnt) {
		
		if(checkDestination(sadaries)) {
			return cnt;
		}
		
		int min= -1;
		int w= sadaries[0].length;
		int startJ= start%w;
		
		for(int i=start/w; i<sadaries.length; i++) {
			for(int j=startJ; j<w-1; j++) {
				/** 사다리에 가로 선을 그을 수 있다면 */
				if(sadaries[i][j] == 0 && sadaries[i][j+1] == 0) {
					sadaries[i][j]= 1;
					sadaries[i][j+1]= -1;
					int tmp= solution(sadaries, i*w+j+1, cnt+1);
					if(tmp >= 0) {
						min= min == -1 ? tmp : (min > tmp ? tmp : min);
					}
					sadaries[i][j]= 0;
					sadaries[i][j+1]= 0;
				}
			}
			startJ= 0;
		}
		
		return min;
	}
	
	private boolean checkDestination(int[][] sadaries) {
		
		int n= sadaries[0].length;
		int cnt= 0;
		
		for(int i=0; i<n; i++) {
			int dest= i;
			
			for(int j=0; j<sadaries.length; j++) {
				dest= dest + sadaries[j][dest];
			}
			
			cnt= (i == dest) ? cnt+1 : cnt;
		}
		
		return cnt == n;
	}
}
