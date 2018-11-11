package de.msg.vertxfuture;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class VerticleOne extends AbstractVerticle {

    final Logger logger = LoggerFactory.getLogger(VerticleOne.class);
    
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        vertx.createHttpServer()
                .requestHandler(httpServerRequest -> sendMessages()).listen(8080);
        startFuture.complete();
    }
    
    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
        stopFuture.complete();
    }
    
    private void sendMessages() {
        logger.info("Verticle one will send messages send");

        Future<Void> messageHTTPFuture = Future.future();
        vertx.eventBus().send(Events.HTTP_EVENT.toString(), new JsonObject().put("key", "value"), messageAsyncResult -> {
            if (messageAsyncResult.succeeded()) {
                messageHTTPFuture.complete();
            } else {
                messageHTTPFuture.fail("error");
            }
        });
        
        Future<Void> messageTIMERFuture = Future.future();
        vertx.eventBus().send(Events.TIMER_EVENT.toString(), new JsonObject().put("key", "value"), messageAsyncResult -> {
            if (messageAsyncResult.succeeded()) {
                messageTIMERFuture.complete();
            } else {
                messageTIMERFuture.fail("error");
            }
        });
        
        CompositeFuture.join(messageHTTPFuture, messageTIMERFuture).setHandler(compositeFutureAsyncResult -> {
            logger.info("entered composite");
            
            if (compositeFutureAsyncResult.succeeded()) {
                logger.info("composite complete");
            } else {
                logger.info("composite failed");
            }
        });
    }
}
