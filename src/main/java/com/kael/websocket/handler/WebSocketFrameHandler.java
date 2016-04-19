package com.kael.websocket.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.util.AttributeKey;

public class WebSocketFrameHandler extends SimpleChannelInboundHandler<WebSocketFrame>{

//	private final ConcurrentMap<String, Object> result = PlatformDependent.newConcurrentHashMap();
	
	private DefaultChannelGroup group ;

	public WebSocketFrameHandler(DefaultChannelGroup channelGroup) {
		super();
		this.group = channelGroup;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
		if(evt == WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE){
			ctx.attr(AttributeKey.valueOf(ctx.channel().remoteAddress().toString()));
			this.group.add(ctx.channel());
			//group.writeAndFlush(new TextWebSocketFrame(String.format("welcome %s  join!", ctx.channel().remoteAddress().toString())));
			return ;
		}
		super.userEventTriggered(ctx, evt);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg)
			throws Exception {
		if(msg instanceof PingWebSocketFrame  || msg instanceof PongWebSocketFrame){
			return;
		}
		if(msg instanceof CloseWebSocketFrame){
			group.writeAndFlush(String.format("%s  has exited!", ctx.channel().remoteAddress().toString()));
			group.remove(ctx.channel());
			return ;
		}
		if(msg instanceof TextWebSocketFrame){
			String rspText = String.format("%s  say:%s", ctx.channel().remoteAddress().toString(),((TextWebSocketFrame)msg).text());
//			System.out.println(rspText);
			group.writeAndFlush(new TextWebSocketFrame(rspText));
			return ;
		}
		group.writeAndFlush(msg.retain());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
	}
	
	

}
