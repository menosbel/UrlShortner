# UrlShortner

## Levantar DB desarrollo local

Levanta el contenedor y el volúmen de Docker.

```
docker volume create url-shortner-test-pg-data
docker run --name url-shortner-pg-test -e POSTGRES_PASSWORD=1234 -e POSTGRES_DB=url-shortner-test -p 5533:5432 -v url-shortner-test-pg-data:/var/lib/postgresql/data -d postgres:16
```

Levanta DB prod

```
docker volume create url-shortner-pg-data
docker run --name url-shortner-pg -e POSTGRES_PASSWORD=1234 -e POSTGRES_DB=url-shortner -p 5532:5432 -v url-shortner-pg-data:/var/lib/postgresql/data -d postgres:16
```
