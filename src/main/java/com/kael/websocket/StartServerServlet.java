package com.kael.websocket;

import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.kael.ClassUtil;
import com.kael.websocket.msg.PacketManager;
import com.kael.websocket.server.ChatServer;

public class StartServerServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		Set<Class<?>> classes = ClassUtil.getClasses("com.kael");
		PacketManager.register(classes);
		String initParameter = config.getInitParameter("port");
		if(initParameter != null){
			System.out.println(initParameter);
			new Thread(new ChatServer(Integer.parseInt(initParameter))).start();
		}
	}

}
