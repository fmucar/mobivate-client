package com.cooldatasoft.sms.data.entity;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

import com.cooldatasoft.sms.data.PayloadCost;

//<xaresponse>
//	<authentication>
//		<code>0</code>
//		<text>OK</text>
//	</authentication>
//
//	<deliverymessage>
//		<created>2014-02-19T22:51:00+0000</created>
//		<id>4C5852950A4999ED7BBC0C2DAB89EC95</id>
//		<cost>
//			<amount>0</amount>
//			<currency>AUD</currency>
//		</cost>
//		<deliveryMethod>SMS</deliveryMethod>
//		<direction>MT</direction>
//		<parts>1</parts>
//		<simulated>false</simulated>
//	</deliverymessage>
//</xaresponse>

@Data
@XmlRootElement(name = "deliverymessage")
public class PayloadDeliveryMessage implements MobivateEntity {

	private String id;
	private String created;
	private String deliveryMethod;
	private String direction;
	private Integer parts;
	private Boolean simulated;
	private PayloadCost cost;

	private String creatingUserId;
	private String modified;
	private String ownerUserId;
	private Integer version;
	private String body;
	private String connectionId;
	private String gatewayReference;
	private String logicalMessageId;
	private String logicalRecipientId;
	private String originator;
	private String recipient;
	private String routeId;
	private String status;
}
