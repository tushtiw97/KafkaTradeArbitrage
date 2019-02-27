from nsetools import Nse
from bsedata.bse import BSE
from pprint import pprint

TCS = '532540'

def get_nse_stock_quotes():
    nse_driver = Nse()
    nse_quote = nse_driver.get_quote('TCS')
    return nse_quote

def get_bse_stock_quotes():
    bse_stock = BSE()
    bse_quote = bse_stock.getQuote(TCS)
    return bse_quote
    
# TODO quotes as user input
def get_stock_quotes():
    nse_quotes = get_nse_stock_quotes()
    bse_quotes = get_bse_stock_quotes()

    # BSE information
    security_id = bse_quotes['securityID']
    bse_buy = bse_quotes['buy']
    bse_sell = bse_quotes['sell']

    bse_buy_price_temp = [bse_buy['1']['price'], bse_buy['2']['price'], bse_buy['3']['price'], bse_buy['4']['price'], bse_buy['5']['price']]
    bse_buy_qty_temp = [bse_buy['1']['quantity'], bse_buy['2']['quantity'], bse_buy['3']['quantity'], bse_buy['4']['quantity'], bse_buy['5']['quantity']]
    bse_sell_price_temp = [bse_sell['1']['price'], bse_sell['2']['price'], bse_sell['3']['price'], bse_sell['4']['price'], bse_sell['5']['price']]
    bse_sell_qty_temp = [bse_sell['1']['quantity'], bse_sell['2']['quantity'], bse_sell['3']['quantity'], bse_sell['4']['quantity'], bse_sell['5']['quantity']]

    bse_buy_price = [float(x) for x in bse_buy_price_temp]
    bse_buy_qty = [float(x) for x in bse_buy_qty_temp]
    bse_sell_price = [float(x) for x in bse_sell_price_temp]
    bse_sell_qty = [float(x) for x in bse_sell_qty_temp]

    # NSE information
    company_name = nse_quotes['companyName']

    # Buy information 
    nse_buy_price = [nse_quotes['buyPrice1'], nse_quotes['buyPrice2'], nse_quotes['buyPrice3'], nse_quotes['buyPrice4'], nse_quotes['buyPrice5']]
    nse_buy_qty = [nse_quotes['buyQuantity1'], nse_quotes['buyQuantity2'], nse_quotes['buyQuantity3'], nse_quotes['buyQuantity4'],  nse_quotes['buyQuantity5']  ]

    # Sell information
    nse_sell_price = [ nse_quotes['sellPrice1'],nse_quotes['sellPrice2'], nse_quotes['sellPrice3'], nse_quotes['sellPrice4'], nse_quotes['sellPrice5']]
    nse_sell_qty = [nse_quotes['sellQuantity1'], nse_quotes['sellQuantity2'], nse_quotes['sellQuantity3'], nse_quotes['sellQuantity4'], nse_quotes['sellQuantity5'] ]

    # Create trade dictionary
    trade = dict()
    trade['security_id'] = security_id
    trade['company_name'] = company_name
    trade['bse_buy_price'] = bse_buy_price
    trade['bse_buy_qty'] = bse_buy_qty
    trade['bse_sell_price'] = bse_sell_price
    trade['bse_sell_qty'] = bse_sell_qty
    trade['nse_buy_price'] = nse_buy_price
    trade['nse_buy_qty'] = nse_buy_qty
    trade['nse_sell_price'] = nse_sell_price
    trade['nse_sell_qty'] = nse_sell_qty

    print (trade)

    return trade

def get_mock_data():
    security_id = 12345
    company_name = "Infosys"

    #BSE buy and sell
    bse_buy_price = [100,105,102,103,106]
    bse_buy_qty = [50,100,25,65,80]
    bse_sell_price = [102,103,104,101,100]
    bse_sell_qty = [45,80,90,60,500]

    # NSE buy and sell
    nse_buy_price = [103,99,100,100,101]
    nse_buy_qty = [80,90,45,60,90]
    nse_sell_price = [106,98,100,103,106]
    nse_sell_qty = [100,50,105,200,250]

    trade = dict()
    trade['security_id'] = security_id
    trade['company_name'] = company_name

    trade['bse_buy_price'] = bse_buy_price
    trade['bse_buy_qty'] = bse_buy_qty
    trade['bse_sell_price'] = bse_sell_price
    trade['bse_sell_qty'] = bse_sell_qty

    trade['nse_buy_price'] = nse_buy_price
    trade['nse_buy_qty'] = nse_buy_qty
    trade['nse_sell_price'] = nse_sell_price
    trade['nse_sell_qty'] = nse_sell_qty

    return trade


 