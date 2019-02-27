#!libraries/bin/python
from flask import Flask
from kafka import KafkaConsumer
from kafkatrade.consumer_arbitrage_trade import get_arbitrage_trade
import json
import logging

app = Flask(__name__)
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

@app.route('/')
def index():
    return get_arbitrage_trade()

if __name__ == '__main__':
    app.run(debug=True)