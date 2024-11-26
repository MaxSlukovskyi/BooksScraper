<h1 align="center" style="font-weight: bold;">BooksScraper</h1>

<p align="center">
    <b>A functional web scraper application for the website <a href="https://books.toscrape.com/">Books to Scrape</a>.</b>
</p>

<h2 id="technologies">💻 Technologies</h2>

- Java
- Spring Boot
- Jsoup
- PostgreSQL

<h2 id="started">🚀 Getting started</h2>

<h3>Cloning</h3>

How to clone this project

```bash
git clone https://github.com/MaxSlukovskyi/BooksScraper.git
```

<h3>Config application.properties</h2>

Use the `application.properties.example` as reference to create configuration file `application.properties` with Database Credentials

```yaml
spring.application.name=BooksScraper
server.port=8080
spring.datasource.url={your_datasource_url}
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
scraper.books.url=https://books.toscrape.com/
```

<h2 id="routes">📍 API Endpoints</h2>

| route               | description                                          
|----------------------|-----------------------------------------------------
| <kbd>GET /api/books</kbd>     | return all books from database
| <kbd>POST /api/books/scraper</kbd>     | save all books from first page
| <kbd>POST /api/books/scraper/all</kbd>     | save all books from all pages
| <kbd>DELETE /api/books</kbd>     | delete all books from database

<h3 id="get-auth-detail">GET /api/books</h3>

**RESPONSE**
```json
[
    {
        "title": "A Light in the Attic",
        "price": "£51.77",
        "availability": "In stock",
        "rating": 3
    },
    {
        "title": "Tipping the Velvet",
        "price": "£53.74",
        "availability": "In stock",
        "rating": 1
    }
]
```
