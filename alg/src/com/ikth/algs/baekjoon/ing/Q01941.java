package com.ikth.algs.baekjoon.ing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
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
	 * 
	 * DFS를 사용하는 것으로 바꾸고, 우선 순위는 S이거나 근처에 S가 있는 Y이어야 한다(조건은 이정도면 충분. 왜냐면 Y의 개수는 최대 3개까지 가능하므로 근처에 S가 없는 Y만 있다면 그건 어차피 조건 만족 못함)
	 * 스택에 삽입하면 total + 1이 되고, stack은 우선순위에 맞춰 총 수가 7개 미만, Y개수가 3개 이하일 때까지 진행한다.
	 * DFS로 쭉 가다가 더 이상 추가가 안되면 분기점까지 빼면서 total - 1을 해준다.
	 * 
	 * TODO : 위 알고리즘으로 구현 해보자.
	 */
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		char[][] map= new char[5][5];
		
		for(int i=0; i<5; i++) {
			map[i]= br.readLine().toCharArray();
		}
		
		Queue<int[]> queue= new LinkedList<>();
		int cnt= 0;
		
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {

				boolean[][] visit= new boolean[5][5];
				queue.add(new int[] {i, j});
				visit[i][j]= true;
				int yCnt= map[i][j] == 'Y' ? 1 : 0;
				int total= 1;
				
				while(queue.size() > 0) {
					
					int[] loc= queue.poll();
					int h= loc[0];
					int w= loc[1];
					
					if(h > 0 && !visit[h-1][w]) {
						
						if(map[h-1][w] == 'S' || yCnt < 3) {
							total++;
							queue.add(new int[] {h-1, w});
							visit[h-1][w]= true;
							yCnt= map[h-1][w] == 'Y' ? yCnt+1 : yCnt;
						}
					}
					
					if(h < 4 && !visit[h+1][w]) {
						
						if(map[h+1][w] == 'S' || yCnt < 3) {
							total++;
							queue.add(new int[] {h+1, w});
							visit[h+1][w]= true;
							yCnt= map[h+1][w] == 'Y' ? yCnt+1 : yCnt;
						}
					}
					
					if(w > 0 && !visit[h][w-1]) {
						
						if(map[h][w-1] == 'S' || yCnt < 3) {
							total++;
							queue.add(new int[] {h, w-1});
							visit[h][w-1]= true;
							yCnt= map[h][w-1] == 'Y' ? yCnt+1 : yCnt;
						}
					}
					
					if(w < 4 && !visit[h][w+1]) {
						
						if(map[h][w+1] == 'S' || yCnt < 3) {
							total++;
							queue.add(new int[] {h, w+1});
							visit[h][w+1]= true;
							yCnt= map[h][w+1] == 'Y' ? yCnt+1 : yCnt;
						}
					}
					
					System.out.println("total ["+total+"]");
					if(total >= 7) {
						cnt++;
						break;
					}
				}
			}
		}
		
		bw.write(String.valueOf(cnt));
	}
}