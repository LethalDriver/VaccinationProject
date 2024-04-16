--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2
-- Dumped by pg_dump version 16.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: db; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA db;


ALTER SCHEMA db OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: _blacklisted_tokens; Type: TABLE; Schema: db; Owner: postgres
--

CREATE TABLE db._blacklisted_tokens (
    id integer NOT NULL,
    token character varying(255)
);


ALTER TABLE db._blacklisted_tokens OWNER TO postgres;

--
-- Name: _blacklisted_tokens_seq; Type: SEQUENCE; Schema: db; Owner: postgres
--

CREATE SEQUENCE db._blacklisted_tokens_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE db._blacklisted_tokens_seq OWNER TO postgres;

--
-- Name: _users; Type: TABLE; Schema: db; Owner: postgres
--

CREATE TABLE db._users (
    date_of_birth date,
    role smallint,
    id bigint NOT NULL,
    email character varying(255),
    first_name character varying(255),
    last_name character varying(255),
    notification_token character varying(255),
    password character varying(255),
    CONSTRAINT _users_role_check CHECK (((role >= 0) AND (role <= 1)))
);


ALTER TABLE db._users OWNER TO postgres;

--
-- Name: _users_seq; Type: SEQUENCE; Schema: db; Owner: postgres
--

CREATE SEQUENCE db._users_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE db._users_seq OWNER TO postgres;

--
-- Name: administered_vaccination; Type: TABLE; Schema: db; Owner: postgres
--

CREATE TABLE db.administered_vaccination (
    dose_number integer,
    date_time timestamp(6) without time zone,
    id bigint NOT NULL,
    user_id bigint,
    vaccine_id bigint
);


ALTER TABLE db.administered_vaccination OWNER TO postgres;

--
-- Name: administered_vaccination_seq; Type: SEQUENCE; Schema: db; Owner: postgres
--

CREATE SEQUENCE db.administered_vaccination_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE db.administered_vaccination_seq OWNER TO postgres;

--
-- Name: doses; Type: TABLE; Schema: db; Owner: postgres
--

CREATE TABLE db.doses (
    dose_number integer,
    months_after_previous_dose integer,
    id bigint NOT NULL,
    vaccine_id bigint
);


ALTER TABLE db.doses OWNER TO postgres;

--
-- Name: doses_seq; Type: SEQUENCE; Schema: db; Owner: postgres
--

CREATE SEQUENCE db.doses_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE db.doses_seq OWNER TO postgres;

--
-- Name: reminder; Type: TABLE; Schema: db; Owner: postgres
--

CREATE TABLE db.reminder (
    sent boolean NOT NULL,
    date_time timestamp(6) with time zone,
    id bigint NOT NULL,
    scheduled_vaccination_id bigint
);


ALTER TABLE db.reminder OWNER TO postgres;

--
-- Name: reminder_seq; Type: SEQUENCE; Schema: db; Owner: postgres
--

CREATE SEQUENCE db.reminder_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE db.reminder_seq OWNER TO postgres;

--
-- Name: scheduled_vaccination; Type: TABLE; Schema: db; Owner: postgres
--

CREATE TABLE db.scheduled_vaccination (
    dose_number integer,
    date_time timestamp(6) without time zone,
    id bigint NOT NULL,
    user_id bigint,
    vaccine_id bigint
);


ALTER TABLE db.scheduled_vaccination OWNER TO postgres;

--
-- Name: scheduled_vaccination_seq; Type: SEQUENCE; Schema: db; Owner: postgres
--

CREATE SEQUENCE db.scheduled_vaccination_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE db.scheduled_vaccination_seq OWNER TO postgres;

--
-- Name: vaccine; Type: TABLE; Schema: db; Owner: postgres
--

