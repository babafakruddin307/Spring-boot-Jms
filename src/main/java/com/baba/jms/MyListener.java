package com.baba.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MyListener {
	@JmsListener(destination = "${springjms.myqueue}")
	public void Recieve(String message) {
		System.out.println("message recieved---->"+message);
	}
}
