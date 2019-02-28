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
