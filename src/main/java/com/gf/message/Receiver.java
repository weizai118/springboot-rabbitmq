package com.gf.message;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * <p>Title: Receiver</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author guofu
 * @version 1.0
 * @date 2018-04-09 17:43
 */
@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch( 1 );

    public void receiveMessage(String message){
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