CREATE TABLE db.vaccine (
    recommended_age_months_lower_bound integer,
    recommended_age_months_upper_bound integer,
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE db.vaccine OWNER TO postgres;

--
-- Name: vaccine_seq; Type: SEQUENCE; Schema: db; Owner: postgres
--

CREATE SEQUENCE db.vaccine_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE db.vaccine_seq OWNER TO postgres;

--
-- Data for Name: _blacklisted_tokens; Type: TABLE DATA; Schema: db; Owner: postgres
--

COPY db._blacklisted_tokens (id, token) FROM stdin;
\.


--
-- Data for Name: _users; Type: TABLE DATA; Schema: db; Owner: postgres
--

COPY db._users (date_of_birth, role, id, email, first_name, last_name, notification_token, password) FROM stdin;
\N	\N	1	admin@email.com	\N	\N	\N	$2a$10$k9oZmqxqK5cqXPUhUpzDCu3UvBnCjWbgLTsgdmjPziiGtV/YusM5m
\.


--
-- Data for Name: administered_vaccination; Type: TABLE DATA; Schema: db; Owner: postgres
--

COPY db.administered_vaccination (dose_number, date_time, id, user_id, vaccine_id) FROM stdin;
\.


--
-- Data for Name: doses; Type: TABLE DATA; Schema: db; Owner: postgres
--

COPY db.doses (dose_number, months_after_previous_dose, id, vaccine_id) FROM stdin;
1	\N	1	1
2	2	2	1
3	2	3	1
4	60	4	1
5	0	5	1
1	\N	6	2
2	2	7	2
3	2	8	2
4	60	9	2
5	0	10	2
1	\N	11	3
2	2	12	3
3	2	13	3
4	60	14	3
5	0	15	3
1	\N	16	4
2	2	17	4
3	2	18	4
4	60	19	4
1	\N	20	5
2	0	21	5
1	\N	22	6
2	0	23	6
1	\N	24	7
1	120	25	8
1	\N	26	9
2	2	27	9
3	6	28	9
\.


--
-- Data for Name: reminder; Type: TABLE DATA; Schema: db; Owner: postgres
--

COPY db.reminder (sent, date_time, id, scheduled_vaccination_id) FROM stdin;
\.


--
-- Data for Name: scheduled_vaccination; Type: TABLE DATA; Schema: db; Owner: postgres
--

COPY db.scheduled_vaccination (dose_number, date_time, id, user_id, vaccine_id) FROM stdin;
\.


--
-- Data for Name: vaccine; Type: TABLE DATA; Schema: db; Owner: postgres
--

COPY db.vaccine (recommended_age_months_lower_bound, recommended_age_months_upper_bound, id, name) FROM stdin;
2	6	1	DTaP
2	6	2	IPV
2	6	3	Hib
2	6	4	PCV
12	15	5	MMR
12	15	6	Varicella
0	\N	7	Hepatitis B
11	\N	8	Tdap
11	\N	9	HPV
\.


--
-- Name: _blacklisted_tokens_seq; Type: SEQUENCE SET; Schema: db; Owner: postgres
--

SELECT pg_catalog.setval('db._blacklisted_tokens_seq', 1, false);


--
-- Name: _users_seq; Type: SEQUENCE SET; Schema: db; Owner: postgres
--

SELECT pg_catalog.setval('db._users_seq', 1, true);


--
-- Name: administered_vaccination_seq; Type: SEQUENCE SET; Schema: db; Owner: postgres
--

SELECT pg_catalog.setval('db.administered_vaccination_seq', 1, false);


--
-- Name: doses_seq; Type: SEQUENCE SET; Schema: db; Owner: postgres
--

SELECT pg_catalog.setval('db.doses_seq', 1, false);


--
-- Name: reminder_seq; Type: SEQUENCE SET; Schema: db; Owner: postgres
--

SELECT pg_catalog.setval('db.reminder_seq', 1, false);


--
-- Name: scheduled_vaccination_seq; Type: SEQUENCE SET; Schema: db; Owner: postgres
--

SELECT pg_catalog.setval('db.scheduled_vaccination_seq', 1, false);


--
-- Name: vaccine_seq; Type: SEQUENCE SET; Schema: db; Owner: postgres
--

SELECT pg_catalog.setval('db.vaccine_seq', 1, false);


--
-- Name: _blacklisted_tokens _blacklisted_tokens_pkey; Type: CONSTRAINT; Schema: db; Owner: postgres
--

ALTER TABLE ONLY db._blacklisted_tokens
    ADD CONSTRAINT _blacklisted_tokens_pkey PRIMARY KEY (id);


--
-- Name: _users _users_pkey; Type: CONSTRAINT; Schema: db; Owner: postgres
--

ALTER TABLE ONLY db._users
    ADD CONSTRAINT _users_pkey PRIMARY KEY (id);


--
-- Name: administered_vaccination administered_vaccination_pkey; Type: CONSTRAINT; Schema: db; Owner: postgres
--

ALTER TABLE ONLY db.administered_vaccination
    ADD CONSTRAINT administered_vaccination_pkey PRIMARY KEY (id);


--
-- Name: doses doses_pkey; Type: CONSTRAINT; Schema: db; Owner: postgres
--

ALTER TABLE ONLY db.doses
    ADD CONSTRAINT doses_pkey PRIMARY KEY (id);


--
-- Name: reminder reminder_pkey; Type: CONSTRAINT; Schema: db; Owner: postgres
--

ALTER TABLE ONLY db.reminder
    ADD CONSTRAINT reminder_pkey PRIMARY KEY (id);


--
-- Name: scheduled_vaccination scheduled_vaccination_pkey; Type: CONSTRAINT; Schema: db; Owner: postgres
--

ALTER TABLE ONLY db.scheduled_vaccination
    ADD CONSTRAINT scheduled_vaccination_pkey PRIMARY KEY (id);


--
-- Name: vaccine vaccine_pkey; Type: CONSTRAINT; Schema: db; Owner: postgres
--

ALTER TABLE ONLY db.vaccine
    ADD CONSTRAINT vaccine_pkey PRIMARY KEY (id);


--
-- Name: doses fk3jc193obeopkhfelwcpywhbwk; Type: FK CONSTRAINT; Schema: db; Owner: postgres
--

ALTER TABLE ONLY db.doses
    ADD CONSTRAINT fk3jc193obeopkhfelwcpywhbwk FOREIGN KEY (vaccine_id) REFERENCES db.vaccine(id);


--
-- Name: scheduled_vaccination fka52tujvpv6liq3ictenvi2jhs; Type: FK CONSTRAINT; Schema: db; Owner: postgres
--

ALTER TABLE ONLY db.scheduled_vaccination
    ADD CONSTRAINT fka52tujvpv6liq3ictenvi2jhs FOREIGN KEY (vaccine_id) REFERENCES db.vaccine(id);


--
-- Name: scheduled_vaccination fkb4lr9yc2e8bsmtmqvh446vwn8; Type: FK CONSTRAINT; Schema: db; Owner: postgres
--

ALTER TABLE ONLY db.scheduled_vaccination
    ADD CONSTRAINT fkb4lr9yc2e8bsmtmqvh446vwn8 FOREIGN KEY (user_id) REFERENCES db._users(id);


--
-- Name: administered_vaccination fkcbprywcliwj9s48b4d4mi1jwi; Type: FK CONSTRAINT; Schema: db; Owner: postgres
--

ALTER TABLE ONLY db.administered_vaccination
    ADD CONSTRAINT fkcbprywcliwj9s48b4d4mi1jwi FOREIGN KEY (user_id) REFERENCES db._users(id);


--
-- Name: administered_vaccination fkig23fvn9r68reaxh6a9o2sxn; Type: FK CONSTRAINT; Schema: db; Owner: postgres
--

ALTER TABLE ONLY db.administered_vaccination
    ADD CONSTRAINT fkig23fvn9r68reaxh6a9o2sxn FOREIGN KEY (vaccine_id) REFERENCES db.vaccine(id);


--
-- Name: reminder fko3ijehgaxkkuh8hcajb8v3n02; Type: FK CONSTRAINT; Schema: db; Owner: postgres
--

ALTER TABLE ONLY db.reminder
    ADD CONSTRAINT fko3ijehgaxkkuh8hcajb8v3n02 FOREIGN KEY (scheduled_vaccination_id) REFERENCES db.scheduled_vaccination(id);


--
-- PostgreSQL database dump complete
--

