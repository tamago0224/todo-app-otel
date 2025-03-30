CREATE TABLE IF NOT EXISTS todos (
    id TEXT PRIMARY KEY,
    title TEXT NOT NULL,
    description TEXT,
    expire TIMESTAMP,
    completed BOOLEAN NOT NULL DEFAULT FALSE
);
