import React, { useState, useEffect } from "react";
import { Form, Button, Segment, Grid } from "semantic-ui-react";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import SockJS from "sockjs-client";
import Stomp from "stompjs";

import { createStockOrder } from "src/services/stockOrder";
import { OrderType } from "src/enums/orderType.ts";
import { PriceType } from "src/enums/priceType.ts";
import { Action } from "src/enums/action.ts";
import { WebSocketUrl, UserId } from "src/components/Objects";

export default function StockTicket({ assetId, stockId }) {
  const [action, setAction] = useState(Action.BUY);
  const [orderType, setOrderType] = useState(OrderType.DELIVERY);
  const [priceType, setPriceType] = useState(PriceType.LIMIT);
  const [price, setPrice] = useState(0);
  const [currentPrice, setCurrentPrice] = useState(0);
  const [volume, setVolume] = useState(0);
  const [isOrderExecuting, setOrdrStatus] = useState(false);

  useEffect(() => {
    const webSocket = new SockJS(WebSocketUrl.url);
    const stompClient = Stomp.over(webSocket);
    stompClient.connect({}, async function (frame) {
      stompClient.subscribe(
        "/topic/" + assetId,
        function (data) {
          let contentBody = JSON.parse(data.body);
          setCurrentPrice(contentBody.marketPrice);
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
    setOrdrStatus(true);
    event.preventDefault();

    let data = {
      tradeVolume: volume,
      action: action,
      priceType: priceType,
      orderType: orderType,
      price: priceType === PriceType.MARKET ? currentPrice : price,
      user: {
        id: UserId.userId,
      },
      stockDetail: {
        id: stockId,
      },
    };

    createStockOrder(data)
      .then((res) => {
        toast("Order Executed Successfully!", {
          position: "bottom-right",
          autoClose: 2000,
          hideProgressBar: false,
          
        });
      })
      .catch((err) => {
        toast.error(err.message, {
          position: "bottom-right",
          autoClose: 2000,
          hideProgressBar: false,
        });
      });
    setOrdrStatus(false);
  };

  return (
    <Segment className="stockTicket">
      <Form inverted>
        <Grid>
          <Grid.Row>
            <Grid.Column width={5}>
              <label> OrderType </label>
            </Grid.Column>
            <Grid.Column width={8}>
              <Button.Group name="ordrType">
                <Button
                  color="grey"
                  positive={orderType === OrderType.DELIVERY}
                  onClick={(event) => setOrderType(OrderType.DELIVERY)}
                >
                  Delivery
                </Button>
                <Button
                  color="grey"
                  positive={orderType === OrderType.INTRADAY}
                  onClick={(event) => setOrderType(OrderType.INTRADAY)}
                >
                  IntraDay
                </Button>
              </Button.Group>
            </Grid.Column>
          </Grid.Row>
          <Grid.Row>
            <Grid.Column width={5}>
              <label> PriceType </label>
            </Grid.Column>
            <Grid.Column width={8}>
              <Button.Group name="priceType">
                <Button
                  color="grey"
                  positive={priceType === PriceType.MARKET}
                  onClick={(event) => setPriceType(PriceType.MARKET)}
                >
                  Market
                </Button>
                <Button
                  color="grey"
                  positive={priceType === PriceType.LIMIT}
                  onClick={(event) => setPriceType(PriceType.LIMIT)}
                >
                  Limit
                </Button>
              </Button.Group>
            </Grid.Column>
          </Grid.Row>
          <Grid.Row>
            <Grid.Column width={5}>
              <label> Action </label>
            </Grid.Column>
            <Grid.Column width={8}>
              <Button.Group name="action">
                <Button
                  color="grey"
                  positive={action === Action.SELL}
                  onClick={(event) => setAction(Action.SELL)}
                >
                  SELL
                </Button>
                <Button
                  color="grey"
                  positive={action === "BUY"}
                  onClick={(event) => setAction("BUY")}
                >
                  BUY
                </Button>
              </Button.Group>
            </Grid.Column>
          </Grid.Row>

          <Grid.Row>
            <Grid.Column width={5}>
              <label>Price</label>
            </Grid.Column>
            <Grid.Column width={8}>
              <Form.Input
                size="mini"
                type="number"
                onChange={(event) => setPrice(event.target.value)}
                readOnly={priceType === PriceType.MARKET}
                fluid
                value={priceType === PriceType.MARKET ? currentPrice : price}
                iconPosition="left"
                icon="rupee"
              />
            </Grid.Column>
          </Grid.Row>
          <Grid.Row>
            <Grid.Column width={5}>
              <label>Volume</label>
            </Grid.Column>
            <Grid.Column width={8}>
              <Form.Input
                size="mini"
                onChange={(event) => setVolume(event.target.value)}
                type="number"
                step="1"
                fluid
              />
            </Grid.Column>
          </Grid.Row>
          <Button
            type="submit"
            onClick={(event) => createOrder(event)}
            fluid
            disabled={
              volume <= 0 ||
              (price <= 0 && priceType === PriceType.LIMIT) ||
              isOrderExecuting
            }
          >
            Invest Now
          </Button>
          <ToastContainer />
        </Grid>
      </Form>
    </Segment>
  );
}
