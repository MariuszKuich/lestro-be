### Lestro BE
Backend part of the Lestro project

Images source: https://pixabay.com/

Components required to run the application locally:
- Elasticsearch instance running on localhost:9200,
- set '--add-opens java.base/java.lang=ALL-UNNAMED' VM option for product-service, delivery-service, payment-service, order-service. customer-service projects
- PostgreSQL database running on localhost:5432

For development purposes disable CORS in your browser (Google Chrome example: run './chrome.exe --disable-site-isolation-trials --disable-web-security --user-data-dir="C:\temp"' in CMD)