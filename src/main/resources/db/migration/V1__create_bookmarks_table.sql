CREATE SEQUENCE IF NOT EXISTS bookmark_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE bookmarks
(
    id          BIGINT                      NOT NULL,
    title       VARCHAR(255)                NOT NULL,
    url         VARCHAR(255)                NOT NULL,
    description VARCHAR(1000),
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at  TIMESTAMP WITHOUT TIME ZONE,
    is_favorite BOOLEAN,
    CONSTRAINT pk_bookmarks PRIMARY KEY (id)
);