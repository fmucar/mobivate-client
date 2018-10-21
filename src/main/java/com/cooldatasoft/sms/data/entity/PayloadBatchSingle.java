package com.cooldatasoft.sms.data.entity;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

//<batchsingle>
//	<body>deneme mesaji</body>
//	<creatingUserId>58411423-911f-4c1d-bdd7-e9ec990062a4</creatingUserId>
//	<detailedResponse>true</detailedResponse>
//	<filterOptouts>true</filterOptouts>
//	<id>4D04A0330A4999ED7BBC0C2D0BE7518F</id>
//	<messageSpread>0</messageSpread>
//	<originator>07581779450</originator>
//	<processOnDelivery>false</processOnDelivery>
//	<routeId>3d7c4a51-c68b-4ee4-b83b-822bf7eb293f</routeId>
//	<filterDuplicaets>true</filterDuplicaets>
//	<deliveryTimeZone>Australia/Melbourne</deliveryTimeZone>  
//	<deliverySchedule>2012-06-25T14:20:00</deliverySchedule>
//	<recipients>
//		<recipient><id>4D04A0330A4999ED7BBC0C2D74DA5CBC</id><recipient>447581779450</recipient><type>MSISDN</type></recipient>
//		<recipient><id>4D04A0330A4999ED7BBC0C2D98933287</id><recipient>447935755151</recipient><type>MSISDN</type></recipient>
//	</recipients>
//</batchsingle>

//<batchsingle><body>deneme mesaji</body><creatingUserId>58411423-911f-4c1d-bdd7-e9ec990062a4</creatingUserId><detailedResponse>true</detailedResponse><filterOptouts>true</filterOptouts><id>50FFAB530A4999ED7BBC0C2DE6CBAAE2</id><messageSpread>1</messageSpread><originator>07581779450</originator><processOnDelivery>false</processOnDelivery><routeId>3d7c4a51-c68b-4ee4-b83b-822bf7eb293f</routeId><filterDuplicaets>true</filterDuplicaets><recipients><recipient><id>50FFAB530A4999ED7BBC0C2DF9251DAD</id><recipient>447581779450</recipient><type>MSISDN</type></recipient><recipient><id>50FFAB530A4999ED7BBC0C2D3AE1BEB0</id><recipient>447581779450</recipient><type>MSISDN</type></recipient><recipient><id>50FFAB530A4999ED7BBC0C2DE13165FC</id><recipient>447581779450</recipient><type>MSISDN</type></recipient></recipients></batchsingle>

@Data
@XmlRootElement(name = "batchsingle")
public class PayloadBatchSingle implements MobivateEntity {

	private String body;
	private Boolean detailedResponse;
	private Boolean filterOptouts;
	private String originator;
	private String routeId;
	private Boolean filterDuplicates;
	private PayloadRecipients recipients;

	private String creatingUserId;
	private String id;
	private Integer messageSpread;
	private Boolean processOnDelivery;
	private Boolean filterDuplicaets;
	
	private String deliveryTimeZone = "Europe/London";
	private String deliverySchedule;

}
