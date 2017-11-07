package com.vag;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * Time Server implemented using Apache Mina Library
 *
 */
public class MinaTimeServer
{
    private static final int PORT = 9124;
    public static void main( String[] args )
    {
        try {
            System.out.println("Time Server Listening on : " + PORT);

            IoAcceptor acceptor = new NioSocketAcceptor();
            acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));

            acceptor.setHandler(new TimeServerHandler());
            acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
            acceptor.getSessionConfig().setReadBufferSize(1024);
            acceptor.bind(new InetSocketAddress(PORT));
        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
