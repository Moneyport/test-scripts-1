package com.mojaloop.fsp;

import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

@RestController
@RequestMapping("/payerfsp")
public class PayerFsp {

    private Logger logger = Logger.getLogger(PayerFsp.class.getName());

    private HashMap<String,String> entityMap = new HashMap<String,String>();

    @RequestMapping(method = { RequestMethod.GET }, value = { "/version" })
    public String getVersion() {
        return "1.0";
    }

    @RequestMapping(value = "/participants/{Type}/{Id}",method = RequestMethod.PUT)
    public HttpStatus putParticipants(@PathVariable("Type") String type, @PathVariable("Id") String id, @RequestHeader("X-Forwarded-For") String correlationId, @RequestBody String payload) throws IOException {
        logger.info("Id: " + id + " Body: " + payload);
        entityMap.put(id, payload);
        return HttpStatus.OK;
    }

    @RequestMapping(value = "/participants/{Type}/{Id}/error",method = RequestMethod.PUT)
    public void putParticipantsError(@PathVariable("Type") String type, @PathVariable("Id") String id, @RequestHeader("X-Forwarded-For") String correlationId, @RequestBody String payload) throws IOException {
        if(correlationId.indexOf(",") != -1)
            correlationId = correlationId.substring(0, correlationId.indexOf(","));
        entityMap.put(correlationId, payload);

    }

    @RequestMapping(value = "/parties/{Type}/{Id}",method = RequestMethod.POST)
    public String postParties(@PathVariable("Type") String type, @PathVariable("Id") String id, @RequestBody String payload) throws IOException {
        logger.info("Entered in payerfsp");
        entityMap.put(id,payload);
        return "200";
    }

    @RequestMapping(value = "/parties/{Type}/{Id}",method = RequestMethod.PUT)
    public HttpStatus putParties(@PathVariable("Type") String type, @PathVariable("Id") String id, @RequestHeader("X-Forwarded-For") String correlationId, @RequestBody String payload) throws IOException {
        logger.info("Id: " + id + " Body: " + payload);
        entityMap.put(id, payload);

        return HttpStatus.OK;
    }

    @RequestMapping(value = "/parties/{Type}/{Id}/error",method = RequestMethod.PUT)
    public void putPartiesError(@PathVariable("Type") String type, @PathVariable("Id") String id, @RequestHeader("X-Forwarded-For") String correlationId, @RequestBody String payload) throws IOException {
        if(correlationId.indexOf(",") != -1)
            correlationId = correlationId.substring(0, correlationId.indexOf(","));
        entityMap.put(correlationId, payload);

    }

    @RequestMapping(value = "/quotes/{quoteId}",method = RequestMethod.PUT)
    public HttpStatus putQuotes(@PathVariable("quoteId") String quoteId, @RequestHeader("X-Forwarded-For") String correlationId, @RequestBody String payload) throws IOException {
        logger.info("QuoteId: " + quoteId + " Body: " + payload);
//        if(correlationId.indexOf(",") != -1)
//            correlationId = correlationId.substring(0, correlationId.indexOf(","));
        entityMap.put(quoteId, payload);

        return HttpStatus.OK;
    }

    @RequestMapping(value = "/quotes/{quoteId}/error",method = RequestMethod.PUT)
    public void putQuotesError(@PathVariable("quoteId") String quoteId, @RequestHeader("X-Forwarded-For") String correlationId, @RequestBody String payload) throws IOException {
        if(correlationId.indexOf(",") != -1)
            correlationId = correlationId.substring(0, correlationId.indexOf(","));
        entityMap.put(correlationId, payload);

    }

    @RequestMapping(value = "/transfers/{transferId}",method = RequestMethod.PUT)
    public HttpStatus putTransfers(@PathVariable("transferId") String transferId, @RequestHeader("X-Forwarded-For") String correlationId, @RequestBody String payload) throws IOException {
        logger.info("TransferId: " + transferId + " Body: " + payload);
//        if(correlationId.indexOf(",") != -1)
//            correlationId = correlationId.substring(0, correlationId.indexOf(","));
        entityMap.put(transferId, payload);

        return HttpStatus.OK;
    }

    @RequestMapping(value = "/transfers/{transferId}/error",method = RequestMethod.PUT)
    public void putTransfersError(@PathVariable("transferId") String transferId, @RequestHeader("X-Forwarded-For") String correlationId, @RequestBody String payload) throws IOException {
        if(correlationId.indexOf(",") != -1)
            correlationId = correlationId.substring(0, correlationId.indexOf(","));
        entityMap.put(correlationId, payload);

    }

    @RequestMapping(value = "/correlationid", method = RequestMethod.POST)
    public void addCorrelationId(@RequestBody String payload){
        JsonParser jsonParser = new JacksonJsonParser();
        entityMap.put((String)jsonParser.parseMap(payload).get("correlationId"),(String)jsonParser.parseMap(payload).get("correlationId"));
    }

    @RequestMapping(value = "/correlationid/{correlationId}", method = RequestMethod.GET)
    public String getCorrelationId(@PathVariable String correlationId){
        logger.info("correlationId in getCorrelationId: "+correlationId);
        return entityMap.get(correlationId);
    }

}
