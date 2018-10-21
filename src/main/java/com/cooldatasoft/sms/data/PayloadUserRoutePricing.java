package com.cooldatasoft.sms.data;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement(name = "userroutepricing")
public class PayloadUserRoutePricing {

	private String countryCode;
	private String countryId;
	private String countryName;
	private String description;
	private String id;
	private String userId;
	private String userRouteId;

	private PayloadPrice price;
	private PayloadCost cost;
}
