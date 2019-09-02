package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://www.acmicpc.net/problem/1722
 */
public class Q01722 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q01722 m= new Q01722();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 결국 풀긴 했는데 알고리즘이 굉장히 지저분하다... 아래 블로그가 굉장히 깔끔한 풀이인듯
	 * https://wjdgus2951.tistory.com/66
	 */
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		int n= Integer.valueOf(br.readLine());
		String[] row= br.readLine().split(" ");
		
		
		int[] nums= null;
		switch(row[0]) {
			case "1":
				nums= solution(n, Long.valueOf(row[1]));
				for(int num : nums) {
					bw.write(String.valueOf(num) + " ");
				}
				break;
			case "2":
				nums= new int[n];
				for(int i=0; i<n; i++) {
					nums[i]= Integer.valueOf(row[i+1]);
				}
				long rank= solution(nums);
				bw.write(String.valueOf(rank));
				break;
		}
	}
	
//	public static void main(String[] args) {
//		Q01722 m= new Q01722();
//		int cnt= 5;
//		
//		long[] facts= m.factorials(cnt);
//		List<int[]> numList= new ArrayList<>();
//		
//		for(int i=1; i<=facts[cnt]; i++) {
//			int[] nums= m.solution(cnt, i);
//			numList.add(nums);
//			
//			System.out.print("rank ["+i+"], ");
//			for(int n : nums) {
//				System.out.print(n + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//		System.out.println();
//		for(int[] nums : numList) {
//			long rank= m.solution(nums);
//			for(int n : nums) {
//				System.out.print(n + " ");
//			}
//			System.out.println(", rank ["+rank+"]");
//		}
//	}
	
	/**
	 * case 2.
	 * 
	 * 1. boolean[1..n]을 생성하고 idx=1로 초기화 한다.
	 * 2. rank 값의 범위를 구한다. min < rank <= max
	 *    - min : [idx+1..n]의 factorial * (현재 숫자-1 등급)
	 *    - max : [idx+1..n]의 factorial * (현재 숫자 등급)
	 * 3. idx를 증가하면서 min과 max를 갱신해나간다.
	 * 4. min과 max의 값 차이가 1이 나면 max가 rank가 된다.
	 */
	private long solution(int[] nums) {
		
		int n= nums.length;
		boolean[] used= new boolean[n];
		long[] facts= factorials(n);
		long min= 0L;
		long max= Long.MAX_VALUE;
		int idx= 0;
		
		while(max-min > 1L) {
			
			long f= facts[n-idx-1];
			int grade= getGrade(used, nums[idx]);
			
			long pMin= min;
			min= min + f * (grade-1);
			max= pMin + f * grade;
			
			used[nums[idx]-1]= true;
			idx++;
		}
		
		return max;
	}
	
	private int getGrade(boolean[] used, int num) {
		int grade= 0;
		for(int i=0; i<num; i++) {
			if(used[i]) {
				continue;
			}
			grade++;
		}
		
		return grade;
	}
	
	/**
	 * case 1.
	 * 
	 * 1. boolean[1..n]을 생성하고 idx=1로 초기화 한다.
	 * 1. (n-1)! 값을 구하여 nf 라고 한다.
	 * 2. nf의 배수가 rank보다 클 때까지 idx++을 한다.
	 * 3. idx를 수열의 숫자로 작성하고 boolean[idx]에 해당 숫자가 사용 되었음을 marking 한다.
	 * 4. nf의 배수 - rank를 한다.
	 * 5. nf의 배수 - rank가 0인 경우는 남은 수열의 자리는 최대 숫자가 되므로 boolean을 n부터 방문하며 사용 안 한 숫자를 하나하나 채워준다.
	 * 6. nf의 배수 - rank가 0이 아닌 경우는 위 1~4를 반복한다.
	 */
	private int[] solution(int n, long rank) {
		
		boolean[] used= new boolean[n];
		long[] facts= factorials(n);
		int[] nums= new int[n];
		int idx= 0;
		
		while(rank >= 0) {
			
			long fact= facts[n-idx-1];
			int cnt= 0;
			
			if(rank == 0L) {
				for(int i=idx; i<nums.length; i++) {
					for(int j=used.length-1; j>=0; j--) {
						if(!used[j]) {
							nums[i]= j+1;
							used[j]= true;
							break;
						}
					}
				}
				break;
			} else if(rank == 1L) {
				for(int i=idx; i<nums.length; i++) {
					for(int j=0; j<used.length; j++) {
						if(!used[j]) {
							nums[i]= j+1;
							used[j]= true;
							break;
						}
					}
				}
				break;
			}
			
			long min= 0L;
			long max= fact;
			
			while(max < rank) {
				max= max + fact;
				cnt++;
			}
			
			min= max - fact;
			
			int num= getNum(used, cnt);
			used[num-1]= true;
			nums[idx++]= num;
			rank= rank - min;
		}
		
		return nums;
	}
	
	private int getNum(boolean[] used, int skip) {
		int idx= 0;
		while(true) {
			
			if(used[idx % used.length]) {
				idx++;
				continue;
			} else if(skip-- == 0) {
				return idx+1;
			}
			idx++;
		}
	}
	
	private long[] factorials(int n) {
		long[] factorials= new long[n+1];
		factorials[0]= 1L;
		
		for(int i=1; i<=n; i++) {
			factorials[i]= factorials[i-1] * i;
		}
		
		return factorials;
	}
	
//	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
//		
//		n= Integer.valueOf(br.readLine());
//		
//		String[] row= br.readLine().split(" ");
//		
//		int type= 0;
//		long rank= 0;
//		int[] nums= new int[n];
//		
//		switch(row[0]) {
//			case "1":
//				type= 1;
//				for(int i=0; i<n; i++) {
//					nums[i]= i+1;
//				}
//				rank= Long.valueOf(row[1]);
//				break;
//			case "2":
//				type= 2;
//				for(int i=0; i<n; i++) {
//					nums[i]= Integer.valueOf(row[i+1]);
//				}
//				break;
//		}
//		
//		long[] r= solution(new boolean[n], 0, new long[n], type, rank, nums);
//		
//		if(type == 1) {
//			for(long num : r) {
//				bw.write(String.valueOf(num) + " ");
//			}
//		} else {
//			bw.write(String.valueOf(r[0]));
//		}
//	}
//	
//	private int n= 0;
//	private long rank= 0;
//	
//	private long[] solution(boolean[] used, int idx, long[] currentNum, int type, long rank, int[] nums) {
//		
//		if(idx == this.n) {
//			this.rank++;
//			
//			if(type == 1 && this.rank == rank) {
//				return currentNum;
//			} else if(type == 2) {
//				boolean eq= true;
//				
//				for(int i=0; i<n; i++) {
//					if(currentNum[i] != nums[i]) {
//						eq= false;
//						break;
//					}
//				}
//				
//				if(eq) {
//					return new long[] {this.rank};
//				}
//			}
//			return null;
//		}
//		
//		for(int i=0; i<nums.length; i++) {
//			if(used[i]) {
//				continue;
//			}
//			
//			used[i]= true;
//			currentNum[idx]= i+1;
//			long[] r= solution(used, idx+1, currentNum, type, rank, nums);
//			if(r != null) {
//				return r;
//			}
//			used[i]= false;
//		}
//		return null;
//	}
}
