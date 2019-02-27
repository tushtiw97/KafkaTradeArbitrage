# Kafka Trade Application

Have implemented the following functionalities:
1. Created a producer to fetch data from the NSE and BSE python libraries. We can change this to Quandl in the future based on Java producer.
2. Consumer consumes the values and performs logic related to finding the arbitrage. This is not as easy as it seems since there are 5 buy and 5 sell prices listed for a stock on the NSE and BSE. Hence there are a few conditions that need to be satisfied for a trade to be considered arbitrage. I have solved this using the [python-constraint](https://github.com/python-constraint/python-constraint) library 

# Setup

1. [Setup](https://github.com/python-constraint/python-constraint) kafka on the machine

2. Install pip requirements `pip install -r requirements.txt`

3. Run zookeeper and kafka server

4. Create topic SEC_MKT_DATA

5. Run consumer_sec_mkt_data.py first then producer_sec_mkt_data.py
