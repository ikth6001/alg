package com.ikth.algs.baekjoon.fin;

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
	
	private final int T= 1;
	private final int F= 0;
	
	/**
	 * 또 다른 블로그 참고 : http://blog.naver.com/PostView.nhn?blogId=pasdfq&logNo=221525007968
	 * 이건 드디어 성공!!!
	 */
	private long solution(final String strTime) {
		
		/**
		 * - dp[i][j][k] : i자리의 시간이고, j개의 선분이 남았을 때, 현재 숫자보다 큰거나/작거나(k) 일 때의 초 정보
		 * 
		 * - dp를 구하는 로직
		 * 1. 입력 값의 선분 총 갯수 구함
		 * 2. 최상단부터 시작하여 i초(0-9를 적절히 처리하여 Loop) 일 때 남은 선분 갯수 및 큼/작음 여부를 재귀 호출 하여 초 정보 저장
		 *    - 여기서 적절이 처리한다는 의미는 입력 초보다 현재 초가 이미 크다면 0부터 시작, 아니라면 입력 초부터 시작 함을 의미
		 * 3. 재귀호출의 결과 값을 dp에 저장하고 결과 값이 10이 아니면 바로 리턴(아니면 끝까지 Loop을 돈다)
		 * 
		 * - dp를 사용하는 로직
		 * 1. dp의 i를 0부터 길이 n까지 차례로 방문하면서 dp값 + (이전 dp들의 값 * 10)을 재귀적으로 호출하며 구한다.
		 * 
		 * - 주의 사항
		 * 1. 10^n-1을 넘어서는 경우 다시 0초부터 시작 하므로, solve의 리턴 값이 10이면 0초부터 시작한 것을 의미한다.
		 * 2. 같은 길이의 시간이 0인 문자열을 만들어 dp 데이터를 채운다.
		 * 3. 실제 입력 값으로 dp 데이터를 채운다.
		 * 4. 만약 실제 입력 값으로 dp 데이터를 채운 리턴이 10이면 10^n-1을 넘는 것이므로 3번에서 채운 dp를 통해 값을 구하고 10^n을 더해준다.
		 */
		final int n= strTime.length();
		final int cnt= getCnt(strTime);
		
		int[][][] dp= new int[n][cnt+1][2];
		init(dp, -1);
		
		if(fill(dp, strTime, 0, cnt, false) != 10) {
			calcVal= 0;
			calc(dp, strTime, 0, cnt, false);
			
			return calcVal - Long.valueOf(strTime);
		} else {
			final String zeroTime= String.format("%0" + n + "d", 0);
			
			init(dp, -1);
			fill(dp, zeroTime, 0, cnt, false);
			
			calcVal= 0;
			calc(dp, zeroTime, 0, cnt, false);
			
			return calcVal + (long) Math.pow(10, n) - Long.valueOf(strTime);
		}
	}
	
	private long calcVal;
	
	private void calc(int[][][]dp, String strTime, int pos, int left, boolean isBig) {
		if(pos == strTime.length()) {
			return;
		}

		int val= dp[pos][left][isBig ? T : F];
		if(val == -1) {
			return;
		}
		
		calcVal= calcVal * 10 + val;
		calc(dp, strTime, pos+1, left - LENGTHS[val], isBig || strTime.charAt(pos)-'0' < val);
	}
	
	private int fill(int[][][]dp, String strTime, int pos, int left, boolean isBig) {
		
		if(left < 0) {
			return 10;
		}
		
		if(pos == dp.length) {
			return (isBig && left == 0) ? 0 : 10; // 크지 않은 상태에서 left가 0이라면 더 작던가 동일하단 의미
		}
		
		int val= dp[pos][left][isBig ? T : F];
		
		if(val != -1) { // 이미 이전 단계에서 값을 구한 경우
			return val;
		}
		
		val= 10;
		int digit= strTime.charAt(pos)-'0';
		for(int i= isBig ? 0 : digit; i<10; i++) {
			val= fill(dp, strTime, pos+1, left-LENGTHS[i], isBig || digit < i);
			dp[pos][left][isBig ? T : F]= val;
			
			if(val != 10) {
				dp[pos][left][isBig ? T : F]= i;
				return i;
			}
		}
		
		return val;
	}
	
	private void init(int[][][] dp, int val) {
		// dp[i][j][k]는 null이 아님이 보장.
		for(int i=0; i<dp.length; i++) {
			for(int j=0; j<dp[i].length; j++) {
				for(int k=0; k<dp[i][j].length; k++) {
					dp[i][j][k]= val;
				}
			}
		}
	}
	
	private int getCnt(final String strTime) {
		int cnt= 0;
		
		// 어차피 숫자임이 보장.
		for(char digit : strTime.toCharArray()) {
			cnt+= LENGTHS[digit-'0'];
		}
		
		return cnt;
	}
	
	private final Integer[] LENGTHS= new Integer[] { 6, 2, 5, 5, 4, 5, 6, 3, 7, 5 };
	private long[] pow10= null;
	private final long MAX= Long.MAX_VALUE;
	
	/**
	 * 못 풀어서 블로그 검색 https://blog.encrypted.gg/263
	 * -> 이유는 모르겠는데 실패 함.. 블로그는 C++ 언어로 되어있는데 그 로직은 성공 함..
	 */
	@SuppressWarnings("unused")
	private long blog01(final String strTime) {
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
