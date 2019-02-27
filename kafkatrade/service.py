from constraint import *
import logging

logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

def solve_arbitrage_problem(trade):
    """
    Accepts 5 buy/sell prices of two stock exchanges NSE and BSE and calculates the arbitrge based on the conditions:
    1. Quantity of sell in one market > Quantity of buy in another market
    2. Sell price in one market > Buy price in another market 

    Parameters:
    trade (dict): Is the details of the stock as produced by producer

    Returns:
    solution_json: Is the identified arbitrage opportunities
    """

    nse_buy_price = trade['nse_buy_price']
    nse_buy_qty = trade['nse_buy_qty']
    nse_sell_price = trade['nse_sell_price']
    nse_sell_qty = trade['nse_sell_qty']
    bse_buy_price = trade['bse_buy_price']
    bse_buy_qty = trade['bse_buy_qty']
    bse_sell_price = trade['bse_sell_price']
    bse_sell_qty = trade['bse_sell_qty']

    # Buying NSE and selling BSE
    problem = Problem()
    problem.addVariable('nse_sell_price', nse_sell_price)
    problem.addVariable('nse_sell_qty', nse_sell_qty)
    problem.addVariable('bse_buy_price', bse_buy_price)
    problem.addVariable('bse_buy_qty', bse_buy_qty)

    problem.addConstraint(lambda a, b: a >= b, ('nse_sell_qty', 'bse_buy_qty'))
    problem.addConstraint(lambda a,b: nse_sell_price.index(a) == nse_sell_qty.index(b), ('nse_sell_price','nse_sell_qty'))
    problem.addConstraint(lambda a,b: bse_buy_price.index(a) == bse_buy_qty.index(b), ('bse_buy_price','bse_buy_qty'))
    problem.addConstraint(lambda a,b: a >= b, ('bse_buy_price','nse_sell_price'))
    #problem.addConstraint(lambda a, b: a[0]*a[1] - b[0]*b[1] >= PROFIT, ('bse_buy','nse_sell'))
    problem.addConstraint(AllDifferentConstraint())

    nse_solutions = problem.getSolutions()

    # Buying BSE and selling NSE
    problem = Problem()
    problem.addVariable('bse_sell_price', trade['bse_sell_price'])
    problem.addVariable('bse_sell_qty', trade['bse_sell_qty'])
    problem.addVariable('nse_buy_price', trade['nse_buy_price'])
    problem.addVariable('nse_buy_qty', trade['nse_buy_qty'])

    problem.addConstraint(lambda a, b: a >= b, ('bse_sell_qty', 'nse_buy_qty'))
    problem.addConstraint(lambda a,b: bse_sell_price.index(a) == bse_sell_qty.index(b), ('bse_sell_price','bse_sell_qty'))
    problem.addConstraint(lambda a,b: nse_buy_price.index(a) == nse_buy_qty.index(b), ('nse_buy_price','nse_buy_qty'))
    problem.addConstraint(lambda a,b: a >= b, ('nse_buy_price','bse_sell_price'))
    #problem.addConstraint(lambda a, b, c, d: a*b - c*d >= PROFIT, ('nse_buy_price','nse_buy_qty','bse_sell_price','bse_sell_qty'))   
    problem.addConstraint(AllDifferentConstraint())

    bse_solutions = problem.getSolutions()

    return create_solution_json(nse_solutions, bse_solutions, trade)


def create_solution_json(nse_solutions, bse_solutions, trade):
     """
    Accepts solutions of both stock exchanges and creates a formatted output with the required fields

    Parameters:
    nse_solutions: solutions of the arbitrage problem as generated when buying NSE and selling BSE
    bse_solutions: solutions of the arbitrage problem as generated when buying BSE and selling NSE
    trade: details of the stock

    Returns:
    solution_json: Is the formatted arbitrage opportunities
    """
    solution_json = dict()
    bse_solution_list = []
    nse_solution_list = []

    solution_json['security_code'] = trade['security_id']
    solution_json['company_name'] = trade['company_name']

    for solution in bse_solutions:
        temp = dict()
        temp['BuyMkt'] = 'BSE'
        temp['SellMkt'] = 'NSE'
        temp['BuyPrice'] = solution['bse_sell_price']
        temp['SellPrice'] = solution['nse_buy_price']
        temp['OrderQty'] = solution['nse_buy_qty']
        bse_solution_list.append(temp)

    for solution in nse_solutions:
        temp = dict()
        temp['BuyMkt'] = 'NSE'
        temp['SellMkt'] = 'BSE'
        temp['BuyPrice'] = solution['nse_sell_price']
        temp['SellPrice'] = solution['bse_buy_price']
        temp['OrderQty'] = solution['bse_buy_qty']
        nse_solution_list.append(temp)

    solution_json['arbitrage_opportunities'] = bse_solution_list + nse_solution_list

    return solution_json