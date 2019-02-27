from kafka import KafkaConsumer
from service import *
import json
import logging

logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

def consume_stock_data():
    """
    Consumes the stock data
    Consumes from SEC_MKT_DATA
    Sends the received json to the service to calculate arbitrage
    """
    consumer = KafkaConsumer('SEC_MKT_DATA',bootstrap_servers=['localhost:9092'],group_id=None,value_deserializer=lambda m: json.loads(m.decode('ascii')))
    for message in consumer:
        logger.info("Waiting To Consume")
        solution_json = solve_arbitrage_problem(message.value)
        print(solution_json)

if __name__ == "__main__":
    logger.info("Running SEC_MKT_DATA Consumer")
    consume_stock_data()