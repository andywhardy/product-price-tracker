# Product Price Tracker

Author: https://github.com/andywhardy

## Description

Product Price Tracker simulates a price decrease for products and allows a client to register an alert notification 
when the price drops below a threshold. 

## Getting Started

### Pre-requisites

In order to modify, commit, build and run the service you will need the following on your machine:

* Java 17
* Git
* Maven 3.9.7

### Installing

Get started by first cloning the project from git. Once on your local machine the service can be run either within an IDE or directly with java on the command line.

##### Command line

To run on the command line, from the root of the project directory:

```bash  
mvn clean package  
mvn spring-boot:run 
```

### Unit testing

To run unit tests:

```bash  
mvn test   
```

### API Testing

To set an alert notification:

#### Request

```bash
curl -v -X 'POST' \
  'http://localhost:8080/product-price-tracker/track-product' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{"productUrl": "http://product-site/abc", "thresholdPrice": 960, "notificationFrequency": "0 */5 * ? * *"}'
```
#### Response

```bash
> HTTP/1.1 200
```


## API Endpoints and OpenApi Spec

The service uses the springdoc-openapi-ui dependency to provide OpenApi/Swagger documentations. This can be viewed by running the service and visiting http://localhost:8080/product-price-tracker/swagger-ui/index.html