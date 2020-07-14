package io.pgs.svc.pref.event;


import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UnitCollectorAgent {

    private final String host;
    private final int port;
    private UnitCollectorAgent(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static UnitCollectorAgent of(String host, int port) {
        UnitCollectorAgent display = new UnitCollectorAgent(host, port);
        return display;
    }

    public void send(String message) throws Exception {
        NettyStartupUtil.runClient(this.host, this.port, pipeline ->
                pipeline.addLast(new LoggingHandler(LogLevel.DEBUG))
                        .addLast(new StringDecoder(CharsetUtil.UTF_8))
                        .addLast(new StringEncoder(CharsetUtil.UTF_8))
                        .addLast(new UnitCollectorHandler(message))
        );
    }

    @ChannelHandler.Sharable
    private static class UnitCollectorHandler extends ChannelInboundHandlerAdapter {

        private final String message;
        public UnitCollectorHandler(String message) {
            this.message = message;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            ctx.write(this.message);
            ctx.flush();
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            String response = (String)msg;
            log.debug("response: "+ response); // ![0020F!]

        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) {
            ctx.close();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }

    }

}
