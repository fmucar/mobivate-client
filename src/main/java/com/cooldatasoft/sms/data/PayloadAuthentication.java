package com.cooldatasoft.sms.data;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

//<authentication>
//<code>0</code>
//<text>OK</text>
//</authentication>

//0 Successful authentication 
//1 Invalid login  
//2 User disabled 
//3 Error 

@Data
@XmlRootElement
public class PayloadAuthentication {
	private Integer code;
	private String text;
}
