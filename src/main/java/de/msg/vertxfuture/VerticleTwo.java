package de.msg.vertxfuture;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class VerticleTwo extends AbstractVerticle {


    final Logger logger = LoggerFactory.getLogger(VerticleTwo.class);

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        vertx.eventBus().consumer(Events.HTTP_EVENT.toString(),message -> sendMessageReply(message));
        startFuture.complete();
    }
    
    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
        stopFuture.complete();
    }
    
    private void sendMessageReply(Message message) {
        logger.info("Verticle two message send");
        message.reply(new JsonObject().put("key","value"));   
    }
}
