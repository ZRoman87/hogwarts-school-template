/* --- STUDENT --- */

ALTER TABLE student
    ADD CONSTRAINT age_constraint CHECK ( age >= 16 );

ALTER TABLE student
    ADD CONSTRAINT name_unique_constraint UNIQUE (name);

ALTER TABLE student
    ALTER COLUMN name SET NOT NULL;

ALTER TABLE student
    ALTER COLUMN age SET DEFAULT 20;

/* --- FACULTY --- */

ALTER TABLE faculty
    ADD CONSTRAINT name_color_unique_constraint UNIQUE (name, color);

