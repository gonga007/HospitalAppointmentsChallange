-- Table: public.appointment

-- DROP TABLE IF EXISTS public.appointment;

CREATE TABLE IF NOT EXISTS public.appointment
(
    id SERIAL PRIMARY KEY,
    doctor text COLLATE pg_catalog."default" NOT NULL,
    speciality text COLLATE pg_catalog."default" NOT NULL,
    patient text COLLATE pg_catalog."default" NOT NULL,
    pathology text COLLATE pg_catalog."default",
    symptoms text[] COLLATE pg_catalog."default"
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.appointment
    OWNER to postgres;