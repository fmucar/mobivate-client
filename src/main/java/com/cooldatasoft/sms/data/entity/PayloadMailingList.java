package com.cooldatasoft.sms.data.entity;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

//<mailinglist>
//	<created>2014-01-25T23:44:33+0000</created>
//	<creatingUserId>58411423-911f-4c1d-bdd7-e9ec990062a4</creatingUserId>
//	<id>CBCA5CE00A4999ED5711C36C834E75B5</id>
//	<ownerUserId>58411423-911f-4c1d-bdd7-e9ec990062a4</ownerUserId>
//	<version>0</version>
//	<name>List Name</name>
//	<recipientCount>0</recipientCount>
//</mailinglist>

@Data
@XmlRootElement(name = "mailinglist")
public class PayloadMailingList implements MobivateEntity {
	private String created;
	private String creatingUserId;
	private String id;
	private String ownerUserId;
	private Integer version;
	private String name;
	private Integer recepientCount;
}