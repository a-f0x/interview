CREATE TABLE orders
(
    id            BIGSERIAL PRIMARY KEY,
    status        TEXT NOT NULL,
    created_at    TIMESTAMPTZ NOT NULL,
    updated_at    TIMESTAMPTZ
);

CREATE TABLE delivery(
    id            BIGSERIAL PRIMARY KEY,
    order_id      BIGINT CONSTRAINT delivery_order_id_fk REFERENCES orders (id)  ON DELETE CASCADE,
    address       TEXT NOT NULL,
    status        TEXT NOT NULL,
    created_at    TIMESTAMPTZ NOT NULL,
    updated_at    TIMESTAMPTZ
);
