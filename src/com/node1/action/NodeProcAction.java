package com.node1.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.node1.model.Block;



public class NodeProcAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String data = request.getParameter("messageTextArea");
		//지금 메시지제이슨을 가져오지 못하고 있음 이것만 가져오면 된다
		Block block = new Block(data, "0");
		System.out.println(data);
		System.out.println(block.hash);
		
		RequestDispatcher dis = request.getRequestDispatcher("socketTest.jsp");
		dis.forward(request, response);
		
	}
}
