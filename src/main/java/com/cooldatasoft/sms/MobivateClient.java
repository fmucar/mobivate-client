package com.cooldatasoft.sms;

import com.cooldatasoft.sms.data.PayloadAuthentication;
import com.cooldatasoft.sms.data.PayloadData;
import com.cooldatasoft.sms.data.PayloadEntityList;
import com.cooldatasoft.sms.data.PayloadUserRoutePricing;
import com.cooldatasoft.sms.data.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.inject.Named;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*************************************************************
 * @author Fatih Mehmet UCAR - fmucar@cooldatasoft.com
 *
 **************************************************************/

@Slf4j
@Named
public class MobivateClient {

    private static final String TEXT_ORIGINATOR = "fromNumber";
    private static final String PASSWORD = "user";
    private static final String USERNAME = "password";
    private final static String USER_AGENT = "agent";

    public String login() {
        return login(USERNAME, PASSWORD);
    }

    public String login(String username, String password) {
        Response response = null;
        try {
            ResteasyClient client = new ResteasyClientBuilder().build();
            String url = String.format("http://app.mobivatebulksms.com/bulksms/xmlapi/login/%s/%s", username, password);
            log.debug("Login URL : {} ", url);
            ResteasyWebTarget target = client.target(url);
            response = target.request().header("User-Agent", USER_AGENT).get();
            PayloadData readEntity = response.readEntity(PayloadData.class);

            checkSecurity(readEntity.getAuthentication());

            log.debug("Session : {} ", readEntity.getSession());
            return readEntity.getSession();
        } finally {
            if (null != response) {
                response.close();
                log.debug("Closing response");
            }
        }
    }

    public PayloadUserRoutePricing getAvailableRoutes(String session) {
        Response response = null;
        try {
            String url = String.format("http://app.mobivatebulksms.com/bulksms/xmlapi/%s/entity/user.UserRoutePricing/all/visible", session);
            log.debug("Login URL : {} ", url);
            ResteasyClient client = new ResteasyClientBuilder().build();
            ResteasyWebTarget target = client.target(url);
            response = target.request().header("User-Agent", USER_AGENT).get();
            PayloadData readEntity = response.readEntity(PayloadData.class);

            checkSecurity(readEntity.getAuthentication());

            log.debug("getAvailableRoutes Response : {} ", readEntity.getEntitylist().getUserroutepricing());
            return readEntity.getEntitylist().getUserroutepricing();
        } finally {
            if (null != response) {
                response.close();
                log.debug("Closing response");
            }
        }
    }

    private void checkSecurity(PayloadAuthentication authentication) {
        if (null == authentication || null == authentication.getCode() || authentication.getCode() != 0) {
            log.error("Error : {} ", authentication);
            throw new MobivateException(authentication);
        }
        log.debug("Auth : {} ", authentication);
    }

    public PayloadDeliveryMessage getMessageDeliveryTemplate(String session) {
        return getEntityTemplate(session, EntityType.MessageDelivery).getDeliverymessage();
    }

    public PayloadContact getContactTemplate(String session) {
        return getEntityTemplate(session, EntityType.Contact).getContact();
    }

    public PayloadBatchMessageData getBatchMessageDataTemplate(String session) {
        return getEntityTemplate(session, EntityType.BatchMessageData).getLogicalmessage();
    }

    public PayloadMailingList getMailingListTemplate(String session) {
        return getEntityTemplate(session, EntityType.MailingList).getMailinglist();
    }

    public PayloadOptoutData getOptoutDataTemplate(String session) {
        return getEntityTemplate(session, EntityType.OptoutData).getOptout();
    }

