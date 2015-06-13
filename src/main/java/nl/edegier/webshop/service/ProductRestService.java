package nl.edegier.webshop.service;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.apex.RoutingContext;
import io.vertx.ext.mongo.MongoService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import nl.edegier.webshop.model.Product;

public class ProductRestService {
	List<Product> products;
	{
		products = Arrays.asList("Whiskas kattenvoer", "Sheba brokjes","Felix zakjes", "Royal Canin brokjes").stream().map(n -> new Product(n,new BigDecimal(new Random().nextDouble()*3))).collect(Collectors.toList()); 
	}

	private MongoService mongoService;
	private static final String PRODUCT = "product";

	public ProductRestService(MongoService mongoService) {
		this.mongoService = mongoService;
	}

	public void getProducts(RoutingContext rc) {
//		findProducts(rc.response(), new JsonObject());
		JsonArray products = new JsonArray();
		ObjectMapper mapper = new ObjectMapper();
		
		for(Product product : this.products){
			try {
				products.add(new JsonObject(mapper.writeValueAsString(product)));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		rc.response().end(products.toString());
	}

	public void getProductById(RoutingContext rc) {
		String id = rc.request().getParam("id");
		findProducts(rc.response(), new JsonObject().put("_id", id));
	}

	private void findProducts(HttpServerResponse response, JsonObject query) {
		this.mongoService.find(PRODUCT, query, res -> {
			response.end(new JsonArray(res.result()).toString());
		});
	}
}
