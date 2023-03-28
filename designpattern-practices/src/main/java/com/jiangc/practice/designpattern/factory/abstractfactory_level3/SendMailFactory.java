package com.jiangc.practice.designpattern.factory.abstractfactory_level3;

public class SendMailFactory implements Provider {

	@Override
    public Sender produce() {
		// TODO Auto-generated method stub
		return new MailSender();
	}

}
