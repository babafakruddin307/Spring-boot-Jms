package com.baba.jms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringjmsApplicationTests {
	@Autowired
   private	MessageSender sender;
	@Test
	public void testsendAndreceive() {
		sender.send("hello spring jms world");
	}

}
