package socket;

import java.util.ArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.GsonBuilder;
import com.node1.model.Block;



// WebSocket의 호스트 주소 설정
@ServerEndpoint("/websocket")
public class Node1Websocket {
//	List<String> messageArray = new ArrayList<String>();
	ArrayList<Block> blockchain = new ArrayList<Block>();
	int i = 1;
	
// WebSocket으로 브라우저가 접속하면 요청되는 함수
	@OnOpen
	public void handleOpen() {
// 콘솔에 접속 로그를 출력한다.
		System.out.println("데이터 전송 준비");
	
	}

// WebSocket으로 메시지가 오면 요청되는 함수
	@OnMessage
	public String handleMessage(String message) {
				//채굴 과정 			 	
			    int difficulty = 1; //난이도 설정

				//초기 블록 생성
			    if (blockchain.isEmpty()) {
					blockchain.add(new Block(message,"0"));
					System.out.println("1번 블록을 채굴합니다");
					blockchain.get(0).mineBlock(difficulty);			
					
				}else {
					//이후 블록 생성

					blockchain.add(new Block(message, blockchain.get(blockchain.size()-1).hash));
					System.out.println(i+1+"번 블록을 채굴합니다");
					blockchain.get(i).mineBlock(difficulty);
					i= i+1; 
				}
			  
			  //전체 블록을 출력
			    String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
			    System.out.println("1번 노드 채굴성공");
				System.out.println("블록체인 리스트 : ");
				System.out.println(blockchainJson);
						
				// 이제 블록을 보내주는 알고리즘만들고 다른 노드들 만들고 경쟁하는 알고리즘 만들고!!
						

				// 리턴
		return blockchainJson;
	}

// WebSocket과 브라우저가 접속이 끊기면 요청되는 함수
	@OnClose
	public void handleClose() {
		System.out.println("연결 끊김");
	}

// WebSocket과 브라우저 간에 통신 에러가 발생하면 요청되는 함수.
	@OnError
	public void handleError(Throwable t) {
		t.printStackTrace();
	}
}