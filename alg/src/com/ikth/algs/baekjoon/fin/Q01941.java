package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/1941
 */
public class Q01941 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q01941 m= new Q01941();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 틀린 이유 : BFS를 사용함 -> 말도 안되는 알고리즘
	 * 또 틀린 이유 : DFS를 사용함 -> 마찬가지로 말도 안되는 알고리즘
	 * 아직 방법은 생각 못했지만.. 무턱대고 구현하기 전에 알고리즘을 신중히 잘 선택해야 겠다.. 시간 낭비만 엄청 하고 있네..
	 * 
	 * 내 최종 방법(실패) : 백트래킹을 하는데 동적으로[?] 다음 대상 목록을 구하는 알고리즘을 해봤는데.. 반복되는 구간 처리를 어떻게 할지 모르겠다.
	 * 블로그 참고 : https://mygumi.tistory.com/218
	 * 
	 * 기존 알고리즘 기법에만 너무 목맨듯 하다. 좀 더 넓게 생각 해보면 쉬운 방법이 있네... -> 성공~ ㅜㅜ
	 */
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		char[][] map= new char[5][5];
		
		for(int i=0; i<5; i++) {
			map[i]= br.readLine().toCharArray();
		}
		
		boolean[] used= new boolean[25];
		List<List<int[]>> groups= new ArrayList<>();
		
		solution(groups, map, used, 0, 0, 0);
		
//		for(List<int[]> group : groups) {
//			for(int[] m : group) {
//				System.out.print("h["+m[0]+"], w["+m[1]+"],   ");
//			}
//			System.out.println();
//		}
//		System.out.println("size ["+groups.size()+"]");
		
		int cnt= 0;
		for(List<int[]> group : groups) {
			if(connected(group)) {
				cnt++;
			}
		}
		
		bw.write(String.valueOf(cnt));
	}
	
	private boolean connected(List<int[]> group) {
		
		Queue<int[]> queue= new LinkedList<int[]>();
		queue.add(group.get(0));
		
		boolean[][] mark= new boolean[5][5];
		int markCnt= group.size();
		
		for(int[] memb : group) {
			int h= memb[0];
			int w= memb[1];
			mark[h][w]= true;
		}
		
		while(queue.size() > 0 && markCnt > 0) {
			
			int[] memb= queue.poll();
			int h= memb[0];
			int w= memb[1];

			if(h>0&&mark[h-1][w]) {
				queue.add(new int[] {h-1, w});
				mark[h-1][w]= false;
				markCnt--;
			}
			if(h<4&&mark[h+1][w]) {
				queue.add(new int[] {h+1, w});
				mark[h+1][w]= false;
				markCnt--;
			}
			if(w>0&&mark[h][w-1]) {
				queue.add(new int[] {h, w-1});
				mark[h][w-1]= false;
				markCnt--;
			}
			if(w<4&&mark[h][w+1]) {
				queue.add(new int[] {h, w+1});
				mark[h][w+1]= false;
				markCnt--;
			}
		}
		
		return markCnt == 0;
	}
	
	private void solution(List<List<int[]>> groups, char[][] map, boolean[] used, int start, int total, int yCnt) {
		
		if(total == 7) {
			List<int[]> group= new ArrayList<>();
			for(int i=0; i<25; i++) {
				if(used[i]) {
					int h= i/5;
					int w= i%5;
					
					group.add(new int[] {h, w});
				}
			}
			groups.add(group);
			return;
		}
		
		for(int i=start; i<25; i++) {
			if(used[i]) {
				continue;
			}
			int h= i/5;
			int w= i%5;
			
			if(yCnt < 3 || map[h][w] == 'S') {
				used[i]= true;
				solution(groups, map, used, i+1, total+1, map[h][w] == 'Y' ? yCnt + 1 : yCnt);
				used[i]= false;
			}
		}
	}
	
	
	@SuppressWarnings("unused")
	private void myFailSolution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		char[][] map= new char[5][5];
		
		for(int i=0; i<5; i++) {
			map[i]= br.readLine().toCharArray();
		}
		
		int cnt= 0;
		
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				int thisStageCnt= myFailSolution(map, i, j, 0, 0, new boolean[5][5], new ArrayList<>());
				System.out.println("h["+i+"], w["+j+"], cnt["+thisStageCnt+"]");
				cnt+= thisStageCnt;
			}
		}
		
		bw.write(String.valueOf(cnt));
	}
	
	private int myFailSolution(char[][] map, int h, int w, int yCnt, int total, boolean[][] visit, List<int[]> available) {
		
		if( yCnt > 2 && map[h][w] == 'Y' ) {
			return 0;
		}
		
		if(total == 6) {
			System.out.println("\t - h["+h+"], w["+w+"]");
			return 1;
		}
		
		total++;
		yCnt= map[h][w] == 'Y' ? yCnt + 1 : yCnt;
		visit[h][w]= true;
		
		List<int[]> newAvailable= new ArrayList<>();
		for(int[] loc : available) {
			if(loc[0] == h && loc[1] == w) {
				continue;
			}
			newAvailable.add(loc);
		}
		if(w < 4 && !visit[h][w+1]) {
			newAvailable.add(new int[] {h, w+1});
		}
		if(h < 4 && !visit[h+1][w]) {
			newAvailable.add(new int[] {h+1, w});
		}
		
		int cnt= 0;
		for(int[] next : newAvailable) {
			cnt+= myFailSolution(map, next[0], next[1], yCnt, total, visit, newAvailable);
		}
		visit[h][w]= false;
		return cnt;
	}
}