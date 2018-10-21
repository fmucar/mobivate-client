package com.cooldatasoft.sms.data.entity;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

//<xaresponse>
//	<authentication>
//		<code>0</code>
//		<text>OK</text>
//		</authentication>
//
//	<optout>
//		<created>2014-02-19T22:37:44+0000</created>
//		<id>4C4C2E760A4999ED7BBC0C2DA96198A6</id>
//		<email/>
//		<mobile/>
//	</optout>
//</xaresponse>

@Data
@XmlRootElement(name = "optout")
public class PayloadOptoutData implements MobivateEntity {

	private String created;
	private String id;
	private String email;
	private String mobile;
}