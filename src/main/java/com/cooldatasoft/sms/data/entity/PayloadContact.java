package com.cooldatasoft.sms.data.entity;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

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

@Data
@XmlRootElement(name = "contact")
public class PayloadContact implements MobivateEntity {
	private String id;
	private String created;
	private String birthday;
	private String custom1;
	private String custom2;
	private String custom3;
	private String custom4;
	private String custom5;
	private String email;
	private String firstName;
	private String lastName;
	private String mobile;
}