package nl.edegier.webshop.verticle;

import io.vertx.core.AbstractVerticle;

public class MainVerticle extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		vertx.deployVerticle(WebVerticle.class.getName());
		vertx.deployVerticle(PersistenceVerticle.class.getName());
		
	}

//	private MongoService setUpMongo() {
//		return MongoService.createEventBusProxy(vertx, MONGO_ADDRESS);
//	}


}
