package com.ikth.algshackrank.ing;

/**
 * https://www.hackerrank.com/challenges/magic-square-forming/problem
 */
public class MagicSquare {
	
	public static void main(String[] args) {
		int[][] data= new int[][] {
			{4, 8, 2}
			, {4, 5, 7}
			, {6, 1, 6}
		};
		System.out.println(formingMagicSquare(data));
	}

	static int formingMagicSquare(int[][] s) {
		
		int[][] magicSquare= null;
		int min= Integer.MAX_VALUE;
		
		for(int i=0; i<4; i++) {
			
			if(magicSquare == null) {
				magicSquare= createMagicSquare(3);
			} else {
				rotate(magicSquare);
			}
			
			print(magicSquare);
			
			int score= 0;
			for(int j=0; j<3; j++) {
				for(int k=0; k<3; k++) {
					score= score + Math.abs(magicSquare[j][k] - s[j][k]);
				}
			}
			min= score < min ? score : min;
			
			reverse(magicSquare);
			print(magicSquare);
			score= 0;
			for(int j=0; j<3; j++) {
				for(int k=0; k<3; k++) {
					score= score + Math.abs(magicSquare[j][k] - s[j][k]);
				}
			}
			min= score < min ? score : min;
			
			/**
			 * 돌려놓은거 다시 원복..
			 */
			reverse(magicSquare);
		}
		
		return min;
    }
	
	static void print(int[][] arr) {
		
		for(int i=0; i<arr.length; i++) {
			
			for(int j=0; j<arr[i].length; j++) {
				
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 * arr은 정사각형이라고 가정
	 */
	static void rotate(int[][] arr) {
		
		int tmp;
		int n= arr.length;
		
		for(int i=0; i<n-1; i++) {
			tmp= arr[0][i];
			arr[0][i]= arr[i][n-1];
			arr[i][n-1]= arr[n-1][n-1-i];
			arr[n-1][n-1-i]= arr[n-1-i][0];
			arr[n-1-i][0]= tmp;
		}
	}
	
	static void reverse(int[][] arr) {
		
		int tmp;
		int n= arr.length;
		int m= n / 2;
		
		for(int i=0; i<n; i++) {
			
			for(int j=0; j<m; j++) {
				
				tmp= arr[i][j];
				arr[i][j]= arr[i][n-1-j];
				arr[i][n-1-j]= tmp;
			}
		}
	}
	
	/**
	 * n은 홀수라고 가정. 검색을 통해 만드는 방법 알아냄.
	 * https://www.geeksforgeeks.org/magic-square/
	 */
	static int[][] createMagicSquare(int n) {
		
		int[][] magicSquare= new int[n][n];
		
		int h= n/2;
		int w= n-1;
		int v= 2;
		int max= n*n;
		magicSquare[h][w]= 1;
		
		while(v <= max) {
			int[] next= getNext(n, h, w);
			h= next[0];
			w= next[1];
			
			while(magicSquare[h][w] != 0) {
				next= getNextIfExist(n, h, w);
				
				h= next[0];
				w= next[1];
			}
			
			magicSquare[h][w]= v++;
		}
		
		return magicSquare;
	}
	
	static int[] getNext(int n, int h, int w) {
		
		int nextH= h == 0 ? n-1 : h-1;
		int nextW= w == n-1 ? 0 : w+1;
		
		return new int[] { nextH, nextW };
	}
	
	static int[] getNextIfExist(int n, int h, int w) {
		
		int nextH= h == n-1 ? 0 : h+1;
		int nextW= w > 1 ? w - 2 : n + (w - 2);
		
		return new int[] { nextH, nextW };
	}
}
