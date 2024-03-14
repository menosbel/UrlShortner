CREATE TABLE url_info(
      id              SERIAL NOT NULL PRIMARY KEY,
      key_url         VARCHAR(20) NOT NULL,
      long_url        VARCHAR(500) NOT NULL,
      short_url       VARCHAR(50) NULL
);

CREATE UNIQUE INDEX url_info_key_uq ON url_info(LOWER(key_url));