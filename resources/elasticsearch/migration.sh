# ą \u0105
# ę \u0119
# ć \u0107
# Ć \u0106
# ł \u0142
# Ł \u0141
# ń \u0144
# ś \u015B
# Ś \u015A
# ź \u017A
# Ź \u0179
# ż \u017C
# Ż \u017B
# ó \u00F3

curl -X PUT http://localhost:9200/_index_template/product-template -H "Content-Type: application/json" -d @templates/product-template.json
curl -X POST http://localhost:9200/product-index/_doc -H "Content-Type: application/json; charset=UTF-8" -d '
{
  "no": 1,
  "code": "STR-1",
  "name": "Stroik S\u0142onecznikowy [OZDOBY]",
  "description": "Stroik z\u0142o\u017Cony z pi\u0119ciu s\u0142onecznik\u00F3w umieszczonych w blaszanym pojemniku. Kompozycja udekorowana jest sztucznymi kolbami kukurydzy oraz dynami.",
  "imgs": [
    "https://i.ibb.co/r3QF0wR/1.jpg",
    "https://i.ibb.co/x7NQZVm/2.jpg",
    "https://i.ibb.co/Xx424Jj/3.jpg"
  ],
  "price": 59.99,
  "height": 45,
  "width": 20,
  "length": 20,
  "plants": [
    "s\u0142onecznik"
  ]
}'
curl -X POST http://localhost:9200/product-index/_doc -H "Content-Type: application/json; charset=UTF-8" -d '
{
  "no": 2,
  "code": "STR-2",
  "name": "R\u00F3\u017Cnokolorowe Pude\u0142ko",
  "description": "Stroik z\u0142o\u017Cony z r\u00F3\u017Cnokolorowych r\u00F3\u017C oraz tulipan\u00F3w umieszczonych w bogato zdobionym pude\u0142ku.",
  "imgs": [
    "https://i.ibb.co/rkRTpwx/1.jpg",
    "https://i.ibb.co/5FSvhy0/2.jpg",
    "https://i.ibb.co/nQ9D5st/3.jpg"
  ],
  "price": 99.99,
  "height": 25,
  "width": 40,
  "length": 20,
  "plants": [
    "r\u00F3\u017Ca",
    "tulipan"
  ]
}'
curl -X POST http://localhost:9200/product-index/_doc -H "Content-Type: application/json; charset=UTF-8" -d '
{
  "no": 3,
  "code": "STR-3",
  "name": "Bogaty Bukiet",
  "description": "Stroik sk\u0142adaj\u0105cy si\u0119 z wielu r\u00F3\u017Cnokolorowych kwiat\u00F3w umieszczonych w wazonie. Idealny na wesele.",
  "imgs": [
	  "https://i.ibb.co/WFFs5nQ/1.jpg",
    "https://i.ibb.co/KwSyqtn/2.jpg",
    "https://i.ibb.co/mChw3H9/3.jpg"
	],
  "price": 69.99,
  "height": 50,
  "width": 30,
  "length": 30,
  "plants": [
    "r\u00F3\u017Ca",
    "cynia skarbiozowa",
    "aksamitka"
  ]
}'
curl -X POST http://localhost:9200/product-index/_doc -H "Content-Type: application/json; charset=UTF-8" -d '
{
  "no": 4,
  "code": "STR-4",
  "name": "Powiew Wiosny",
  "description": "Minimalistyczny stroik z\u0142o\u017Cony z ro\u015Blin polnych umieszczonych w szklanym s\u0142oiku. Zwiastun wiosny.",
  "imgs": [
	  "https://i.ibb.co/Fb1hSDY/1.jpg"
	],
  "price": 15.99,
  "height": 15,
  "width": 7,
  "length": 7,
  "plants": [
    "kwiaty polne"
  ]
}'
curl -X POST http://localhost:9200/product-index/_doc -H "Content-Type: application/json; charset=UTF-8" -d '
{
  "no": 5,
  "code": "STR-5",
  "name": "R\u00F3\u017Cany Upominek",
  "description": "Stroik z\u0142o\u017Cony z barwnych r\u00F3\u017C umieszczonych w bia\u0142ym drewnianym koszu. Idealny upominek dla drugiej po\u0142\u00F3wki.",
  "imgs": [
	  "https://i.ibb.co/BqZQ7wP/1.jpg"
	],
  "price": 45.99,
  "height": 35,
  "width": 50,
  "length": 30,
  "plants": [
    "r\u00F3\u017Ca"
  ]
}'
curl -X POST http://localhost:9200/product-index/_doc -H "Content-Type: application/json; charset=UTF-8" -d '
{
  "no": 6,
  "code": "STR-6",
  "name": "Stroik S\u0142onecznikowy [BEZ OZD\u00D3B]",
  "description": "Stroik z\u0142o\u017Cony z sze\u015Bciu s\u0142onecznik\u00F3w umieszczonych w bia\u0142ym wazonie. Wariant bez ozd\u00F3b.",
  "imgs": [
	  "https://i.ibb.co/TTkLvyr/1.jpg"
	],
  "price": 39.99,
  "height": 45,
  "width": 15,
  "length": 15,
  "plants": [
    "s\u0142onecznik"
  ]
}'
curl -X POST http://localhost:9200/product-index/_doc -H "Content-Type: application/json; charset=UTF-8" -d '
{
  "no": 7,
  "code": "STR-7",
  "name": "Dary Natury",
  "description": "Stroik z\u0142o\u017Cony z r\u00F3\u017Cnokolorowych ro\u015Blin polnych umieszczonych w wiklinowym koszu. Idealny spos\u00F3b na przywitanie wiosny.",
  "imgs": [
	  "https://i.ibb.co/jTZdyKZ/1.jpg"
	],
  "price": 24.99,
  "height": 30,
  "width": 20,
  "length": 20,
  "plants": [
    "kwiaty polne"
  ]
}'
curl -X POST http://localhost:9200/product-index/_doc -H "Content-Type: application/json; charset=UTF-8" -d '
{
  "no": 8,
  "code": "STR-8",
  "name": "Wenecja",
  "description": "Stroik z\u0142o\u017Cony z trzech czerwonych r\u00F3\u017C umieszczonych w bogato zdobionym szklanym wazonie. Idealny prezent na walentynki.",
  "imgs": [
	  "https://i.ibb.co/XVRKn5C/1.jpg"
	],
  "price": 39.99,
  "height": 23,
  "width": 15,
  "length": 15,
  "plants": [
    "r\u00F3\u017Ca"
  ]
}'
curl -X POST http://localhost:9200/product-index/_doc -H "Content-Type: application/json; charset=UTF-8" -d '
{
  "no": 9,
  "code": "STR-9",
  "name": "Liliowe Kolory",
  "description": "Stroik z\u0142o\u017Cony z niebieskich, bia\u0142ych oraz filetowych lilii umieszczonych w minimalistycznym szklanym wazonie.",
  "imgs": [
	  "https://i.ibb.co/QQLbfYb/1.jpg"
	],
  "price": 42.99,
  "height": 50,
  "width": 20,
  "length": 20,
  "plants": [
    "lilia"
  ]
}'
curl -X POST http://localhost:9200/product-index/_doc -H "Content-Type: application/json; charset=UTF-8" -d '
{
  "no": 10,
  "code": "STR-10",
  "name": "Kolory w Wiklinie",
  "description": "Stroik z\u0142o\u017Cony z wielokolorowych r\u00F3\u017C i kwiat\u00F3w polnych umieszczony w wiklinowym koszu o wymiarach 30x30x30 [cm].",
  "imgs": [
	  "https://i.ibb.co/1GWBCyn/1.jpg"
	],
  "price": 99.99,
  "height": 60,
  "width": 30,
  "length": 30,
  "plants": [
    "r\u00F3\u017Ca",
    "kwiaty polne"
  ]
}'
curl -X POST http://localhost:9200/product-index/_doc -H "Content-Type: application/json; charset=UTF-8" -d '
{
  "no": 11,
  "code": "STR-11",
  "name": "Elegancja w R\u00F3\u017Cu",
  "description": "Stroik z\u0142o\u017Cony z bia\u0142o-r\u00F3\u017Cowych hortensji i kwiat\u00F3w polnych umieszczonych w porcelanowym wazonie imituj\u0105cym imbryk.",
  "imgs": [
	  "https://i.ibb.co/Rh7xm7d/1.jpg"
	],
  "price": 109.99,
  "height": 40,
  "width": 20,
  "length": 20,
  "plants": [
    "kwiaty polne",
    "hortensja"
  ]
}'
curl -X POST http://localhost:9200/product-index/_doc -H "Content-Type: application/json; charset=UTF-8" -d '
{
  "no": 12,
  "code": "STR-12",
  "name": "Minimalistyczna Biel",
  "description": "Stroik, w kt\u00F3rego sk\u0142ad wchodz\u0105 stokrotka, rumianek oraz jastrun. Kwiaty umieszczone s\u0105 w niewielkim porcelanowym wazonie. Kompozycja idealna dla mi\u0142o\u015Bnik\u00F3w bieli.",
  "imgs": [
	  "https://i.ibb.co/yg8gMwC/1.jpg"
	],
  "price": 25.99,
  "height": 15,
  "width": 8,
  "length": 8,
  "plants": [
    "stokrotka",
    "rumianek",
    "jastrun w\u0142a\u015Bciwy"
  ]
}'

