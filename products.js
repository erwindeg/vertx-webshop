db = connect("localhost:27017/DEFAULT_DB");
db.product.remove({});
db.product.insert({_id: "559fdf72af06c921a8e092f5", name: "Whiskas kattenvoer", price: 2.80});
db.product.insert({_id: "559fdf72af06c921a8e092f6", name: "Sheba brokjes", price: 4.50});
db.product.insert({_id: "559fdf72af06c921a8e092f7", name: "Felix zakjes", price: 3.80});
db.product.insert({_id: "559fdf72af06c921a8e092f8", name: "ROyal Canin brokjes", price: 1.50});