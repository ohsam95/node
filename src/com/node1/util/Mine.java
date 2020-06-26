package com.node1.util;

import java.util.ArrayList;

import com.google.gson.GsonBuilder;
import com.node1.model.Block;

public class Mine {


		
	//채굴 과정
	
	//블록들을 담을 ArrayList 생성 
 	public static	ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 3; //난이도 설정

	public static void chain(String message) {
	//초기 블록 생성
	blockchain.add(new Block(message,"0"));
	System.out.println("1번 블록을 채굴합니다");
	blockchain.get(0).mineBlock(difficulty);
	blockchain.get(0).mineBlock(difficulty);
	//이후 블록 생성
	for (int i = 1; i <= 1; i++) {
		blockchain.add(new Block(message+i, blockchain.get(blockchain.size()-1).hash));
		System.out.println(i+1+"번 블록을 채굴합니다");
		blockchain.get(i).mineBlock(difficulty);
	}
	//전체 블록이 정신인지 체크
	System.out.println("블록 체크 : "+isChainValid());
	//전체 블록을 출력
	String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
	System.out.println("블록체인 리스트 : ");
	System.out.println(blockchainJson);
	
	
}

public static Boolean isChainValid() {
		
		Block currentBlock; 
		Block previousBlock;

		//전체 블럭을 체크합니다.
		for(int i=1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			
			//현재 블럭의 hash가 맞는지 체크합니다.
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("현재 해쉬가 다릅니다.");			
				return false;
			}
			
			//이전 블럭의 hash값과 동일한지 체크합니다.
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("이전 해쉬가 다릅니다.");
				return false;
			}
		}
		return true;
	}	
}
