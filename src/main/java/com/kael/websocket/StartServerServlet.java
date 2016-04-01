package com.kael.websocket;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.kael.websocket.server.ChatServer;

public class StartServerServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		new Thread(new ChatServer()).start();
	}

}
