
CREATE TABLE taskslist
(
  task character varying(1000) NOT NULL
)
WITH (
  OIDS=FALSE
);
ALTER TABLE taskslist
  OWNER TO postgres;
  
  insert into taskslist values('kara'),('dziara');