package nl.edegier.webshop.service;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.RoutingContext;

public class ProductRestService {

	private MongoClient mongoClient;
	private static final String PRODUCT = "product";

	public ProductRestService(MongoClient mongoClient) {
		this.mongoClient = mongoClient;		
	}

	public void getProducts(RoutingContext rc) {
		findProducts(rc.response(), new JsonObject());
	}

	public void getProductById(RoutingContext rc) {
		String id = rc.request().getParam("id");
		findProducts(rc.response(), new JsonObject().put("_id", id));
	}

	private void findProducts(HttpServerResponse response, JsonObject query) {
		this.mongoClient.find(PRODUCT, query, res -> {
			response.end(new JsonArray(res.result()).toString());
		});
	}
}
