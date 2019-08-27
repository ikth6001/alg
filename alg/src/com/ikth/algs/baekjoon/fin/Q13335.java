package com.ikth.algs.baekjoon.fin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://www.acmicpc.net/problem/13335
 */
public class Q13335 {

	public static void main(String[] args) {
		try(BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			Q13335 m= new Q13335();
			m.solution(br, bw);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void solution(BufferedReader br, BufferedWriter bw) throws NumberFormatException, IOException {
		
		int truckCnt;
		int bridgeLeng;
		int bridgeEnableWeight;
		
		int[] truckWeights;
		{
			String[] row= br.readLine().split(" ");
			truckCnt= Integer.valueOf(row[0]);
			bridgeLeng= Integer.valueOf(row[1]);
			bridgeEnableWeight= Integer.valueOf(row[2]);
			
			truckWeights= new int[truckCnt];
		}
		
		{
			String[] row= br.readLine().split(" ");
			for(int i=0; i<truckCnt; i++) {
				truckWeights[i]= Integer.valueOf(row[i]);
			}
		}
		
		int[] truckLocs= new int[truckCnt];
		int truckNextIdx= 0;
		int headTruckIdxInBridge= 0;
		int currentWeight= 0;
		int tick= 0;
		
		while(truckLocs[truckCnt-1] <= bridgeLeng) {
			tick++;
			
			for(int i=headTruckIdxInBridge; i<truckNextIdx; i++) {
				truckLocs[i]+= 1;
			}
			
			if(truckLocs[headTruckIdxInBridge] > bridgeLeng) {
				currentWeight-= truckWeights[headTruckIdxInBridge];
				headTruckIdxInBridge++;
			}
			
			if(truckNextIdx < truckCnt
					&& bridgeEnableWeight >= currentWeight + truckWeights[truckNextIdx]) {
				truckLocs[truckNextIdx]= 1;
				currentWeight+= truckWeights[truckNextIdx];
				truckNextIdx++;
			}
		}
		
		bw.write(String.valueOf(tick));
	}
}
