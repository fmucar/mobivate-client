package com.cooldatasoft.sms.data;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

import com.cooldatasoft.sms.data.entity.PayloadBatchMessageData;
import com.cooldatasoft.sms.data.entity.PayloadBatchSingle;
import com.cooldatasoft.sms.data.entity.PayloadContact;
import com.cooldatasoft.sms.data.entity.PayloadDeliveryMessage;
import com.cooldatasoft.sms.data.entity.PayloadMailingList;
import com.cooldatasoft.sms.data.entity.PayloadOptoutData;

//<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
//<xaresponse>
//	<authentication>
//		<code>0</code>
//		<text>OK</text>
//	</authentication>
//	<session>BC468D0C0A4999ED5711C36C7DCB1453</session>
//	<contact>
//		<created>2014-01-25T23:43:29+0000</created>
//		<creatingUserId>58411423-911f-4c1d-bdd7-e9ec990062a4</creatingUserId>
//		<id>CBC963600A4999ED5711C36C9A62037E</id>
//		<ownerUserId>58411423-911f-4c1d-bdd7-e9ec990062a4</ownerUserId>
//		<version>0</version>
//		<birthday>1970-01-01T00:00:00+0000</birthday>
//		<custom1/>
//		<custom2/>
//		<custom3/>
//		<custom4/>
//		<custom5/>
//		<email/>
//		<firstName/>
//		<lastName/>
//		<mobile>00447581779450</mobile>
//	</contact>
//	<mailinglist>
//		<created>2014-01-25T23:44:33+0000</created>
//		<creatingUserId>58411423-911f-4c1d-bdd7-e9ec990062a4</creatingUserId>
//		<id>CBCA5CE00A4999ED5711C36C834E75B5</id>
//		<ownerUserId>58411423-911f-4c1d-bdd7-e9ec990062a4</ownerUserId>
//		<version>0</version>
//		<name>List Name</name>
//		<recipientCount>0</recipientCount>
//	</mailinglist>
//	<entitylist offset="0" total="1">
//		<userroutepricing>
//		<cost>
//			<amount>0</amount>
//			<currency>AUD</currency>
//		</cost>
//		<countryCode>44</countryCode>
//		<countryId>1eb261d7-999f-45b5-a720-53efce62a7f3</countryId>
//		<countryName>United Kingdom</countryName>
//		<description>: Platinum Route</description>
//		<id>0D15BBCE0ABEA7BE01ECE62604EF029E</id>
//		<price>
//			<amount>0.02000</amount>
//			<currency>GBP</currency>
//		</price>
//		<userId>58411423-911f-4c1d-bdd7-e9ec990062a4</userId>
//		<userRouteId>3d7c4a51-c68b-4ee4-b83b-822bf7eb293f</userRouteId>
//	</userroutepricing>
//	</entitylist>
//</xaresponse>

@Data
@XmlRootElement(name = "xaresponse")
public class PayloadData {

	private PayloadAuthentication authentication;
	private String session;
	private PayloadContact contact;
	private PayloadMailingList mailinglist;
	private PayloadEntityList entitylist;
	private PayloadBatchMessageData logicalmessage;
	private PayloadOptoutData optout;
	private PayloadDeliveryMessage deliverymessage;
	private PayloadBatchSingle batchsingle;
}
