package cn.tf.blog.controller.ws;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import cn.tf.blog.service.CommentService;


@Controller("newsController")

public class NewsController extends TextWebSocketHandler{
	
	@Autowired
	private CommentService commentService;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
	   //	String msgStr="肯德基一元抢";
		String content="博客云欢迎您";
		
		String msgStr="{\"content\":\""+content+"\"}";
	
		session.sendMessage(new TextMessage(msgStr));  //给每一个进入页面的
	}
	



}
