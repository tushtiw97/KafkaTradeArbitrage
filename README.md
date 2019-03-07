# Kafka Trade Application

A Kafka application to calculate the arbitrage given buy and sell prices from two stock exchanges.

# Tested Environments
* python 3.7.2
* kafka 2.11 - 2.10

# Implementation Details

Currently the following functionalities have been implemented:
1. Created a Kafka producer to fetch data from the NSE and BSE stock exchanges using [bsedata](https://github.com/sdabhi23/bsedata)
and [nsetools](https://nsetools.readthedocs.io/en/latest/) python libraries. 
2. A Kafka Consumer consumes the values and performs logic related to finding the arbitrage. There are 5 buy and 5 sell prices listed for a stock on the NSE and BSE hence there are a few conditions that need to be satisfied for a trade to be considered arbitrage. We can use the [python-constraint](https://github.com/python-constraint/python-constraint) library to solve CSP's (Constraint Satisfaction Problems).

# Setup

1. [Setup](https://github.com/python-constraint/python-constraint) kafka and zookeeper on the machine

2. Install pip requirements `pip install -r requirements.txt`

3. Run zookeeper and kafka server

4. Create topic SEC_MKT_DATA

5. Run consumer_sec_mkt_data.py first then producer_sec_mkt_data.py

---------------------------------------------------------------------------

For Java examples

Pre-requisites: 
1) The producer and consumer run bi-directional communication despite the unidirectional communication restriction placed by Kafka by default.

2) The topics used for testing purposes are "test" and "test1" respectively.

3) The producer and consumer codes have functionality to act as consumer and producer, respectively, as well depending on certain conditions. These conditions, for the time being, include the producer sending "raise arbitrage" message on topic "test" and the consumer sending "client wants to raise an arbitrage" on topic "test1" upon receipt of the aforementioned "raise arbitrage" message from the producer. 

4) The messages mentioned above can be replaced with the corresponding business logic on both the sides.

Setup:
1) Run the spring boot application "TestApp".

2) Open localhost:8080/runProducer. The method to start the producer is executed, and the producer starts. The same is visible on console of the IDE.

3) Run the Java application "KafkaConsumer" and enter "test" as the first input, and "0" as the input for the group ID

4) Switch back to TestApp. You should be seeing an "Enter message : " on the console of the IDE by now.

That's it. Your producer and consumer have been set up. You can test by sending messages from the producer, and check it's receipt using the consumer.

PS: try sending "raise arbitrage" message from the producer, and check it's immediate response on the producer again. Should be "client wants to raise an arbitrage". If not, press enter on the console of the producer app, and you should be able to see the receipt of the response.