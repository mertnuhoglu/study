DROP TABLE IF EXISTS product CASCADE;
CREATE TABLE product (  
  product_id SERIAL PRIMARY KEY
  , title TEXT NOT NULL
  -- df product_title: word=product_title.txt
  -- df: text=product_title length=1
);
DROP TABLE IF EXISTS purchase_order CASCADE;
CREATE TABLE purchase_order (  --df: mult=0.1
  purchase_order_id SERIAL PRIMARY KEY
);
DROP TABLE IF EXISTS order_line CASCADE;
CREATE TABLE order_line (  --df: mult=0.5
  product_id INTEGER NOT NULL REFERENCES product (product_id)
  , purchase_order_id INTEGER NOT NULL REFERENCES purchase_order (purchase_order_id)
  , amount INTEGER NOT NULL --df: offset=1 size=5
  , PRIMARY KEY (product_id, purchase_order_id)
);
