package com.kael.websocket;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.kael.websocket.server.ChatServer;

public class StartServerServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		String initParameter = config.getInitParameter("port");
		if(initParameter != null){
			System.out.println(initParameter);
			new Thread(new ChatServer(Integer.parseInt(initParameter))).start();
		}
	}

}
