import React, { useState, useEffect } from "react";
import { Form, Button, Segment, Grid, Label } from "semantic-ui-react";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import SockJS from "sockjs-client";
import Stomp from "stompjs";
import { createStockOrder } from "src/services/stockOrder";
import { ProductCode } from "src/enums/productCode";
import { OrderType } from "src/enums/orderType";
import { Action } from "src/enums/action";
import { WebSocketUrl} from "src/components/Objects";
export default function StockTicket({ assetId, stockId }) {
  const [action, setAction] = useState(Action.BUY);
  const [productCode, setProductCode] = useState(ProductCode.CNC);
  const [orderType, setOrderType] = useState(OrderType.LIMIT);
  const [price, setPrice] = useState(0);
  const [currentPrice, setCurrentPrice] = useState(0);
  const [volume, setVolume] = useState(0);
  const [isOrderExecuting, setOrderStatus] = useState(false);
  useEffect(() => {
    const webSocket = new SockJS(WebSocketUrl.url);
    const stompClient = Stomp.over(webSocket);
    stompClient.debug = (f) => f;
    stompClient.connect({}, async function (frame) {
      stompClient.subscribe(
        "/topic/" + assetId,
        function (data) {
          let contentBody = JSON.parse(data.body);
          setCurrentPrice((contentBody.marketPrice).toFixed(2));
        },
        { id: assetId }
      );
    });
    return () => {
      stompClient.unsubscribe(assetId);
      stompClient.disconnect();
    };
  }, []);
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
        toast("Order Executed Successfully!", {
          position: "bottom-right",
          autoClose: 2000,
          hideProgressBar: false,
        });
      })
      .catch((err) => {
        setOrderStatus(false);
        toast.error(err.message, {
          position: "bottom-right",
          autoClose: 2000,
          hideProgressBar: false,
        });
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
              <label>{(orderType === OrderType.MARKET?volume*currentPrice:volume*price).toFixed(2)}</label>
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
            {action==Action.BUY?`Invest Now`:`Sell`}
          </Button>
          <ToastContainer />
        </Grid>
      </Form>
    </Segment>
  );
}