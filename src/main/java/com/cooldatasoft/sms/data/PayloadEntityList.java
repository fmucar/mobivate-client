package com.cooldatasoft.sms.data;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

import com.cooldatasoft.sms.data.entity.PayloadDeliveryMessage;

@Data
@XmlRootElement(name = "entitylist")
@XmlAccessorType(XmlAccessType.FIELD)
public class PayloadEntityList {

	private PayloadUserRoutePricing userroutepricing;

	private List<PayloadDeliveryMessage> deliverymessage;
	@XmlAttribute
	private Integer offset;
	@XmlAttribute
	private Integer total;

}
