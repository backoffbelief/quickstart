package com.kael.websocket.handler;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedNioFile;

import java.io.RandomAccessFile;

public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

	private final String uri;
	
	public HttpRequestHandler(String uri) {
		super();
		this.uri = uri;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg)
			throws Exception {
		if(uri.equalsIgnoreCase(msg.getUri())){
			ctx.fireChannelRead(msg.retain());
			return ;
		}
		if(HttpHeaders.is100ContinueExpected(msg)){
			ctx.writeAndFlush(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE));
		}
		HttpResponse response = new DefaultHttpResponse(msg.getProtocolVersion(), HttpResponseStatus.OK);
		response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/html;charset=UTF-8");
		RandomAccessFile file  = new RandomAccessFile(this.getClass().getResource("/").getPath()+"/index.html", "r");
		if(HttpHeaders.isKeepAlive(msg)){
			response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, file.length());
			response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
		}
		ctx.writeAndFlush(response);
		if(ctx.pipeline().get(SslHandler.class) == null){
			ctx.writeAndFlush(new DefaultFileRegion(file.getChannel(), 0, file.length()));
		}else{
			ctx.writeAndFlush(new ChunkedNioFile(file.getChannel()));
		}
		ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
		if(!HttpHeaders.isKeepAlive(msg)){
			future.addListener(ChannelFutureListener.CLOSE);
		}
		file.close();
		
	}

}
