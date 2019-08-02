package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://www.acmicpc.net/problem/1629
 */
public class Q01629 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q01629 m= new Q01629();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 결국 공유 소스 보고 풀었는데.. > https://www.acmicpc.net/source/share/2c57da2f313e40c2a2885cafc6a1963f
	 * 저렇게 mod를 미리 해도 상관이 없는건가..??
	 * 
	 * 원리는 아래와 같은 수학을 이용해 곱셈 횟수를 줄이는 것.
	 * a^b%c를 구할 때,
	 * b=2k라면, (a^k)^2를 통해 횟수를 줄일 수 있고
	 * b=2k+1이라면, (a^k)^2*a를 통해 횟수를 줄일 수 있다.
	 * 
	 * 위의 힌트를 봐도 mod를 저렇게 중간 중간 해도 값이 변경 안된다는걸 몰라서.. Overflow를 어떻게 해결해야 할지 몰랐었음..
	 */
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		String[] data= br.readLine().split(" ");
		int a= Integer.valueOf(data[0]);
		int b= Integer.valueOf(data[1]);
		int c= Integer.valueOf(data[2]);
		
		long r= solution(a, b, c);
		bw.write(String.valueOf(r % c));
	}
	
	private long solution(long a, long b, long c) {
		
		if(b == 0) {
			return 1;
		}
		
		long r= solution(a, b/2, c);
		r= r * r % c;
		
		if(b % 2 == 1) {
			r= r * a % c;
		}
		
		return r;
	}
}
