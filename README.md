# UrlShortner

## Levantar DB desarrollo local

Levanta el contenedor y el volúmen de Docker.

```
docker run --name UrlShortnerPgDev -e POSTGRES_PASSWORD=1234 -e POSTGRES_USER=admin -p 5433:5432 -v pgdata:/var/lib/postgresql/data -d postgres
```