curl -X PUT http://localhost:9200/_index_template/delivery-template -H "Content-Type: application/json" -d @templates/delivery-template.json
curl -X POST http://localhost:9200/delivery-index/_doc -H "Content-Type: application/json; charset=UTF-8" -d '
{
  "code": "SELF_PICKUP",
  "icon-class": "bi bi-house",
  "name": "Odbi\u00F3r osobisty",
  "price": 0
}'
curl -X POST http://localhost:9200/delivery-index/_doc -H "Content-Type: application/json; charset=UTF-8" -d '
{
  "code": "PDP",
  "icon-class": "bi bi-truck",
  "name": "Kurier PDP",
  "price": 19.99
}'

curl -X PUT http://localhost:9200/_index_template/payment-template -H "Content-Type: application/json" -d @templates/payment-template.json
curl -X POST http://localhost:9200/payment-index/_doc -H "Content-Type: application/json; charset=UTF-8" -d '
{
  "code": "TRAD_TRANSFER",
  "icon-class": "bi bi-bank",
  "name": "Przelew tradycyjny"
}'
curl -X POST http://localhost:9200/payment-index/_doc -H "Content-Type: application/json; charset=UTF-8" -d '
{
  "code": "PAY_Y",
  "icon-class": "bi bi-coin",
  "name": "PayY"
}'

