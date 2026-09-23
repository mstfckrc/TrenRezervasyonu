# Tren Rezervasyonu API

Java 21, Spring Boot ve Maven ile geliştirilmiş tren rezervasyonu kontrol API'si.

## Çalıştırma

Windows: `.\mvnw.cmd spring-boot:run`

## İstek

`POST http://localhost:8080/api/rezervasyon`  
`Content-Type: application/json`

```json
{
  "Tren": {
    "Ad": "Başkent Ekspres",
    "Vagonlar": [
      {"Ad": "Vagon 1", "Kapasite": 100, "DoluKoltukAdet": 68},
      {"Ad": "Vagon 2", "Kapasite": 90, "DoluKoltukAdet": 50}
    ]
  },
  "RezervasyonYapilacakKisiSayisi": 3,
  "KisilerFarkliVagonlaraYerlestirilebilir": true
}
```

API, vagonların %70 doluluk sınırını aşmadan rezervasyon yapılıp yapılamayacağını ve yolcuların yerleşimini döndürür. Yerleşim mümkün değilse `RezervasyonYapilabilir` değeri `false`, `YerlesimAyrinti` listesi boş olur.

## Testler

Windows: `.\mvnw.cmd test`