    private PayloadData getEntityTemplate(String session, EntityType entityType) {
        Response response = null;
        try {
            String url = String.format("http://app.mobivatebulksms.com/bulksms/xmlapi/%s/entity/%s/new", session, entityType.getType());
            log.debug("Login URL : {} ", url);
            ResteasyClient client = new ResteasyClientBuilder().build();
            ResteasyWebTarget target = client.target(url);
            response = target.request().header("User-Agent", USER_AGENT).get();

            PayloadData readEntity = response.readEntity(PayloadData.class);

            checkSecurity(readEntity.getAuthentication());

            log.debug("getEntityTemplate : {} ", readEntity);
            return readEntity;
        } finally {
            if (null != response) {
                response.close();
                log.debug("Closing response");
            }
        }
    }

    public PayloadContact createContact(String session, PayloadContact entity) {
        return createEntity(session, EntityType.Contact, entity).getContact();
    }

    public PayloadBatchMessageData createBatchMessageData(String session, PayloadBatchMessageData entity) {
        return createEntity(session, EntityType.BatchMessageData, entity).getLogicalmessage();
    }

    public PayloadMailingList createMailingList(String session, PayloadMailingList entity) {
        return createEntity(session, EntityType.MailingList, entity).getMailinglist();
    }

    public PayloadOptoutData createOptoutData(String session, PayloadOptoutData entity) {
        return createEntity(session, EntityType.OptoutData, entity).getOptout();
    }

    private PayloadData createEntity(String session, EntityType entityType, MobivateEntity entity) {
        Response response = null;
        try {
            String url = String.format("http://app.mobivatebulksms.com/bulksms/xmlapi/%s/entity/%s", session, entityType.getType());
            log.debug("Login URL : {} ", url);

            ResteasyClient client = new ResteasyClientBuilder().build();
            ResteasyWebTarget target = client.target(url);

            String payload = toXml(entity);
            response = target.request().header("User-Agent", USER_AGENT).post(Entity.entity(payload, MediaType.APPLICATION_FORM_URLENCODED_TYPE));

            PayloadData readEntity = response.readEntity(PayloadData.class);

            checkSecurity(readEntity.getAuthentication());

            log.debug("getEntityTemplate : {} ", readEntity);
            return readEntity;
        } finally {
            if (null != response) {
                response.close();
                log.debug("Closing response");
            }
        }
    }

