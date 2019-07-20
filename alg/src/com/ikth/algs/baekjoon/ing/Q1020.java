package com.ikth.algs.baekjoon.ing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/1020
 */
public class Q1020 {
	
	public static void main(String[] args) throws IOException {
		try (Scanner scanner = new Scanner(System.in)) {
			String time = scanner.next();
			Q1020 m = new Q1020();
			System.out.println(m.solution(time));
		}
	}
	
	private final Integer[] LENGTHS= new Integer[] { 6, 2, 5, 5, 4, 5, 6, 3, 7, 5 };
	private long[] pow10= null;
	private final long MAX= Long.MAX_VALUE;
	
	/**
	 * 못 풀어서 블로그 검색 https://blog.encrypted.gg/263
	 * -> 이유는 모르겠는데 실패 함.. 블로그는 C++ 언어로 되어있는데 그 로직은 성공 함..
	 */
	private long solution(final String strTime) {
		final int n= strTime.length();
		final long longTime= Long.valueOf(strTime);
		
		pow10= new long[n+1];
		pow10[0]= 1;
		for(int i=1; i<pow10.length; i++) {
			pow10[i]= pow10[i-1] * 10;
		}
		
		/**
		 * dp[i][j] : i자리의 합이 j인 최소 시간
		 */
		long[][] dp= new long[n+1][];
		dp[1]= new long[] { MAX, MAX, 1, 7, 4, 2, 0, 8, MAX, MAX };
		
		for(int i=2; i<=n; i++) {
			int min= i*2;
			int prevMin= min-2;
			int max= i*7;
			int prevMax= max-7;
			dp[i]= new long[max+1];
			
			for(int j=0; j<dp[i].length; j++) {
				dp[i][j]= MAX;
			}
			
			for(int j=min; j<dp[i].length; j++) {
				for(int k=0; k<10; k++) {
					int leng= LENGTHS[k];
					int df= j-leng;
					if(df < prevMin || df > prevMax) {
						continue;
					}
					long prev= dp[i-1][df];
					long next= prev * 10 + k;
					
					if(dp[i][j] > next) {
						dp[i][j]= next;
					}
				}
			}
		}
		
		long result= pow10[n];
		int lastDigit= (int) (longTime % 10);
		for(int i=0; i<10; i++) {
			int leng= LENGTHS[i];
			if(leng == LENGTHS[lastDigit]) {
				if(i > lastDigit) {
					int tmp= i-lastDigit;
					result= result > tmp ? tmp : result;
				} else if(i < lastDigit) {
					long tmp= i - lastDigit + pow10[n];
					result= result > tmp ? tmp : result;
				}
			}
		}
		
		for(int i=2; i<=n; i++) {
			int cnt= lastNDigitCnt(strTime, i);
			
			for(int j=0; j<10; j++) {
				if(cnt - LENGTHS[j] < 2 * (i-1) || cnt - LENGTHS[j] > 7 * (i-1)) {
					continue;
				}
				
				long nextTime= Long.valueOf(substringWithDefault(strTime, 0, n-i, "0")) * pow10[i] + j * pow10[i-1] + dp[i-1][cnt-LENGTHS[j]];
				long df= nextTime - longTime;
				
				if(df <= 0L) {
					df= df + pow10[n];
				}
				
				result= result > df ? df : result;
			}
		}
		
		return result;
	}
	
	private String substringWithDefault(String data, int start, int end, String def) {
		if(start >= end) {
			return def;
		}
		
		return data.substring(start, end);
	}
	
	private int lastNDigitCnt(String strTime, int digit) {
		int leng= strTime.length();
		int result= 0;
		for(int i=leng-digit; i<leng; i++) {
			result += LENGTHS[strTime.charAt(i) - '0'];
		}
		return result;
	}
	
	/**
	 * @param time 시간을 나타냄. 길이 N <= 15 이고, 시간이 N자리보다 작으면 0으로 채워진다. e.g. 007 (N이 3인 경우)
	 * @return
	 */
	@SuppressWarnings("unused")
	private long mine(String time) {

		/**
		 * - 시간의 길이를 N이라고 할 때, 각 시간의 자릿수 K는 2 * N <= K <= 7 * N 이고, N <= 15 이다.
		 * - 이전 시간 패턴에 각 숫자를 하나씩 추가하여 각 패턴의 새로운 자릿수를 구할 수 있다.
		 * 
		 * - dp는 배열이다. dp[i]는 길이가 i인 패턴들의 List를 의미한다.
		 * - 시간의 길이를 1부터 최대 길이까지 Loop을 돌면서 이전 데이터를 통해 새로운 데이터를 빠르게 구한다.
		 * 
		 * -> 현재 이렇게 하면 각 길이의 모든 패턴을 구하지만... OutOfMemoryError가 난다...
		 * -> 모든 패턴을 구하는게 아니라... 각 패턴의 조합을 구하는 방법을 찾아야 할 듯...
		 * -> 패턴을 구해서 하는 방향은 아닐 듯..
		 */
		
		final int timeLeng= time.length();
		final int maxLeng= timeLeng * 7;
		int[][][] dp= new int[timeLeng][maxLeng + 1][]; // [timeLeng][patternLeng][patterns]
		
		for(int i=0; i<timeLeng; i++) {
			
			Map<Integer, List<Integer>> tmp= new HashMap<Integer, List<Integer>>();
			
			if(i == 0) {
				for(int j=0; j<LENGTHS.length; j++) {
					int leng= LENGTHS[j];
					if(!tmp.containsKey(leng)) {
						tmp.put(leng, new ArrayList<Integer>());
					}
					tmp.get(leng).add(j);
				}
			} else {
				for(int j=0; j<dp[i-1].length; j++) {
					if(dp[i-1][j] == null) {
						continue;
					}
					
					for(int k=0; k<dp[i-1][j].length; k++) {
						for(int l=0; l<LENGTHS.length; l++) {
							int leng= j + LENGTHS[l];
							if(!tmp.containsKey(leng)) {
								tmp.put(leng, new ArrayList<Integer>());
							}
							tmp.get(leng).add(dp[i-1][j][k] * 10 + l);
						}
					}
				}
			}
			
			for(int leng : tmp.keySet()) {
				dp[i][leng]= tmp.get(leng).stream().mapToInt(pattern -> pattern).toArray();
			}
		}
		
		/**
		 * 테스트용 출력
		 */
		for(int i=0; i<dp[timeLeng-1].length; i++) {
			int[] patterns= dp[timeLeng-1][i];
			
			if(patterns == null) {
				continue;
			}
			
			System.out.format("length [%02d] : ", i);
			for(int pattern : patterns) {
				System.out.format("%03d ", pattern);
			}
			System.out.println();
		}
		
		return 0L;
	}
}
