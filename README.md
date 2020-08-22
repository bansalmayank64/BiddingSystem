#The Bidding System

This bidding system enables the users to place the bid for running auctions.

An Auction have following attributes:
1. Item Code - for which auction is running
2. Minimum Base Price - This is starting bidding amount, no user can place the bid lesser than this defined price
3. Step Rate - minimum amount difference b/w two consecutive bids. For example, if a user placed the bid of 1000 /- INR then the next acceptable bid will be a minimum of 1000 + Step Rate. If the step rate is 250 /- INR then the next acceptable bid should be >= 1250.
4. Status -
	a. RUNNING: Only running auctions are the candidates of placing the bid
	b. OVER: Once auction is over then no user can place the bid on the corresponding item
5. User Bids - All user bids should be recorded whether it was accepted or rejected.

## Technology stack used:
1. JDK 1.8+
2. Spring boot
3. Maven
4. JPA
5. H2 Db (Used temporary just for portability, Can be replaced with real time db)

## Future Scope
1. Realtime broadcasting of bid placed on the auction so that all users aware of latest highest bid on the auction -> Can be done using Websockets.
2. Authentication improvement and User roles based authorization
3. User Onboarding apis
4. Add/Delete/Modify Auctions/Items apis

## Usage 
Use Basic Authentication in every api given below:
Username: admin
Password: admin

Note: This feature can be improved and we can use JWT token authentication instead of basic authentication.

### Fetch Auctions Api
Test the `auction` endpoint.

```sh
curl http://localhost:8080/auction?startFrom=0&limit=10&status=RUNNING
```
Here pagination parameters i.e. startFrom and limit are optional. Above api without pagination can be tested using -
```sh
curl http://localhost:8080/auction?status=RUNNING
```

If we want to fetch all the auctions without filtering based on status then -
```sh
curl http://localhost:8080/auction
```

Sample Response:
```sh
{
    "resultsReturned": 3,
    "resultsAvailable": 3,
    "data": [
        {
            "auctionId": "AUC22082020",
            "itemCode": "TT123ki98011",
            "highestBidAmount": 2500.0,
            "stepRate": 100.0
        },
        {
            "auctionId": "AUC20072020",
            "itemCode": "VKh128901111",
            "highestBidAmount": 3500.0,
            "stepRate": 200.0
        },
        {
            "auctionId": "AUC23452671",
            "itemCode": "HTK718adg1123",
            "highestBidAmount": 6500.0,
            "stepRate": 500.0
        }
    ]
}
```

### Place Bid 

Test the `placebid` endpoint.

```sh
curl -d '{"bidAmount":3800}' -H "Content-Type: application/json" http://localhost:8080/auction/{itemCode}/bid
```
You can receive the following response-
```sh
HTTP Status 201 - If the bid is accepted
HTTP Status 404 - If auction not found
HTTP Status 406 - If the bid is rejected
```