
    [product| product_id PK; product_name TEXT; ]
    [purchase_order| purchase_order_id PK; ]
    [order_line| order_line_id PK; product_id @NN; amount INT; ]
    [purchase_order] 1-n [order_line]
    [product] 1-n [order_line]
