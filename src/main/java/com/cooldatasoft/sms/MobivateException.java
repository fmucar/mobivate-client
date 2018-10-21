package com.cooldatasoft.sms;

import com.cooldatasoft.sms.data.PayloadAuthentication;

public class MobivateException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MobivateException(PayloadAuthentication authentication) {
		super(null == authentication ? "" : authentication.toString());
	}

}
