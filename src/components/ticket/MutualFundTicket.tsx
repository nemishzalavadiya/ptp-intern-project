import React from "react";
import { useState } from "react";
import {
  Form,
  Button,
  Segment,
  Grid,
  Message,
  Icon,
  Dropdown,
  Select,
} from "semantic-ui-react";
import {
  createMutualFundOrder,
  updateMutualFundOrder,
} from "src/services/mutualFundOrder";
import { ToastContainer, toast } from "react-toastify";
import { InvestmentType } from "src/enums/InvestmentType";
import { Frequency } from "src/enums/Frequency";
import { UserId } from "src/components/Objects";
import showToast from "src/components/showToast";

export default function MutualFundTicket(props) {
  const [investmentType, setInvestmentType] = useState(InvestmentType.SIP);
  const [amount, setAmount] = useState(
    props.isUpdate ? props.ticketDetail.amount : 0
  );
  const [date, setDate] = useState(
    props.isUpdate ? props.ticketDetail.date : ""
  );
  const [frequency, setFrequency] = useState(
    props.isUpdate ? props.ticketDetail.frequency : Frequency.MONTHLY_SIP
  );
  const [isOrderExecuting, setOrderStatus] = useState(false);

  const mfId = props.mfDetail.mutualFundDetail.id;
  const minSIP = props.mfDetail.minSIP;
  const frequencyOptions = [
    {
      key: "WEEKY_SIP",
      text: "Weekly",
      value: "WEEKLY_SIP",
    },
    {
      key: "MONTHLY_SIP",
      text: "Monthly",
      value: "MONTHLY_SIP",
    },
    {
      key: "YEARLY_SIP",
      text: "Yearly",
      value: "YEARLY_SIP",
    },
  ];

  const createOrder = async (event) => {
    if (props.isUpdate) {
      let data = {
        sipdate: date,
        sipStatus: "ACTIVE",
        investmentType: frequency,
        price: amount,
        user: {
          id: UserId.userId,
        },
        mutualFundDetail: {
          id: mfId,
        },
      };
      await updateMutualFundOrder(props.mfOrderId, data);
      props.setIsUpdate(!props.isUpdateFlag);
    } else {
      setOrderStatus(true);
      event.preventDefault();
      let data = {
        sipdate: date,
        investmentType: frequency,
        price: amount,
        user: {
          id: UserId.userId,
        },
        mutualFundDetail: {
          id: mfId,
        },
      };
      if (investmentType == InvestmentType.ONE_TIME) {
        data.sipdate = "";
        data.investmentType = InvestmentType.ONE_TIME;
      }
      createMutualFundOrder(data)
        .then(() => {
          setOrderStatus(false);
          toast("Order executed successfully", {
            position: "bottom-right",
            autoClose: 2000,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: false,
            draggable: false,
            progress: undefined,
          });
        })
        .catch((err) => {
          setOrderStatus(false);
          toast("Something went wrong please try", {
            position: "bottom-right",
            autoClose: 2000,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: false,
            draggable: false,
            progress: undefined,
          });
        });
    }
  };
  return (
    <Segment className="mutualFundTicket">
      <Form inverted>
        <Grid>
          {!props.isUpdate && (
            <Grid.Row>
              <Grid.Column width={5}>
                <label>Investment Type</label>
              </Grid.Column>
              <Grid.Column width={11}>
                <Button.Group name="orderType" widths="2" fluid>
                  <Button
                    color="grey"
                    positive={investmentType === InvestmentType.SIP}
                    onClick={() => {
                      setInvestmentType(InvestmentType.SIP);
                    }}
                  >
                    SIP
                  </Button>
                  <Button
                    color="grey"
                    positive={investmentType === InvestmentType.ONE_TIME}
                    onClick={() => {
                      setInvestmentType(InvestmentType.ONE_TIME);
                    }}
                  >
                    Lumpsum
                  </Button>
                </Button.Group>
              </Grid.Column>
            </Grid.Row>
          )}
          <Grid.Row>
            <Grid.Column width={5}>
              <label> Frequency </label>
            </Grid.Column>
            <Grid.Column width={11}>
              <Select
                disabled={investmentType === InvestmentType.ONE_TIME}
                fluid
                value={frequency}
                onChange={(event, data) => {
                  setFrequency(data.value);
                }}
                selection
                color="Grey"
                options={frequencyOptions}
              />
            </Grid.Column>
          </Grid.Row>

          <Grid.Row>
            <Grid.Column width={5}>
              <label> {investmentType === InvestmentType.ONE_TIME ? "Lumpsum Amount" : "Amount"} </label>
            </Grid.Column>
            <Grid.Column width={11}>
              <Form.Input
                type="float"
                value={amount}
                onChange={(event) => setAmount(event.target.value)}
                iconPosition="left"
                icon="rupee"
              />
            </Grid.Column>
          </Grid.Row>
          <Grid.Row>
            <Grid.Column width={5}>
              <label>Date</label>
            </Grid.Column>
            <Grid.Column width={11}>
              <Form.Input
                disabled={investmentType == InvestmentType.ONE_TIME}
                type="date"
                required
                value={date}
                onChange={(event) => setDate(event.target.value)}
              />
            </Grid.Column>
          </Grid.Row>
          <Button
            className="invest"
            type="submit"
            onClick={createOrder}
            fluid
            disabled={amount < minSIP || isOrderExecuting}
          >
            {props.isUpdate ? "Edit" : "Invest Now"}
          </Button>
          <ToastContainer />
        </Grid>
      </Form>
    </Segment>
  );
}
