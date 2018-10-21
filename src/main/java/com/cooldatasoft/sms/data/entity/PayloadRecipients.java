package com.cooldatasoft.sms.data.entity;

import java.util.ArrayList;
import java.util.List;

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

@Data
@XmlRootElement(name = "recipients")
public class PayloadRecipients implements MobivateEntity {

	private List<PayloadRecipient> recipient = new ArrayList<>();
}
