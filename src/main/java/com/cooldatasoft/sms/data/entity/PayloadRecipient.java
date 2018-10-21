package com.cooldatasoft.sms.data.entity;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

//batchsingle.recepients.recipient.recipient = id
//batchsingle.recepients.recipient.type=MAILINGLIST
//
//batchsingle.recepients.recipient.recipient = id
//batchsingle.recepients.recipient.type=CONTACT
//
//batchsingle.recepients.recipient.recipient = 447581779450
//batchsingle.recepients.recipient.type=MSISDN

//<recipient><id>4D04A0330A4999ED7BBC0C2D74DA5CBC</id><recipient>447581779450</recipient><type>MSISDN</type></recipient>
@Data
@XmlRootElement(name = "recipient")
public class PayloadRecipient implements MobivateEntity {

	private String id;
	private String recipient;
	private String type;
}