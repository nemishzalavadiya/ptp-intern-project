import React, { useState } from "react";
import { Form, Button, Segment, Grid } from "semantic-ui-react";
import { ToastContainer, toast } from "react-toastify";
import showToast from "src/components/showToast";
import { createStockOrder } from "src/services/stockOrder";
import { ProductCode } from "src/enums/productCode";
import { OrderType } from "src/enums/orderType";
import { Action } from "src/enums/action";
export default function StockTicket({ assetId, stockId,currentPrice }) {
  const [action, setAction] = useState(Action.BUY);
  const [productCode, setProductCode] = useState(ProductCode.CNC);
  const [orderType, setOrderType] = useState(OrderType.LIMIT);
  const [price, setPrice] = useState(0);
  const [volume, setVolume] = useState(0);
  const [isOrderExecuting, setOrderStatus] = useState(false);
  const createOrder = async (event) => {
    setOrderStatus(true);
    event.preventDefault();
    let data = {
      tradeVolume: volume,
      action: action,
      orderType: orderType,
      productCode: productCode,
      price: orderType === OrderType.MARKET ? currentPrice : price,
      stockDetail: {
        id: stockId,
      },
    };
    createStockOrder(data)
      .then((res) => {
        setOrderStatus(false);
        showToast("Order Executed Successfully!")
      })
      .catch((err) => {
        setOrderStatus(false);
        showToast(err.message,true)
      });
  };
  return (
    <Segment className="stockTicket">
      <Form inverted>
        <Grid>
          <Grid.Row>
            <Grid.Column width={6}>
              <label> Product Code </label>
            </Grid.Column>
            <Grid.Column width={10}>
              <Button.Group name="productCode" fluid widths="2">
                <Button
                  color="grey"
                  positive={productCode === ProductCode.CNC}
                  onClick={() => setProductCode(ProductCode.CNC)}
                >
                  CNC
                </Button>
                <Button
                  color="grey"
                  positive={productCode === ProductCode.MIS}
                  onClick={() => setProductCode(ProductCode.MIS)}
                >
                  MIS
                </Button>
              </Button.Group>
            </Grid.Column>
          </Grid.Row>
          <Grid.Row>
            <Grid.Column width={6}>
              <label> OrderType </label>
            </Grid.Column>
            <Grid.Column width={10}>
              <Button.Group name="orderType" fluid widths="2">
                <Button
                  color="grey"
                  positive={orderType === OrderType.MARKET}
                  onClick={() => setOrderType(OrderType.MARKET)}
                >
                  Market
                </Button>
                <Button
                  color="grey"
                  positive={orderType === OrderType.LIMIT}
                  onClick={() => setOrderType(OrderType.LIMIT)}
                >
                  Limit
                </Button>
              </Button.Group>
            </Grid.Column>
          </Grid.Row>
          <Grid.Row>
            <Grid.Column width={6}>
              <label> Side </label>
            </Grid.Column>
            <Grid.Column width={10}>
              <Button.Group name="action" fluid widths="2">
                <Button
                  color="grey"
                  positive={action === Action.SELL}
                  onClick={() => setAction(Action.SELL)}
                >
                  SELL
                </Button>
                <Button
                  color="grey"
                  positive={action === Action.BUY}
                  onClick={() => setAction(Action.BUY)}
                >
                  BUY
                </Button>
              </Button.Group>
            </Grid.Column>
          </Grid.Row>
          <Grid.Row>
            <Grid.Column width={6}>
              <label>Price</label>
            </Grid.Column>
            <Grid.Column width={10}>
              <Form.Input
                size="mini"
                type="number"
                onChange={(event) => setPrice(event.target.value)}
                disabled={orderType === OrderType.MARKET}
                fluid
                value={orderType === OrderType.MARKET ? currentPrice : price}
                iconPosition="left"
                icon="rupee"
              />
            </Grid.Column>
          </Grid.Row>
          <Grid.Row>
            <Grid.Column width={6}>
              <label>Quantity</label>
            </Grid.Column>
            <Grid.Column width={10}>
              <Form.Input
                size="mini"
                onChange={(event) => setVolume(event.target.value)}
                type="number"
                step="1"
                fluid
              />
            </Grid.Column>
          </Grid.Row>
          <Grid.Row>
            <Grid.Column width={6}>
              <label>Total Amount</label>
            </Grid.Column>
            <Grid.Column width={10}>
              <label>
                {(orderType === OrderType.MARKET
                  ? volume * currentPrice
                  : volume * price
                ).toFixed(2)}
              </label>
            </Grid.Column>
          </Grid.Row>
          <Button
            className="invest"
            type="submit"
            onClick={createOrder}
            fluid
            disabled={
              volume <= 0 ||
              (price <= 0 && orderType === OrderType.LIMIT) ||
              isOrderExecuting
            }
          >
            {action == Action.BUY ? `Invest Now` : `Sell`}
          </Button>
          <ToastContainer />
        </Grid>
      </Form>
    </Segment>
  );
}
