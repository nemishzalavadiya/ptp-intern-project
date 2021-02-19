/*
  Component: Watchlist
  props: None
  TODO: 
  1. Handle Error on fetching
*/
import React from "react";
import { Menu } from "semantic-ui-react";
import { getAllWatchlistByUserId } from "../../services/watchlist";
import Loading from "../loader/Loading";
import WatchlistById from "./WatchlistById";
import {useState} from 'react';
const content = [
  { header: "Company_Id", icon: "" },
  { header: "Open", icon: <i className="rupee sign icon small"></i> },
  { header: "Close", icon: <i className="rupee sign icon small"></i> },
  { header: "last", icon: <i className="rupee sign icon small"></i> },
  { header: "High", icon: <i className="rupee sign icon small"></i> },
  { header: "Low", icon: <i className="rupee sign icon small"></i> },
  { header: "% CHG", icon: <i className="percent icon small"></i> },
];

export default function Watchlist() {
  const [activeItem, setActiveItem] = useState(0);
  const [isContentFetchingCompleted, response] = getAllWatchlistByUserId(
    "00000000-0000-0000-0000-000000000000"
  );
  function handleItemClick( index ){setActiveItem(index)}

  return isContentFetchingCompleted ? (
    <div style={{backgroundColor:'black'}}>
      <Menu pointing inverted secondary>
        {
          response.content.map((item,index)=>{
            return <Menu.Item
            key={index}
            name={item.name}
            active={activeItem === index}
            onClick={()=>handleItemClick(index)}
          />
          })
        }
      </Menu>
        <WatchlistById content={content} watchlistId={response.content[activeItem].id}/>
    </div>
  ) : (
    <Loading />
  );
}
