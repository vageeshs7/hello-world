package com.vag;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeServerHandler extends IoHandlerAdapter {
    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
    {
        cause.printStackTrace();
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String msgStr = message.toString();
        if(msgStr.trim().equalsIgnoreCase("stop"))
        {
            session.closeNow();
            return;
        }

        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.S Z(z)");
        Date date = new Date();
        String response = formater.format(date) + "\n";
        session.write(response);
        System.out.println("Time request served : " + msgStr);

    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        System.out.println("IDLE=" + session.getIdleCount(status));
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("Connection accepted from : " + session.getRemoteAddress().toString());
        session.write("Hello\n".toString());
    }
}
