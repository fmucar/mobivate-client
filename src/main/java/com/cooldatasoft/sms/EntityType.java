package com.cooldatasoft.sms;

public enum EntityType {

	Contact("addressbook.Contact"), MailingList("addressbook.MailingList"), OptoutData("addressbook.Optout"), BatchMessageData("message.LogicalMessage"),
	MessageDelivery("message.DeliveryMessage");

	private String type;

	EntityType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}
}
