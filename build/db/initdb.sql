--
-- TABLE : backup_task
--
-- Backup task table.
--
DROP TABLE IF EXISTS backup_task;
CREATE TABLE backup_task (
    name VARCHAR PRIMARY KEY,
    source_dir VARCHAR,
    dest_dir VARCHAR,
    schedule VARCHAR,
    encrypt_algorithm VARCHAR,
    encrypt_password VARCHAR,
    status VARCHAR,
    next_backup_time BIGINT,
    description VARCHAR
);