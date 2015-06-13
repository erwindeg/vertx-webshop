package nl.edegier.webshop.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoService;
import io.vertx.ext.mongo.MongoServiceVerticle;

import java.math.BigDecimal;
import java.util.Random;

public class DataGeneratorVerticle extends AbstractVerticle {

	public static final String MONGO_ADDRESS = "vertx.mongo";
	MongoService proxy;
	String[] animals = new String[] { "Dogs", "Cats", "Birds", "Fish", "Rodents" };
	String[] types = new String[] { "Food", "Care", "Housing", "Litter", "Toys" };
	String[] brands = new String[] { "HappyCat", "Royal Canin", "Bonzo", "Frolic", "Pedigree" };
	String[] names = new String[] { "Balls", "Biscuits", "Crackers", "Snacks", "Straw" };

	@Override
	public void start() throws Exception {

		setUpMongo();

		for (int i = 0; i < 1000; i++) {
			JsonObject product = new JsonObject();
			product.put("categories", new JsonArray().add(animals[new Random().nextInt(animals.length)]).add(types[new Random().nextInt(types.length)]));
			product.put("price", new Random().nextDouble() * 10);
			product.put("brand", brands[new Random().nextInt(brands.length)]);
			product.put("name", names[new Random().nextInt(names.length)]);
			// proxy.save("petproduct",product, null);
		}

		getImages("balls");

	}

	public void getImages(String name) {
		String url = "/search?q=%s&source=lnms&tbm=isch";
		url = url.format(url, name);
		System.out.println("Url: "+url);
		HttpClient client = vertx.createHttpClient();
		client.get(80,"www.google.nl",url).handler(response -> {
			Buffer entity = Buffer.buffer();
			response.handler(entity::appendBuffer);
			response.endHandler(v -> {
				String result = entity.toString("UTF-8");
				
				int begin = result.indexOf("<img");
				result = result.substring(begin);
				int end = result.indexOf(">");
				result = result.substring(0, end);
				begin = result.indexOf("src=");
//				result = result.substring(begin+4);
//				end = result.indexOf("\"");
//				result = result.substring(0,end);
				System.out.println(result);
			});
		}).end();
	}

	private void setUpMongo() {
		DeploymentOptions options = new DeploymentOptions().setConfig(new JsonObject().put("address", MONGO_ADDRESS).put("db_name", "sample_data"));
		vertx.deployVerticle(new MongoServiceVerticle(), options, res -> System.out.println(res.result()));
		this.proxy = MongoService.createEventBusProxy(vertx, MONGO_ADDRESS);
	}
}
