package com.vag;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class TimeClientSessionHandler extends IoHandlerAdapter{

    public TimeClientSessionHandler()
    {

    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
        cause.printStackTrace();
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("Connected");
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String msgStr = message.toString();
        System.out.println("Server: " + msgStr);
        if(msgStr.indexOf("Hello") >= 0) {
            session.write("Time Please");
        }else{
            session.write("stop");
            Thread.sleep(500);
            session.closeNow();
        }
    }
}
