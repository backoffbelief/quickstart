package com.kael.websocket.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.ImmediateEventExecutor;

import com.kael.websocket.handler.ChatServerInitializer;

public class ChatServer implements Runnable{

	private final int port;
	
	public ChatServer(int port) {
		this.port = port;
	}

	@Override
	public void run(){
		ServerBootstrap bootstrap = new ServerBootstrap();
		final EventLoopGroup loopGroup = new NioEventLoopGroup();
		
		final ChannelFuture future = bootstrap.group(loopGroup)
		.channel(NioServerSocketChannel.class)
		.childHandler(new ChatServerInitializer(new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE)))
		.bind(port);
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			
			@Override
			public void run() {
				Channel channel = future.channel();
				if(channel != null){
					channel.close();
					loopGroup.shutdownGracefully();
				}
			}
		}));
		System.out.println("start lister port:"+port);
	}
}
