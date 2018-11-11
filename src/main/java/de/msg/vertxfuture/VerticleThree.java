package de.msg.vertxfuture;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class VerticleThree extends AbstractVerticle {
    
    final Logger logger = LoggerFactory.getLogger(VerticleThree.class);
    
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        vertx.eventBus().consumer(Events.TIMER_EVENT.toString(),message -> sendMessageReply(message));
        startFuture.complete();
    }


    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
        stopFuture.complete();
    }
    
    private void sendMessageReply(Message<Object> message) {
        logger.info("Verticle three send reply");
        message.reply(new JsonObject().put("key","value"));
        //message.fail(1,"error");
    }
}
