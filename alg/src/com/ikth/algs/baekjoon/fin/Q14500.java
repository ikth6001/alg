package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Q14500 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q14500 m= new Q14500();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private int[] rotateCnt= new int[] {1, 0, 3, 1, 3, 3, 3};
	private int[][][] template= new int[][][] {
		{
			{1},
			{1},
			{1},
			{1}
		}, {
			{1, 1},
			{1, 1}
		}, {
			{1, 0},
			{1, 0},
			{1, 1}
		}, {
			{1, 0},
			{1, 1},
			{0, 1}
		}, {
			{0, 1, 0},
			{1, 1, 1}
		}, {
			{0, 1},
			{0, 1},
			{1, 1}
		}, {
			{0, 1},
			{1, 1},
			{1, 0}
		}
	};
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		int h, w;
		int[][] map;
		
		String[] row= br.readLine().split(" ");
		h= Integer.valueOf(row[0]);
		w= Integer.valueOf(row[1]);
		
		map= new int[h][w];
		for(int i=0; i<h; i++) {
			String[] mapRow= br.readLine().split(" ");
			for(int j=0; j<w; j++) {
				map[i][j]= Integer.valueOf(mapRow[j]);
			}
		}
		
		int[][][] tetrominos= initTetrominos();
		
		int max= 0;
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				for(int k=0; k<tetrominos.length; k++) {
					int[][] tetromino= tetrominos[k];
					int ei= i + tetromino.length;
					int ej= j + tetromino[0].length;
					
					if(ei > h || ej > w) {
						continue;
					}
					
					int score= 0;
					for(int si=i; si<ei; si++) {
						for(int sj=j; sj<ej; sj++) {
							score= score + map[si][sj] * tetromino[si-i][sj-j];
						}
					}
					
					max= max < score ? score : max;
				}
			}
		}
		
		bw.write(String.valueOf(max));
	}
	
	private int[][][] initTetrominos() {
		
		int[][][] tetrominos= new int[template.length + 14][][];
		int idx= 0;
		for(int i=0; i<template.length; i++) {
			int cnt= rotateCnt[i];
			
			tetrominos[idx++]= template[i];
			for(int j=0; j<cnt; j++) {
				tetrominos[idx]= rotate(tetrominos[idx-1]);
				idx++;
			}
		}
		
		return tetrominos;
	}
	
	private int[][] rotate(int[][] tetromino) {
		int[][] rotate= new int[tetromino[0].length][];
		
		for(int i=0; i<tetromino[0].length; i++) {
			rotate[rotate.length - i - 1]= new int[tetromino.length];
			for(int j=0; j<tetromino.length; j++) {
				rotate[rotate.length - i - 1][j]= tetromino[j][i]; 
			}
		}
		
		return rotate;
	}
}
