import React from 'react';
import { useState } from "react";
import { Form, Button, Segment, Grid, Message, Icon, Dropdown, Select } from "semantic-ui-react";
import { createMutualFundOrder } from 'src/services/mutualFundOrder';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { InvestmentType } from 'src/enums/InvestmentType';
import { Frequency } from 'src/enums/Frequency'
import { UserId } from "src/components/Objects";

export default function MutualFundTicket(props) {
  const [investmentType, setInvestmentType] = useState(InvestmentType.SIP);
  const [amount, setAmount] = useState(0);
  const [amountTag, setAmountTag] = useState("Amount");
  const [date, setDate] = useState("");
  const [frequency, setFrequency] = useState(Frequency.MONTHLY_SIP)
  const [isOrderExecuting, setOrderStatus] = useState(false);

  const mfId = props.mfDetail.mutualFundDetail.id;
  const minSIP = props.mfDetail.minSIP;
  const frequencyOptions = [
    {
      key: 'WEEKY_SIP',
      text: 'WEEKLY_SIP',
      value: 'WEEKLY_SIP',
    },
    {
      key: 'MONTHLY_SIP',
      text: 'MONTHLY_SIP',
      value: 'MONTHLY_SIP',

    }, {
      key: 'YEARLY_SIP',
      text: 'YEARLY_SIP ',
      value: 'YEARLY_SIP',
    }
  ]


  const createOrder = async (event) => {
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
        id: mfId
      }
    }
    if (investmentType == InvestmentType.ONE_TIME) {
      data.sipdate = "";
      data.investmentType = InvestmentType.ONE_TIME
    }
    createMutualFundOrder(data).
      then(() => {
        setOrderStatus(false);
        toast('Order executed successfully', {
          position: "bottom-right",
          autoClose: 2000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: false,
          draggable: false,
          progress: undefined,
        });

      }).
      catch((err) => {
        setOrderStatus(false);
        toast('Something went wrong please try', {
          position: "bottom-right",
          autoClose: 2000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: false,
          draggable: false,
          progress: undefined,
        });
      })
  }

  return (

    <Segment className="mutualFundTicket">
      <Form inverted>
        <Grid>
          <Grid.Row>
            <Grid.Column width={5}>
              <label>Investment Type</label>
            </Grid.Column>
            <Grid.Column width={11}>
              <Button.Group name="orderType">
                <Button
                  color="grey"
                  positive={investmentType === InvestmentType.SIP}
                  onClick={() => { setInvestmentType(InvestmentType.SIP); setAmountTag("Amount"); }}
                >
                  SIP
                </Button>
                <Button
                  color="grey"
                  positive={investmentType === InvestmentType.ONE_TIME}
                  onClick={() => { setInvestmentType(InvestmentType.ONE_TIME); setAmountTag("Lumpsum Ammount"); }}
                >
                  LUMPSUM
                </Button>
              </Button.Group>
            </Grid.Column>
          </Grid.Row>
          <Grid.Row>
            <Grid.Column width={5}>
              <label> Frequency </label>
            </Grid.Column>
            <Grid.Column width={11}>

              <Select disabled={investmentType === InvestmentType.ONE_TIME}
                placeholder='Frequency'
                fluid
                onChange={(event) => { setFrequency(event.target.innerText) }}
                selection
                color="Grey"
                options={frequencyOptions}
              />
            </Grid.Column>
          </Grid.Row>


          <Grid.Row>
            <Grid.Column width={5}>
              <label> {amountTag} </label>
            </Grid.Column>
            <Grid.Column width={11}>
              <Form.Input
                type="float"
                onChange={(event) => setAmount(event.target.value)}

                placeholder="amount"
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
                placeholder="Date"
                required
                onChange={(event) => setDate(event.target.value)}
              />
            </Grid.Column>
          </Grid.Row>
          <Button
            type="submit"
            onClick={createOrder}
            fluid
            disabled={
              (amount < minSIP) ||
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