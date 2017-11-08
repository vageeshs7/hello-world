package com.vag;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * Hello world!
 *
 */
public class MinaTimeClient extends IoHandlerAdapter
{
    private static String HOST = "localhost";
    private static int PORT = 9124;
    public static void main( String[] args )
    {
        System.out.println( "Apache Mina Client" );

        NioSocketConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(2000);
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
        connector.setHandler(new TimeClientSessionHandler());

        ConnectFuture future = connector.connect(new InetSocketAddress(HOST, PORT ));
        future.awaitUninterruptibly();
        IoSession session = future.getSession();

        //Wait for Time request to complete
        session.getCloseFuture().awaitUninterruptibly();
        connector.dispose();
    }
}
