package com.cooldatasoft.sms.data;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
public class PayloadCost {

	private Double amount;
	private String currency;
}
