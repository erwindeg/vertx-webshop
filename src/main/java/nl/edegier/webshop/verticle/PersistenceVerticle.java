package nl.edegier.webshop.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoServiceVerticle;

public class PersistenceVerticle extends AbstractVerticle {
	
	static final String MONGO_ADDRESS = "vertx.mongo";
	@Override
	public void start() throws Exception {
		DeploymentOptions options = new DeploymentOptions().setConfig(new JsonObject().put("address", MONGO_ADDRESS));
		vertx.deployVerticle(new MongoServiceVerticle(), options, res -> System.out.println(res.result()));
	
	}
}
