package com.jiangc.practice.designpattern.handlerchain;

/**
 * 类名称：HandlerChain<br>
 * 类描述：
 *
 * 责任链模式感觉就是将处理类变成一个链表一样的任务链 经过条件判断就会将 消息转给下一个节点进行处理
 *
 *
 *
 *
 * <br>
 * 创建时间：2018年08月28日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public abstract class HandlerChain {

    private HandlerChain nextHandler;


    //每个处理者都必须对请求做出处理
    public final Response handleMessage(Request request){
        Response response = null;
        //判断是否是自己的处理级别
        if (this.getHandlerLevel().equals(request.getHandlerLevel())){
            response = this.echo(request);
        } else { //不属于自己的处理级别
            //判断是否有下一个处理者
            if (this.nextHandler != null){
                response = this.nextHandler.handleMessage(request);
            } else {
                //没有适当的处理者，业务自行处理
            }
        }
        return response;
    }

    //设置下一个处理者是谁
    public void setNextHandler(HandlerChain handler){
        this.nextHandler = handler;
    }

    //每个处理者都必须实现处理任务
    protected abstract Response echo(Request request);

    //每个处理者都有一个处理级别
    protected abstract Level getHandlerLevel();

    abstract class Level{}

    abstract class Response{}

    abstract class Request{
        //每个处理者都有一个处理级别
        protected abstract Level getHandlerLevel();
    }
}