    private String toXml(MobivateEntity entity) {
        String payload = "";
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(entity.getClass());
            StringWriter writer = new StringWriter();
            jaxbContext.createMarshaller().marshal(entity, writer);
            payload = String.format("xml=%s", writer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("XML Payload : {} ", payload);
        return payload;
    }

    public PayloadBatchSingle sendSms(String session, String mobileNumber, String textMessage, long startDate) {
        List<String> mobileNumberList = new ArrayList<>();
        mobileNumberList.add(mobileNumber);

        return sendSms(session, mobileNumberList, textMessage, startDate);
    }

    public PayloadBatchSingle sendSms(String session, List<String> mobileNumberList, String textMessage, long startDate) {
        return sendSms(session, mobileNumberList, textMessage, 0, startDate);
    }

    public PayloadBatchSingle sendSms(String session, List<String> mobileNumberList, String textMessage, Integer spreadOverHours, long startDate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");

        PayloadBatchSingle payloadBatchSingle = new PayloadBatchSingle();
        payloadBatchSingle.setBody(textMessage);
        payloadBatchSingle.setDetailedResponse(true);
        payloadBatchSingle.setOriginator(TEXT_ORIGINATOR);
        payloadBatchSingle.setProcessOnDelivery(true);
        payloadBatchSingle.setRouteId(getAvailableRoutes(session).getUserRouteId());
        payloadBatchSingle.setMessageSpread(spreadOverHours);
        payloadBatchSingle.setDeliveryTimeZone("Europe/London");
        payloadBatchSingle.setDeliverySchedule(dateFormat.format(new Date(startDate)));

        PayloadRecipients payloadRecipients = new PayloadRecipients();
        payloadBatchSingle.setRecipients(payloadRecipients);

        for (String mobileNumber : mobileNumberList) {
            PayloadRecipient payloadRecipient = new PayloadRecipient();
            payloadRecipient.setRecipient(mobileNumber);
            payloadRecipient.setType("MSISDN");
            payloadRecipients.getRecipient().add(payloadRecipient);
        }

        return sendSms(session, payloadBatchSingle);
    }

    public PayloadBatchSingle sendSms(String session, PayloadBatchSingle entity) {
        Response response = null;
        try {
            String url = String.format("http://app.mobivatebulksms.com/bulksms/xmlapi/%s/send/sms/batch", session);
            log.debug("Login URL : {} ", url);

            String payload = toXml(entity);
            log.info("SendSms request payload : {} ", payload);

            ResteasyClient client = new ResteasyClientBuilder().build();
            ResteasyWebTarget target = client.target(url);
            response = target.request().header("User-Agent", USER_AGENT).post(Entity.entity(payload, MediaType.APPLICATION_FORM_URLENCODED_TYPE));

            PayloadData readEntity = response.readEntity(PayloadData.class);

            checkSecurity(readEntity.getAuthentication());

            log.debug("result : {} ", readEntity.getBatchsingle());
            return readEntity.getBatchsingle();
        } finally {
            if (null != response) {
                response.close();
                log.debug("Closing response");
            }
        }
    }

    public PayloadDeliveryMessage getMessageDelivery(String session, String id) {
        return getEntity(session, EntityType.MessageDelivery, id).getDeliverymessage();
    }

    public PayloadContact getContact(String session, String id) {
        return getEntity(session, EntityType.Contact, id).getContact();
    }

    public PayloadBatchMessageData getBatchMessageData(String session, String id) {
        return getEntity(session, EntityType.BatchMessageData, id).getLogicalmessage();
    }

    public PayloadMailingList getMailingList(String session, String id) {
        return getEntity(session, EntityType.MailingList, id).getMailinglist();
    }

    public PayloadOptoutData getOptoutData(String session, String id) {
        return getEntity(session, EntityType.OptoutData, id).getOptout();
    }

    private PayloadData getEntity(String session, EntityType entityType, String id) {
        Response response = null;
        try {
            String url = String.format("http://app.mobivatebulksms.com/bulksms/xmlapi/%s/entity/%s/%s", session, entityType.getType(), id);
            log.debug("URL : {} ", url);

            ResteasyClient client = new ResteasyClientBuilder().build();
            ResteasyWebTarget target = client.target(url);
            response = target.request().header("User-Agent", USER_AGENT).get();

            PayloadData readEntity = response.readEntity(PayloadData.class);

            checkSecurity(readEntity.getAuthentication());

            log.debug("result : {} ", readEntity);
            return readEntity;
        } finally {
            if (null != response) {
                response.close();
                log.debug("Closing response");
            }
        }
    }

    public PayloadEntityList getMessageDeliveryList(String session, Integer offsett, Integer limit) {
        return getEntityList(session, EntityType.MessageDelivery, offsett, limit).getEntitylist();
    }


    public PayloadData getEntityList(String session, EntityType entityType, Integer offsett, Integer limit) {
        Response response = null;
        try {
            String url = String.format("http://app.mobivatebulksms.com/bulksms/xmlapi/%s/entity/%s?offset=%d&limit=%d", session, entityType.getType(), offsett,
                    limit);
            log.debug("URL : {} ", url);

            ResteasyClient client = new ResteasyClientBuilder().build();
            ResteasyWebTarget target = client.target(url);
            response = target.request().header("User-Agent", USER_AGENT).get();

            PayloadData readEntity = response.readEntity(PayloadData.class);

            checkSecurity(readEntity.getAuthentication());

            log.debug("result : {} ", readEntity);
            return readEntity;
        } finally {
            if (null != response) {
                response.close();
                log.debug("Closing response");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        MobivateClient mobivateClient = new MobivateClient();
        String session = mobivateClient.login(USERNAME, PASSWORD);
        PayloadEntityList messageDeliveryList = mobivateClient.getMessageDeliveryList(session, 0, 100);

        log.info("\n\n\n\n\n\n\n");
        log.info("*************************************************************");
        log.info("***** : {}", messageDeliveryList.getTotal());
        log.info("***** : {}", messageDeliveryList.getDeliverymessage().size());

        for (PayloadDeliveryMessage p : messageDeliveryList.getDeliverymessage()) {
            System.out.println(p);
        }
        log.info("*************************************************************");

    }

    public String updateEntity(String session, EntityType entityType, String xmlPayload, String id) {
        Response response = null;
        try {
            String url = "http://app.mobivatebulksms.com/bulksms/xmlapi/" + session + "/entity/" + entityType.getType() + "/" + id;
            String payload = "xml=" + xmlPayload;

            ResteasyClient client = new ResteasyClientBuilder().build();
            ResteasyWebTarget target = client.target(url);
            response = target.request().header("User-Agent", USER_AGENT).post(Entity.entity(payload, MediaType.APPLICATION_FORM_URLENCODED_TYPE));

            String readEntity = response.readEntity(String.class);

            log.info("Response : {} ", readEntity);
            return readEntity;
        } finally {
            if (null != response) {
                response.close();
            }
        }
    }

    public String getEntityList(String session, EntityType entityType, int offset, int limit) {
        Response response = null;
        try {
            String url = "http://app.mobivatebulksms.com/bulksms/xmlapi/" + session + "/entity/" + entityType.getType() + "?offset=" + offset + "&limit="
                    + limit;

            ResteasyClient client = new ResteasyClientBuilder().build();
            ResteasyWebTarget target = client.target(url);
            response = target.request().header("User-Agent", USER_AGENT).get();

            String readEntity = response.readEntity(String.class);

            log.info("Response : {} ", readEntity);
            return readEntity;
        } finally {
            if (null != response) {
                response.close();
            }
        }
    }


    public PayloadMailingList createList(String session, String listName) throws UnsupportedEncodingException {
        Response response = null;
        try {
            String url = "http://app.mobivatebulksms.com/bulksms/xmlapi/" + session + "/entity/addressbook.MailingList/new";
            String payload = "xml=<mailinglist><name>List Name</name></mailinglist>";
            log.info("CreateList URL : {} ", url);

            ResteasyClient client = new ResteasyClientBuilder().build();
            ResteasyWebTarget target = client.target(url);

            response = target.request().header("User-Agent", USER_AGENT).post(Entity.entity(payload, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
            // TODO return error if fails
            PayloadData readEntity = response.readEntity(PayloadData.class);

            if (null == readEntity.getAuthentication() || null == readEntity.getAuthentication().getCode() || readEntity.getAuthentication().getCode() != 0) {
                log.error("Error : {} ", readEntity.getAuthentication());
                throw new MobivateException(readEntity.getAuthentication());
            }

            log.info("Auth : {} ", readEntity.getAuthentication());
            log.info("CreateList Response : {} ", readEntity.getMailinglist());
            return readEntity.getMailinglist();
        } finally {
            if (null != response) {
                response.close();
            }
        }
    }

    public boolean addContactToList(String session, String listId, String contactId) {
        Response response = null;
        try {
            String url = "http://app.mobivatebulksms.com/bulksms/xmlapi/" + session + "/entity/addressbook.MailingList/add/" + listId + "/contact/" + contactId;

            ResteasyClient client = new ResteasyClientBuilder().build();
            ResteasyWebTarget target = client.target(url);
            response = target.request().header("User-Agent", USER_AGENT).get();
            // TODO return error if fails
            PayloadData readEntity = response.readEntity(PayloadData.class);

            if (null == readEntity.getAuthentication() || null == readEntity.getAuthentication().getCode() || readEntity.getAuthentication().getCode() != 0) {
                log.error("Error : {} ", readEntity.getAuthentication());
                throw new MobivateException(readEntity.getAuthentication());
            }

            log.info("addContactToList Response : {} ", readEntity);
            return true;
        } finally {
            if (null != response) {
                response.close();
            }
        }
    }

}