curl -X PUT http://localhost:9200/_index_template/order-template -H "Content-Type: application/json" -d @templates/order-template.json
curl -X PUT http://localhost:9200/_index_template/product-order-template -H "Content-Type: application/json" -d @templates/product-order-template.json

curl -X PUT http://localhost:9200/_index_template/configurator-template -H "Content-Type: application/json" -d @templates/configurator-template.json
curl -X POST http://localhost:9200/configurator-index/_doc -H "Content-Type: application/json; charset=UTF-8" -d '
{
  "type": "PLANT",
  "code": "t1",
  "name": "Zamioculcas",
  "price": 59.99
}'
curl -X POST http://localhost:9200/configurator-index/_doc -H "Content-Type: application/json; charset=UTF-8" -d '
{
  "type": "PLANT",
  "code": "t2",
  "name": "Skrzyd\u0142okwiat Cupido",
  "price": 29.99
}'
curl -X POST http://localhost:9200/configurator-index/_doc -H "Content-Type: application/json; charset=UTF-8" -d '
{
  "type": "PLANT",
  "code": "t3",
  "name": "Saintpaulia",
  "price": 34.99
}'
curl -X POST http://localhost:9200/configurator-index/_doc -H "Content-Type: application/json; charset=UTF-8" -d '
{
  "type": "PLANT",
  "code": "t4",
  "name": "Nefrolepis Exaltata",
  "price": 32.99
}'
curl -X POST http://localhost:9200/configurator-index/_doc -H "Content-Type: application/json; charset=UTF-8" -d '
{
  "type": "DECORATION",
  "code": "",
  "name": "Brak",
  "price": 0
}'
curl -X POST http://localhost:9200/configurator-index/_doc -H "Content-Type: application/json; charset=UTF-8" -d '
{
  "type": "DECORATION",
  "code": "d1",
  "name": "Kamyki z\u0142ote",
  "price": 14.99
}'
curl -X POST http://localhost:9200/configurator-index/_doc -H "Content-Type: application/json; charset=UTF-8" -d '
{
  "type": "DECORATION",
  "code": "d2",
  "name": "Kamyki czerwone",
  "price": 14.99
}'
curl -X POST http://localhost:9200/configurator-index/_doc -H "Content-Type: application/json; charset=UTF-8" -d '
{
  "type": "ORNAMENT",
  "code": "",
  "name": "Brak",
  "price": 0
}'
curl -X POST http://localhost:9200/configurator-index/_doc -H "Content-Type: application/json; charset=UTF-8" -d '
{
  "type": "ORNAMENT",
  "code": "o1",
  "name": "Gwiazdki",
  "price": 7.99
}'
curl -X POST http://localhost:9200/configurator-index/_doc -H "Content-Type: application/json; charset=UTF-8" -d '
{
  "type": "ORNAMENT",
  "code": "o2",
  "name": "Wi\u015Bnie (sztuczne)",
  "price": 4.99
}'
curl -X POST http://localhost:9200/configurator-index/_doc -H "Content-Type: application/json; charset=UTF-8" -d '
{
  "type": "ORNAMENT",
  "code": "o3",
  "name": "\u0141a\u0144cuch \u015Bwi\u0105teczny",
  "price": 6.99
}'
curl -X POST http://localhost:9200/configurator-index/_doc -H "Content-Type: application/json; charset=UTF-8" -d '
{
  "type": "POT",
  "code": "p1",
  "name": "Doniczka safari",
  "price": 19.99
}'
curl -X POST http://localhost:9200/configurator-index/_doc -H "Content-Type: application/json; charset=UTF-8" -d '
{
  "type": "POT",
  "code": "p2",
  "name": "Doniczka pod\u0142u\u017Cna",
  "price": 24.99
}'
curl -X POST http://localhost:9200/configurator-index/_doc -H "Content-Type: application/json; charset=UTF-8" -d '
{
  "type": "POT",
  "code": "p3",
  "name": "Doniczka czarno-z\u0142ota",
  "price": 14.99
}'