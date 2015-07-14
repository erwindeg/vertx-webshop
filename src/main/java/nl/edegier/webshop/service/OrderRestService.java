package nl.edegier.webshop.service;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.RoutingContext;

import java.util.UUID;

public class OrderRestService {

    private MongoClient mongoClient;
    private static final String ORDER = "order";

    public OrderRestService(MongoClient mongoClient) {
	this.mongoClient = mongoClient;
    }
    
    public void getOrderById(RoutingContext rc) {
	String id = rc.request().getParam("id");
	
	this.mongoClient.find(ORDER, new JsonObject().put("orderid", id), res -> {
		rc.response().end(new JsonArray(res.result()).getJsonObject(0).toString());
	});
    }

    public void saveOrder(RoutingContext rc) {
	JsonObject order = rc.getBodyAsJson().put("orderid", UUID.randomUUID().toString());
	this.mongoClient.save(ORDER, order , result -> {
	    if (result.succeeded()) {
		rc.response().end(order.toString());
	    }
	});

    }
    


}
