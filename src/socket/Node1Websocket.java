package socket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.node1.model.Block;

// WebSocket의 호스트 주소 설정
@ServerEndpoint("/websocket")
public class Node1Websocket {
//	List<String> messageArray = new ArrayList<String>();
	// ArrayList<Block> blockchain = new ArrayList<Block>();
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
		// 채굴 과정
//		int difficulty = 2; // 난이도 설정

	
		//메시지 파싱
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonObject = (JsonObject) jsonParser.parse(message);
		String hash = jsonObject.get("prvHash").toString();
		String data = jsonObject.get("dataJson").toString();
		int difficulty = Integer.parseInt(jsonObject.get("difficulty").toString()); // 난이도 설정
		
		//블록 생성 및 채굴 시작
		Block block = new Block(data, hash);
		System.out.println(i + "번 블록을 채굴합니다");
		block.mineBlock(difficulty);
		i = i + 1;

		// 전체 블록을 출력
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(block);
		System.out.println("블록체인 리스트 : ");
		System.out.println(blockchainJson);

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
