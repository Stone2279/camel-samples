# Project description
Purpose of this project...

# Prerequisites
Running postgres db. For example with docker:

`docker run --rm --name postgres -e POSTGRES_PASSWORD=admin -d -p 5432:5432 -v postgres:/var/lib/postgresql/data postgres`

Example table:

```
CREATE TABLE COMPANY(
   ID SERIAL PRIMARY KEY     NOT NULL,
   NAME           TEXT    NOT NULL,
   AGE            INT     NOT NULL,
   ADDRESS        TEXT,
   SALARY         real,
   status         INT default 10
);
```

Sample data:

```
INSERT INTO public.company (id, "name", age, address, salary) VALUES (1, 'hans', 23, 'home', 1111);

```

# Contained routings
Description of contained routings:

    `JpaSelectBuilder`: Uses Camel JPA component to work with DB
    `JpaWithEjbBuilder`: Uses EJB from camel route to work with DB
    `SqlDeleteBuilder`: Uses sql camel component to delete data from DB
    `SqlInsertBuilder`: Uses sql camel component to insert data to DB
    `SqlSelectBuilder`: Uses sql camel component to select data from DB
    `SqlUpdateBuilder`: Uses sql camel component to update data in DB

# Deployed on
`local` - for testing purpose