package com.titanic.camel.routes;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.ExchangeBuilder;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.BlockingQueue;

/**
 * Created by wb-yangbin.d on 2015/12/18.
 */
@Lazy(false)
@Component
public class ProducerMassage
{

    @Resource(name="blockingQueue")
    BlockingQueue blockingQueue;


    CamelContext producerContext;

    public void setBlockingQueue(BlockingQueue blockingQueue)
    {
        this.blockingQueue = blockingQueue;
    }

    public void setProducerContext(CamelContext producerContext)
    {
        this.producerContext = producerContext;
    }


    @PostConstruct
    public void send()
    {
        for(int x = 0 ; x < 100; x++)
        {
            ExchangeBuilder eb =ExchangeBuilder.anExchange(producerContext);
            Exchange exchange = eb.withBody("massage->"+x).build();
            blockingQueue.offer(exchange);
        }

    }



}
