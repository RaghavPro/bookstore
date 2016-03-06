-- I converted from MySQL to Postgres. That's why this schema looks weird.
-- ~Raghav
--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: book; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE book (
    isbn character varying(20) NOT NULL,
    title character varying(340) NOT NULL,
    author character varying(100) NOT NULL,
    price smallint NOT NULL,
    mrp smallint,
    pages character varying(22) DEFAULT NULL::character varying,
    lang character varying(20) DEFAULT NULL::character varying,
    dimensions character varying(50) DEFAULT NULL::character varying,
    publisher character varying(400) DEFAULT NULL::character varying,
    summary text,
    about_author text,
    review text,
    rank integer NOT NULL
);


--
-- Name: book_category; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE book_category (
    isbn character varying(20) NOT NULL,
    categoryid bigint NOT NULL
);


--
-- Name: category; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE category (
    categoryid bigint NOT NULL,
    categoryName character varying(150) DEFAULT NULL::character varying,
    parent bigint
);


--
-- Name: orders; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE orders (
    orderid integer NOT NULL,
    isbn character varying(20) NOT NULL,
    userid integer NOT NULL,
    quantity integer,
    status integer DEFAULT 1
);


--
-- Name: COLUMN orders.status; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN orders.status IS '1 - pending 2 - approved 3 - rejected';


--
-- Name: review_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE review_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: review; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE review (
    id integer DEFAULT nextval('review_id_seq'::regclass) NOT NULL,
    isbn character varying(20) DEFAULT NULL::character varying,
    reviewer character varying(100) DEFAULT NULL::character varying,
    stars integer,
    title character varying(340) DEFAULT NULL::character varying,
    review text,
    review_date date
);


--
-- Name: user; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE user (
    userId integer NOT NULL,
    firstname character varying(100) NOT NULL,
    lastname character varying(90) DEFAULT NULL::character varying,
    username character varying(32) NOT NULL,
    password character varying(200) NOT NULL,
    email character varying(90) NOT NULL,
    address text NOT NULL,
    gender character varying(12) NOT NULL,
    date date NOT NULL
);


--
-- Name: user_cart; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE user_cart (
    userid integer NOT NULL,
    isbn character varying(20) NOT NULL,
    quantity integer DEFAULT 1
);


--
-- Name: user_group_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE user_group_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: user_group; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE user_group (
    id integer DEFAULT nextval('user_group_id_seq'::regclass) NOT NULL,
    name character varying(90) NOT NULL
);


--
-- Name: user_userId_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE user_userId_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: user_userId_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE user_userId_seq OWNED BY user.userId;


--
-- Name: user_wishlist; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE user_wishlist (
    userid integer NOT NULL,
    isbn character varying(20) NOT NULL,
    quantity integer DEFAULT 1
);


--
-- Name: userId; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY user ALTER COLUMN userId SET DEFAULT nextval('"user_userId_seq"'::regclass);


--
-- Name: book_category_isbn_categoryid_key; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY book_category
    ADD CONSTRAINT book_category_isbn_categoryid_key UNIQUE (isbn, categoryid);


--
-- Name: book_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY book
    ADD CONSTRAINT book_pkey PRIMARY KEY (isbn);


--
-- Name: category_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY category
    ADD CONSTRAINT category_pkey PRIMARY KEY (categoryid);


--
-- Name: orders_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (orderid);


--
-- Name: review_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY review
    ADD CONSTRAINT review_pkey PRIMARY KEY (id);


--
-- Name: user_group_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY user_group
    ADD CONSTRAINT user_group_pkey PRIMARY KEY (id);


--
-- Name: user_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY user
    ADD CONSTRAINT user_pkey PRIMARY KEY ("userId");


--
-- Name: book_to_tsvector_idx; Type: INDEX; Schema: public; Owner: -; Tablespace: 
--

CREATE INDEX book_to_tsvector_idx ON book USING gin (to_tsvector('english'::regconfig, (((title)::text || ' '::text) || (author)::text)));


--
-- Name: orders_isbn_idx; Type: INDEX; Schema: public; Owner: -; Tablespace: 
--

CREATE INDEX orders_isbn_idx ON orders USING btree (isbn);


--
-- Name: orders_userid_idx; Type: INDEX; Schema: public; Owner: -; Tablespace: 
--

CREATE INDEX orders_userid_idx ON orders USING btree (userid);


--
-- Name: review_isbn_idx; Type: INDEX; Schema: public; Owner: -; Tablespace: 
--

CREATE INDEX review_isbn_idx ON review USING btree (isbn);


--
-- Name: user_cart_isbn_idx; Type: INDEX; Schema: public; Owner: -; Tablespace: 
--

CREATE INDEX user_cart_isbn_idx ON user_cart USING btree (isbn);


--
-- Name: user_wishlist_isbn_idx; Type: INDEX; Schema: public; Owner: -; Tablespace: 
--

CREATE INDEX user_wishlist_isbn_idx ON user_wishlist USING btree (isbn);


--
-- Name: isbn_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_cart
    ADD CONSTRAINT isbn_fk FOREIGN KEY (isbn) REFERENCES book(isbn) DEFERRABLE INITIALLY DEFERRED;


--
-- Name: isbn_fk_orders; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT isbn_fk_orders FOREIGN KEY (isbn) REFERENCES book(isbn) DEFERRABLE INITIALLY DEFERRED;


--
-- Name: isbn_fk_wishlist; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_wishlist
    ADD CONSTRAINT isbn_fk_wishlist FOREIGN KEY (isbn) REFERENCES book(isbn) DEFERRABLE INITIALLY DEFERRED;


--
-- Name: review_ibfk_1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY review
    ADD CONSTRAINT review_ibfk_1 FOREIGN KEY (isbn) REFERENCES book(isbn) DEFERRABLE INITIALLY DEFERRED;


--
-- Name: userid_fk_orders; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT userid_fk_orders FOREIGN KEY (userid) REFERENCES "user"("userId") DEFERRABLE INITIALLY DEFERRED;

--
-- PostgreSQL database dump complete
--

