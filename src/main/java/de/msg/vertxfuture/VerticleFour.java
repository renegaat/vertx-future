package de.msg.vertxfuture;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;

public class VerticleFour extends AbstractVerticle {
    
    @Override
    public void start(Future<Void> startFuture) throws Exception {

        NetServerOptions options = new NetServerOptions().setPort(4711);
        NetServer server = vertx.createNetServer(options);
        
        server.connectHandler(socket -> {
            socket.handler(buffer -> {
                System.out.println("I received some bytes: " + buffer.length());
            });
        });
        
        startFuture.complete();
    }
}
