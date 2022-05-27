--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1
-- Dumped by pg_dump version 14.1

-- Started on 2022-05-27 15:42:23

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 209 (class 1259 OID 17352)
-- Name: clients; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.clients (
    dni character varying(9) NOT NULL,
    nom character varying(40),
    correo character varying(40),
    telefon integer,
    adreca character varying(40)
);


ALTER TABLE public.clients OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 17367)
-- Name: factures_client; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.factures_client (
    numfactura integer NOT NULL,
    "dniClient" character varying(9),
    data date
);


ALTER TABLE public.factures_client OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 17366)
-- Name: factures_client_numFactura_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."factures_client_numFactura_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."factures_client_numFactura_seq" OWNER TO postgres;

--
-- TOC entry 3338 (class 0 OID 0)
-- Dependencies: 210
-- Name: factures_client_numFactura_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."factures_client_numFactura_seq" OWNED BY public.factures_client.numfactura;


--
-- TOC entry 214 (class 1259 OID 17441)
-- Name: lineafactura; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.lineafactura (
    numfactura integer NOT NULL,
    numlinea integer NOT NULL,
    codi_producte integer,
    quantitat_producte integer,
    preu_producte double precision,
    total_producte double precision,
    iva_producte double precision
);


ALTER TABLE public.lineafactura OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 17376)
-- Name: productes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.productes (
    codi integer NOT NULL,
    nom character varying(50),
    stock integer,
    preu double precision,
    iva double precision
);


ALTER TABLE public.productes OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 17375)
-- Name: productes_codi_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.productes_codi_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.productes_codi_seq OWNER TO postgres;

--
-- TOC entry 3339 (class 0 OID 0)
-- Dependencies: 212
-- Name: productes_codi_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.productes_codi_seq OWNED BY public.productes.codi;


--
-- TOC entry 3177 (class 2604 OID 17379)
-- Name: productes codi; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.productes ALTER COLUMN codi SET DEFAULT nextval('public.productes_codi_seq'::regclass);


--
-- TOC entry 3327 (class 0 OID 17352)
-- Dependencies: 209
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.clients (dni, nom, correo, telefon, adreca) FROM stdin;
42955901C	Pepe	pepe@gmail.com	602872520	C/victor catala
59994788R	Jose	jose@gmail.com	602989325	C/cristoval colon
32037030P	Manolo	manolo@gmail.com	602365374	C/Santa Marta
19222620W	Yosua	yosua@gmail.com	603395024	C/Barcelona
45928911V	Maria	maria@gmail.com	625182891	C/Quarter
62685951A	Montserrat	montserrat@gmail.com	647197210	C/Pedro
19345901A	Paqui	paqui@gmail.com	680338887	C/Sardenya
81901225L	Sara	sara@gmail.com	672758493	C/Corsega
86048158Y	Jaime	jaime@gmail.com	672149513	C/Madrid
76837413W	Uxue	uxue@gmail.com	658059854	C/Ciutat de Girona
41627783z	daniel	fdadf@fdafd.com	654667788	fdad
\.


--
-- TOC entry 3329 (class 0 OID 17367)
-- Dependencies: 211
-- Data for Name: factures_client; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.factures_client (numfactura, "dniClient", data) FROM stdin;
2553	41627783z	2022-05-27
\.


--
-- TOC entry 3332 (class 0 OID 17441)
-- Dependencies: 214
-- Data for Name: lineafactura; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.lineafactura (numfactura, numlinea, codi_producte, quantitat_producte, preu_producte, total_producte, iva_producte) FROM stdin;
2553	0	6973	2	1.11	2.22	21
\.


--
-- TOC entry 3331 (class 0 OID 17376)
-- Dependencies: 213
-- Data for Name: productes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.productes (codi, nom, stock, preu, iva) FROM stdin;
9533	Llet Semidesnatada de Llet Nostre	57	1.05	21
3353	Ous Granel	12	2.12	21
7283	Pantene Reparador Anti Caspa	65	2.43	21
5854	Mascarilla Pantene Para el Acne	22	4.34	21
9937	Pizza 4 Quesos Casa Tarradelles	48	2.21	21
9636	Cafe Latte KaiKu	5	1.2	21
2660	Cafe Machiato Starbucks	42	1.4	21
5852	Ensalada Cesar Florete	14	3.38	21
6640	Pack Cicles Orbit 4x10 Unitats	69	0.73	21
6973	Patates Frejides Spar	24	1.11	21
\.


--
-- TOC entry 3340 (class 0 OID 0)
-- Dependencies: 210
-- Name: factures_client_numFactura_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."factures_client_numFactura_seq"', 1, false);


--
-- TOC entry 3341 (class 0 OID 0)
-- Dependencies: 212
-- Name: productes_codi_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.productes_codi_seq', 1, false);


--
-- TOC entry 3179 (class 2606 OID 17356)
-- Name: clients Clients_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT "Clients_pkey" PRIMARY KEY (dni);


--
-- TOC entry 3181 (class 2606 OID 17374)
-- Name: factures_client factures_client_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factures_client
    ADD CONSTRAINT factures_client_pkey PRIMARY KEY (numfactura);


--
-- TOC entry 3185 (class 2606 OID 17457)
-- Name: lineafactura lineafactura_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lineafactura
    ADD CONSTRAINT lineafactura_pkey PRIMARY KEY (numfactura, numlinea);


--
-- TOC entry 3183 (class 2606 OID 17540)
-- Name: productes productes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.productes
    ADD CONSTRAINT productes_pkey PRIMARY KEY (codi);


--
-- TOC entry 3186 (class 2606 OID 17387)
-- Name: factures_client fk_client; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factures_client
    ADD CONSTRAINT fk_client FOREIGN KEY ("dniClient") REFERENCES public.clients(dni) NOT VALID;


--
-- TOC entry 3187 (class 2606 OID 17446)
-- Name: lineafactura fk_numfactura; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lineafactura
    ADD CONSTRAINT fk_numfactura FOREIGN KEY (numfactura) REFERENCES public.factures_client(numfactura);


-- Completed on 2022-05-27 15:42:23

--
-- PostgreSQL database dump complete
--

