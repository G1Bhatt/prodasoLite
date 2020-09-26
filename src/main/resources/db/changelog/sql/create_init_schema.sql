CREATE TABLE public.machine_details (
    id Long NOT NULL,
    name character varying(255),
    created_at bigint NOT NULL,
    status character varying(255),
    updated_at bigint NOT NULL,
    serial_no bigint NOT NULL
);
