-- Create sequence for bookmark IDs
CREATE SEQUENCE bookmark_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Create bookmarks table
CREATE TABLE bookmarks (
    id BIGINT NOT NULL DEFAULT nextval('bookmark_id_seq'),
    title VARCHAR(255) NOT NULL,
    url VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    is_favorite BOOLEAN DEFAULT FALSE,
    CONSTRAINT pk_bookmarks PRIMARY KEY (id)
);

-- Add indexes for common queries
CREATE INDEX idx_bookmarks_title ON bookmarks (title);
CREATE INDEX idx_bookmarks_url ON bookmarks (url);
CREATE INDEX idx_bookmarks_favorite ON bookmarks (is_favorite) WHERE is_favorite = TRUE;

-- Add comment to the table
COMMENT ON TABLE bookmarks IS 'Stores browser bookmarks';