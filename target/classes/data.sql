-- Crear secuencias
CREATE SEQUENCE batch_job_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE batch_job_execution_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE batch_step_execution_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE batch_job_execution_params_seq START WITH 1 INCREMENT BY 1;

-- Crear tabla batch_job_instance
CREATE TABLE batch_job_instance (
    job_instance_id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('batch_job_seq'),
    version BIGINT,
    job_name VARCHAR(100) NOT NULL,
    job_key VARCHAR(32) NOT NULL,
    CONSTRAINT job_instance_unique UNIQUE (job_name, job_key)
);

-- Crear tabla batch_job_execution
CREATE TABLE batch_job_execution (
    job_execution_id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('batch_job_execution_seq'),
    version BIGINT,
    job_instance_id BIGINT NOT NULL,
    create_time TIMESTAMP NOT NULL,
    start_time TIMESTAMP DEFAULT NULL,
    end_time TIMESTAMP DEFAULT NULL,
    status VARCHAR(10),
    exit_code VARCHAR(2500),
    exit_message VARCHAR(2500),
    last_updated TIMESTAMP,
    job_configuration_location VARCHAR(2500) NULL,
    CONSTRAINT fk_job_instance FOREIGN KEY (job_instance_id)
    REFERENCES batch_job_instance(job_instance_id)
);

-- Crear tabla batch_job_execution_params
CREATE TABLE batch_job_execution_params (
    job_execution_id BIGINT NOT NULL,
    parameter_name VARCHAR(100) NOT NULL,
    parameter_type VARCHAR(100) NOT NULL,
    parameter_value VARCHAR(2500),
    identifying CHAR(1) NOT NULL,
    PRIMARY KEY (job_execution_id, parameter_name),
    CONSTRAINT fk_job_execution FOREIGN KEY (job_execution_id)
    REFERENCES batch_job_execution(job_execution_id)
);

-- Crear tabla batch_step_execution
CREATE TABLE batch_step_execution (
    step_execution_id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('batch_step_execution_seq'),
    version BIGINT NOT NULL,
    step_name VARCHAR(100) NOT NULL,
    job_execution_id BIGINT NOT NULL,
    start_time TIMESTAMP DEFAULT now(),
    end_time TIMESTAMP DEFAULT NULL,
    status VARCHAR(10),
    commit_count BIGINT,
    read_count BIGINT,
    filter_count BIGINT,
    write_count BIGINT,
    read_skip_count BIGINT,
    write_skip_count BIGINT,
    process_skip_count BIGINT,
    rollback_count BIGINT,
    exit_code VARCHAR(2500) NOT NULL,
    exit_message VARCHAR(2500),
    last_updated TIMESTAMP,
    CREATE_TIME TIMESTAMP DEFAULT now(),
    CONSTRAINT fk_job_exec FOREIGN KEY (job_execution_id)
    REFERENCES batch_job_execution(job_execution_id)
);

-- Crear tabla batch_step_execution_context
CREATE TABLE batch_step_execution_context (
    step_execution_id BIGINT NOT NULL PRIMARY KEY,
    short_context VARCHAR(2500) NOT NULL,
    serialized_context TEXT,
    CONSTRAINT fk_step_exec FOREIGN KEY (step_execution_id)
    REFERENCES batch_step_execution(step_execution_id)
);

-- Crear tabla batch_job_execution_context
CREATE TABLE batch_job_execution_context (
    job_execution_id BIGINT NOT NULL PRIMARY KEY,
    short_context VARCHAR(2500) NOT NULL,
    serialized_context TEXT,
    CONSTRAINT fk_job_exec_ctx FOREIGN KEY (job_execution_id)
    REFERENCES batch_job_execution(job_execution_id)
);
