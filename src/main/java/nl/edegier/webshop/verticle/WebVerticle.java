package nl.edegier.webshop.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;
import nl.edegier.webshop.service.OrderRestService;
import nl.edegier.webshop.service.ProductRestService;

public class WebVerticle extends AbstractVerticle {
	
	private static final int PORT = 9080;
	private static final String PATH = "app";
	private static final String WELCOME_PAGE = "index.html";
	ProductRestService productRestService;
	OrderRestService orderRestService;
	
	

	@Override
	public void start() throws Exception {
	    MongoClient mongoClient = MongoClient.createShared(vertx, new JsonObject());
		productRestService = new ProductRestService(mongoClient);
		orderRestService = new OrderRestService(mongoClient);
		vertx.createHttpServer(new HttpServerOptions().setPort(PORT)).requestHandler(req -> setupRouter().accept(req)).listen();
	}
	
	private Router setupRouter() {
		Router router = Router.router(vertx);
		router.route().handler(BodyHandler.create());
		router.get("/").handler(StaticHandler.create().setWebRoot(PATH).setIndexPage(WELCOME_PAGE));
		router.get("/" + PATH + "/*").handler(StaticHandler.create().setWebRoot(PATH));
		router.get("/api/hello-world").handler(context -> context.response().end("{\"content\" : \"Hello world!\" }"));
		router.get("/api/product").handler(productRestService::getProducts);
		router.get("/api/product/:id").handler(productRestService::getProductById);
		router.get("/api/order/:id").handler(orderRestService::getOrderById);
		router.post("/api/order").handler(orderRestService::saveOrder);
		
		return router;
	}

}
