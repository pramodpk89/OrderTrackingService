# OrderTackingService


##Sample Request:

````
curl --location 'http://localhost:8080/orders/tracking' \
--header 'Content-Type: application/json' \
--data '{
    "data": {
        "attributes": {
            "shipFrom": {
                "address": {
                    "address1": "address1",
                    "address2": "address2",
                    "address3": "",
                    "city": "city",
                    "state": "STATE",
                    "provinceCode": "PC",
                    "country": "CA",
                    "zipcode": "12345"
                },
                "accountNumber": "carrier1",
                "locationId": "DC1"
            },
            "shipTo": {
                "address": {
                    "address1": "address1",
                    "address2": "address2",
                    "address3": "",
                    "city": "city",
                    "state": "STATE",
                    "provinceCode": "PC",
                    "country": "CA",
                    "zipcode": "43211"
                }
            },
            "returnTo": {
                "address": {
                    "address1": "address1",
                    "address2": "address2",
                    "address3": "",
                    "city": "city",
                    "state": "STATE",
                    "provinceCode": "PC",
                    "country": "CA",
                    "zipcode": "12345"
                }
            },
            "packageDetail": [
                {
                    "packageLineItems": [
                        {
                            "itemSequence": 1,
                            "itemCode": "123456",
                            "quantity": 1,
                            "uom": "each",
                            "itemDescription": "1st quality ",
                            "color": "Black",
                            "size": "Large",
                            "currency": "GBP",
                            "unitPrice": 100,
                            "retailPrice": 100,
                            "listPrice": 100
                        },
                        {
                            "itemSequence": 2,
                            "itemCode": "1234567",
                            "quantity": 1,
                            "uom": "EACH",
                            "itemDescription": "1st quality",
                            "color": "Blue",
                            "size": "Large",
                            "currency": "GBP",
                            "unitPrice": 100,
                            "retailPrice": 100,
                            "listPrice": 100
                        }
                    ]
                }
            ]
        }
    }
}'
````


## Sample Response:

````
{
    "data": {
        "attributes": {
            "shipFrom": null,
            "shipTo": null,
            "returnTo": null,
            "packageDetail": null,
            "label": {
                "trackingNumber": "c586408e61",
                "trackingUrl": "https://dummy-carrier.com:8081/tracking/c586408e61"
            }
        }
    }
}
````


## To run this via docker:

1.Navigate to Project directory

2. run ``docker build -t ordertracker:v1 .`` to build image

3.run `` docker run -p 8080:8080 ordertracker:v1`` to spin up a new container.
