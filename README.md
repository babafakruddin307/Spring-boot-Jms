# Spring-boot-Jms
Demo program on Spring Boot Java Message Service

Spring JMS is used for communication between different applications developed in different languages.
Spring JMS template which is used to send and receive messages and topics 

JMS support two types of messaging services:
1.point to point
2.publish/subscribe 

JMS will send messages both in Synchronously and asynchronously through virtual channel called queues 

To use Spring JMS First we need to install "Apache Mq" 
Go to bin directory of apache mq folder->go to cmd of bin path ->start apachemq by using "activemq start" command 
->Go to browser and enter "localhost8161/admin
->use username and password has "admin"


Now we are ready to use spring JMS and send and receive messages

**sender class**
```
package com.baba.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
@Component
public class MessageSender {
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Value(value = "${springjms.myqueue}")
	private String queue;

	public void send(String message) {
		jmsTemplate.convertAndSend(queue, message);
	}
}

```
**Listener class**

```
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

```

**Configuration class**
```
package com.baba.jms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class SpringjmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringjmsApplication.class, args);
	}

}

```
**Test the JMS funtionality**
```
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

```
**properties file**

```
springjms.myqueue=myqueue

spring.activemq.broker-url=tcp://localhost:61616
spring.activemq.user=admin
spring.activemq.password=admin
```
