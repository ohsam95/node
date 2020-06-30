package com.node1.model;

import java.util.Date;

import com.node1.util.SHA256;
import com.node1.util.StringUtil;


public class Block {

	public String hash;
	public String previousHash;
	private String data;
	private long timestamp;
	private int nonce;
	private int count;
	
	public Block(String data, String proviousHash) {
		this.data=data;  //거래데이터를 제이슨화 시켜서 넣기
		this.previousHash = proviousHash;  //이전해쉬
		this.timestamp = new Date().getTime();  //시간
		this.hash = calculateHash();   //이전해쉬와 시간과 넌스, 데이터를 합친 해쉬
		this.count = 0;
	}
	public String calculateHash() {
		String calculatehash = StringUtil.applySha256(
				previousHash+
				Long.toString(timestamp)+
				Integer.toString(nonce)+
				data
				);
		return calculatehash;
	}
	public void mineBlock(int difficulty) {
		//간단한 테스트와 이해를 돕기위해 target을 difficulty 숫자 만큼 앞에 0으로 채웁니다.
		String target = new String(new char[difficulty]).replace('\0', '0');
		
		while(!hash.substring(0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
			
			System.out.println("1번 노드의 Hash #"+ nonce+" : "+ hash);
			
		}
		System.out.println("1번 노드 채굴 성공!!! : " + hash);
	}
	
	}
	
	

