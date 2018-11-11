package de.msg.vertxfuture;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class CustomObjectCodec implements MessageCodec<CustomObject,CustomObject> {
    
    @Override
    public void encodeToWire(Buffer buffer, CustomObject customObject) {
    }

    @Override
    public CustomObject decodeFromWire(int i, Buffer buffer) {
        return null;
    }

    @Override
    public CustomObject transform(CustomObject customObject) {
        return customObject;
    }

    @Override
    public String name() {
        return "CustomObjectCodec";
    }

    @Override
    public byte systemCodecID() {
        return -1;
    }
}
