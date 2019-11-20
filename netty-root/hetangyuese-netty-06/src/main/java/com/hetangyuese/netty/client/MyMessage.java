package com.hetangyuese.netty.client;

/**
 * @program: netty-root
 * @description: 消息体
 * @author: hewen
 * @create: 2019-11-15 16:09
 **/
public class MyMessage {


    private int length;

    private String content;


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    private static boolean decodeState = true;

    private static Boolean STATE_HANDLER_REMOVED_PENDING = true;

    public static void main(String[] args) {
        boolean removePending = decodeState == STATE_HANDLER_REMOVED_PENDING;

        System.out.println(removePending);
        System.out.println(decodeState);
    }
}
