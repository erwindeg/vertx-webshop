package nl.edegier.webshop.service;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.mongo.MongoClient;

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

	private MongoClient mongoClient;
	private static final String PRODUCT = "product";

	public ProductRestService(MongoClient mongoClient) {
		this.mongoClient = mongoClient;	
		this.mongoClient.save(PRODUCT, new JsonObject().put("name","erwin"), res -> {

		      if (res.succeeded()) {

		        String id = res.result();
		        System.out.println("Saved book with id " + id);

		      } else {
		        res.cause().printStackTrace();
		      }

		    });
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
		this.mongoClient.find(PRODUCT, query, res -> {
			response.end(new JsonArray(res.result()).toString());
		});
	}
}
