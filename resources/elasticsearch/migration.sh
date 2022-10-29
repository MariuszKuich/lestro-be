curl -X PUT http://localhost:9200/_index_template/product-template -H "Content-Type: application/json" -d @templates/product-template.json
curl -X POST http://localhost:9200/product-index/_doc -H "Content-Type: application/json" -d '
{
  "code": "STR-1",
  "name": "Stroik słonecznikowy",
  "description": "Stroik złożony z pięciu słoneczników umieszczonych w blaszanym pojemniku. Kompozycja udekorowana jest sztucznymi kolbami kukurydzy oraz dynami.",
  "img": "",
  "price": 59.99,
  "height": 45,
  "width": 20,
  "length": 20,
  "plants": ["słonecznik"]
}'