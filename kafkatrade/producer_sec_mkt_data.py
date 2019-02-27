from kafka import KafkaProducer
from pprint import pprint
from stockexchange import *
import json
import logging
from time import sleep

TOPIC = 'SEC_MKT_DATA'
TIME_TO_SLEEP  = 20
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

def produce_stock_data():
    """
    Produces the stock data as per API or (currently) mock data
    Publishes to topic SEC_MKT_DATA
    Sleeps for 20 seconds
    """
    producer = KafkaProducer(bootstrap_servers='localhost:9092',value_serializer=lambda m: json.dumps(m).encode('ascii'))
    while(1):
        logger.info("Fetching Stock Data")
        trade = get_mock_data()
        logger.info("Publishing Stock Data")
        producer.send(TOPIC, trade)
        sleep(TIME_TO_SLEEP)
    logger.info("Closing Producer")
    producer.close()

if __name__ == "__main__": 
    produce_stock_data()
    logger.info("Quitting Program")