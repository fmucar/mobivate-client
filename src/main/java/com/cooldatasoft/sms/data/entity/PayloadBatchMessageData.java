package com.cooldatasoft.sms.data.entity;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

//<xaresponse>
//	<authentication>
//	<code>0</code>
//	<text>OK</text>
//	</authentication>
//
//	<logicalmessage>
//		<created>2014-02-19T19:42:57+0000</created>
//		<id>4BAC29700A4999ED7BBC0C2D7A00C9A6</id>
//		<allowDuplicates>true</allowDuplicates>
//		<body/>
//		<deliveryScheduleDestinationTime/>
//		<duplicateCount>0</duplicateCount>
//		<messageSpread>0</messageSpread>
//		<optoutCount>0</optoutCount>
//		<optoutProcessing>true</optoutProcessing>
//		<originator/>
//		<processOnDelivery>false</processOnDelivery>
//		<recipientCount>0</recipientCount>
//		<routeId/>
//		<sentCount>0</sentCount>
//		<status>SCHEDULED</status>
//		<successfulCount>0</successfulCount>
//		<validCount>0</validCount>
//		<value>0</value>
//	</logicalmessage>
//</xaresponse>

@Data
@XmlRootElement(name = "logicalmessage")
public class PayloadBatchMessageData implements MobivateEntity {
	private String created;
	private String id;
	private Boolean allowDuplicates;
	private String body;
	private String deliveryScheduleDestinationTime;
	private Integer duplicateCount;
	private Integer messageSpread;
	private Integer optoutCount;
	private Boolean optoutProcessing;
	private String originator;
	private Boolean processOnDelivery;
	private Integer recipientCount;
	private String routeId;
	private Integer sentCount;
	private String status;
	private Integer successfulCount;
	private Integer validCount;
	private Integer value;
}