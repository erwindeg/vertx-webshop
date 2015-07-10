package nl.edegier.webshop.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import nl.edegier.webshop.service.ProductRestService;

public class WebVerticle extends AbstractVerticle {
	
	private static final int PORT = 8080;
	private static final String PATH = "app";
	private static final String welcomePage = "index.html";
	ProductRestService productRestService;
	
	

	@Override
	public void start() throws Exception {
	    MongoClient mongoClient = MongoClient.createShared(vertx, new JsonObject());
//		MongoService mongoService = MongoService.createEventBusProxy(vertx, PersistenceVerticle.MONGO_ADDRESS);
		productRestService = new ProductRestService(mongoClient);
		
//		vertx.createHttpServer(new HttpServerOptions().setPort(PORT)).requestHandler(req -> setupRouter().accept(req)).listen();
	}
	
//	private Router setupRouter() {
//		Router router = Router.router(vertx);
//		router.get("/").handler(context -> context.response().sendFile(PATH + "/" + welcomePage));
//		router.get("/" + PATH + "/*").handler(context -> context.response().sendFile(context.request().path().substring(1)));
//		router.get("/api/hello-world").handler(context -> context.response().end("{\"content\" : \"Hello world!\" }"));
//		router.get("/api/product").handler(productRestService::getProducts);
//		router.get("/api/product/:id").handler(productRestService::getProductById);
//		
//		return router;
//	}

}
