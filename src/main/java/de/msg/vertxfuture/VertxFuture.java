package de.msg.vertxfuture;

import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**
 * insert lombok
 *
 * create main method shortcut -> psvm 
 *
 * deploy verticle one 
 *
 * deploy verticle two 
 * 
 * deploy verticle three
 *
 * verticle one http server creates futures for verticle event two and three 
 *
 * verticle two and three reply with standard adress 
 * 
 * what happens to the future when one verticle is not responding
 * 
 * show netserver in verticle four
  */


public class VertxFuture {
    public static void main(String[] args) {

        final Logger logger = LoggerFactory.getLogger(VertxFuture.class);

        Vertx vertx = Vertx.vertx();
        
        vertx.eventBus().registerDefaultCodec(CustomObject.class, new CustomObjectCodec());
        
        vertx.deployVerticle(new VerticleOne(), res -> {
            if (res.succeeded()) {
                logger.info("verticle one deployed successfully");
            } else {
                logger.info("verticle one deployed with error : " + res.cause().getLocalizedMessage());
            }
        });
        
        vertx.deployVerticle(new VerticleTwo(), res -> {
            if (res.succeeded()) {
                logger.info("verticle two deployed successfully");
            } else {
                logger.info("verticle two deployed with error : " + res.cause().getLocalizedMessage());
            }
        });
        
        vertx.deployVerticle(new VerticleThree(), res -> {
            if (res.succeeded()) {
                logger.info("verticle three deployed successfully");
            } else {
                logger.info("verticle three deployed with error : " + res.cause().getLocalizedMessage());
            }
        });

        vertx.deployVerticle(new VerticleFour(), res -> {
            if (res.succeeded()) {
                logger.info("verticle four deployed successfully");
            } else {
                logger.info("verticle four deployed with error : " + res.cause().getLocalizedMessage());
            }
        });
        
    }
}
