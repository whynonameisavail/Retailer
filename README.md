# Retailer Reward Project

## Create Purchase

Type:  POST  

URL: ` /purchases`

Example:

```
{"customerID": 1, "payAmount": 120, "payTime":"2023-01-30"} 

http://localhost:8080/purchases
```

When no payTime in the input json,  current date time is used as the default value.

```
{"customerID": 1, "payAmount": 120} 

http://localhost:8080/purchases
```


## Get Reward points information for given customer

Type:  GET

URL:   `/reward/{customerID}?months={months}`

The default value of `months` parameter is 3.

### Example 1

Request :

```
http://localhost:8080/reward/1
```
Response:

```
{"customerID":1,"months":3,"total":400.0,"monthlyRewards":[{"month":"2022-12","rewardPoint":140.0},{"month":"2023-01","rewardPoint":220.0},{"month":"2023-02","rewardPoint":40.0}]}
```

### Example 2

Request

```
http://localhost:8080/reward/1?months=4
```

Response

```
{"customerID":1,"months":4,"total":640.0,"monthlyRewards":[{"month":"2022-11","rewardPoint":240.0},{"month":"2022-12","rewardPoint":140.0},{"month":"2023-01","rewardPoint":220.0},{"month":"2023-02","rewardPoint":40.0}]}
```

## Get Reward points information for all customers

Type:  GET

URL:   `/reward`

### Example

Request:

```
http://localhost:8080/reward
```

Response:

```
[{"customerID":1,"months":3,"total":400.0,"monthlyRewards":[{"month":"2022-12","rewardPoint":140.0},{"month":"2023-01","rewardPoint":220.0},{"month":"2023-02","rewardPoint":40.0}]},{"customerID":2,"months":3,"total":330.0,"monthlyRewards":[{"month":"2022-12","rewardPoint":30.0},{"month":"2023-01","rewardPoint":80.0},{"month":"2023-02","rewardPoint":220.0}]}]
```

## Find all purchases for given customer ID

Type:  GET

URL:   `/purchases/search/findByCustomerID?id={id}`

### Example

```
http://localhost:8080/purchases/search/findByCustomerID?id=1
```

## Create data set for demo

In class `com.example.retailer.RetailerApplication`,  the method`createDemoDataSet` creates 16 purchases during recent 4 months for 2 customers.

## Unit Test

In class `com.example.retailer.RetailerApplicationTests`,  the API endpoints mentioned above are all tested and the static method `computeReward` is also tested.









