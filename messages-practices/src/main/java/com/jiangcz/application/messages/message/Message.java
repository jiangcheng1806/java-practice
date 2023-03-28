package com.jiangcz.application.messages.message;

/**
 * 类名称：Message<br>
 * 类描述：消息<br>
 * 创建时间：2019年04月19日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class Message {
    //消息类型
    public static final int KEY_MSG = 1;
    public static final int MOUSE_MSG = 2;
    public static final int SYS_MSG = 3;

    private Object source; // 来源
    private int type; //类型
    private String info; //信息

    public Message(Object source,int type,String info){
        super();
        this.source = source;
        this.type = type;
        this.info = info;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public static int getKeyMsg() {
        return KEY_MSG;
    }

    public static int getMouseMsg() {
        return MOUSE_MSG;
    }

    public static int getSysMsg() {
        return SYS_MSG;
    }
}
