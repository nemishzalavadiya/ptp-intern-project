import React,{useState,useEffect} from 'react';
import { getPositionByuserAndAsset} from 'src/services/position';
import StockPosition from 'src/components/position/stockPositionView';
import {UserId} from "src/components/Objects";
import Link from 'next/link'
import { Loader } from "semantic-ui-react";

export default function StockPositionList({value,page,handlePaginationChange})
{
  
    let positionList=[];
    let companyUuids=[];
    let [isCompleted,response]=[false];
    [isCompleted,response]=getPositionByuserAndAsset(UserId.userId,"stock",value,page,5);

  
  
  const pagination = {
    activePage: page,
    totalPages: response.totalPages,
    handlePaginationChange:handlePaginationChange
  }

    if(isCompleted)
    {
       response.content.forEach(element => {
       companyUuids.push(element.assetDetail.id);
        positionList.push([
          <Link href={`/details/${element.assetDetail.id}`}>{element.assetDetail.name}</Link>,
          element.volume,
          element.price,
          element.price*element.volume
          ])
      });
    }
  return !isCompleted?<Loader active/>:
  <StockPosition uuid={companyUuids} positionList={positionList} pagination={pagination}></StockPosition>
  }
