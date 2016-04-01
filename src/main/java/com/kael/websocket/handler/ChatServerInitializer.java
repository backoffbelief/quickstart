package com.kael.websocket.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class ChatServerInitializer extends ChannelInitializer<SocketChannel> {

	private DefaultChannelGroup channelGroup;

	public ChatServerInitializer(DefaultChannelGroup channelGroup) {
		super();
		this.channelGroup = channelGroup;
	}

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new HttpServerCodec(),
				new ChunkedWriteHandler(), 
				new HttpObjectAggregator(64*1024),
				new HttpRequestHandler("/ws"),
				new WebSocketServerProtocolHandler("/ws"),
				new WebSocketFrameHandler(channelGroup));
	}
}
