import React from "react";
import { Loader, Grid, Button, Icon, Modal } from "semantic-ui-react";
import { useState, useEffect } from "react";
import Router from "next/router";
import { UserId } from "src/components/Objects";
import GridContainer from "src/components/grid/GridContainer";
import {
  getMutualFundOrdersBySipStatus,
  deleteSIPStatus,
} from "src/services/sipstatus";
import "semantic-ui-css/semantic.min.css";
import styles from "src/styles/Layout.module.scss";
import MutualFundTicket from "src/components/ticket/MutualFundTicket";
import { getMfByAssetId } from "src/services/assets";
import Moment from "moment";
import { updateMutualFundOrder } from "src/services/mutualFundOrder";
import { sipStatus } from "src/enums/sipStatus";

export default function MutualFundOrder(props) {
  let [isDataFetchingCompleted, SetIsDataFetchingCompleted] = useState(false);
  let [results, setResults] = useState([]);
  let [open, setOpen] = useState(false);
  let [MFEdit, setMFEdit] = useState({});
  var temp = [];
  let [mutualFundId, setMutualFundId] = useState("");
  let [mutualFundOrderId, setMutualFundOrderId] = useState("");
  let [ticketDetail, setTicketDetail] = useState({});
  let [dataResponse, setDataResponse] = useState([]);
  let [isUpdate, setIsUpdate] = useState(false);
  let [statusPlayFlag, setStatusPlayFlag] = useState(false);
  const header = [
    { header: "Company Name", icon: "" },
    { header: "Amount", icon: <i className="rupee sign icon small"></i> },
    { header: "Order Date", icon: "" },
    { header: "Frequency", icon: "" },
    { header: "Scheduled On", icon: "" },
    { header: "SIP Status", icon: "" },
    { header: "", icon: "" },
  ];

  const [page, setPage] = useState({
    pages: 0,
  });
  useEffect(() => {
    setResults([]);
    getMutualFundOrdersBySipStatus(UserId.userId, page.pages, 5).then((res) => {
      setDataResponse(res);
      res.content.map((item) => {
        temp.push([
          item.mutualFundDetail.assetDetail.name,
          item.price,
          item.timestamp.substr(0, 10),
          item.investmentType,
          item.sipdate.substring(0, 10),
          item.sipStatus,
          <Button.Group color="grey" icon>
            <Button
              onClick={() =>
                editSIP(
                  item.mutualFundDetail.assetDetail.id,
                  item.id,
                  item.investmentType,
                  item.price,
                  Moment(item.sipdate).format("yyyy-MM-DD")
                )
              }
            >
              <Icon name="pencil alternate" />
            </Button>
            <Button>
              <Icon
                onClick={() => updateStatus(item)}
                name={item.sipStatus === sipStatus.ACTIVE ? "pause" : "play"}
              />
            </Button>
            <Button onClick={() => deleteSIP(item.id)}>
              <Icon name="trash" />
            </Button>
          </Button.Group>,
        ]);
      });
      setResults(temp);
      SetIsDataFetchingCompleted(true);
    });
  }, [isUpdate, statusPlayFlag, page]);
  function handlePaginationChange(pageNo) {
    setPage({ pages: pageNo, userId: UserId.userId });
  }

  const pagination = {
    activePage: page.pages,
    totalPages: 2,
    handlePaginationChange: handlePaginationChange,
  };

  function handleItemClick(index) {
    setActiveItem(index);
  }

  function deleteSIP(mutualFundOrderId) {
    deleteSIPStatus(mutualFundOrderId);
    setIsUpdate(!isUpdate);
  }

  const [isMFFetchingComplete, MFResponse] = getMfByAssetId(mutualFundId);
  const updateStatus = async (mutualFundOrder) => {
    if (mutualFundOrder.sipStatus === sipStatus.PAUSED) {
      mutualFundOrder.sipStatus = sipStatus.ACTIVE;
    } else {
      mutualFundOrder.sipStatus = sipStatus.PAUSED;
    }
    const updatedSipStatus = mutualFundOrder.sipStatus;
    let data = {
      sipdate: mutualFundOrder.sipdate,
      sipStatus: updatedSipStatus,
      investmentType: mutualFundOrder.investmentType,
      price: mutualFundOrder.price,
      user: {
        id: UserId.userId,
      },
      mutualFundDetail: {
        id: mutualFundOrder.mutualFundDetail.id,
      },
    };
    await updateMutualFundOrder(mutualFundOrder.id, data);
    setIsUpdate(!isUpdate);
  };
  function editSIP(mfAssetId, mfOrderId, frequency, amount, date) {
    setIsUpdate(!isUpdate);
    setMutualFundId(mfAssetId);
    setMutualFundOrderId(mfOrderId);
    setTicketDetail({
      frequency: frequency,
      amount: amount,
      date: date,
    });
    setOpen(true);
  }
  useEffect(() => {
    setMFEdit({
      mutualFundDetail: {
        id: MFResponse.id,
      },
      minSIP: MFResponse?.minSIP,
    });
  }, [MFResponse]);
  pagination.totalPages = dataResponse.totalPages;
  return isDataFetchingCompleted ? (
    <div>
      <GridContainer content={header} data={results} pagination={pagination} />
      <Modal
        onClose={() => setOpen(false)}
        onOpen={() => setOpen(true)}
        open={open}
        basic
        size="large"
      >
        <Modal.Content>
          {
            <div align="center">
              <MutualFundTicket
                ticketDetail={ticketDetail}
                mfOrderId={mutualFundOrderId}
                isUpdate={true}
                isUpdateFlag={isUpdate}
                setIsUpdate={setIsUpdate}
                mfDetail={MFEdit}
              />
            </div>
          }
        </Modal.Content>
      </Modal>
    </div>
  ) : (
    <Loader active>Loading</Loader>
  );
}
