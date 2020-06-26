package com.node1.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.node1.action.Action;
import com.node1.action.NodeProcAction;
import com.node1.action.SocketTestAction;




@WebServlet("/node1")
public class node1Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public node1Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String cmd = request.getParameter("cmd");
		Action action =router(cmd);
		action.execute(request, response);
	}
	private Action router(String cmd) {
		if (cmd.equals("nodeProc")) {
			return new NodeProcAction();
		}else if (cmd.equals("socketTest")) {
			return new SocketTestAction();
		}
		return null;
	}
}
