import { useFetch } from "src/hooks/useFetch";

async function getMutualFundOrdersBySipStatus(userId,page,size) {
   const response = await fetch("/api/mutualfund/sip-status/users?userId="+userId+"&page="+page+"&size="+size
  )
  const body = await response.json();
  return body;
} 

async function getMutualFundOrdersCountBySipStatus(userId) {
   const response = await fetch("/api/mutualfun/sip-status-records/users?userId="+userId
  )
  const body = await response.json();
  return body;
} 

async function deleteSIPStatus(mutualFundOrderId){
    const response = await fetch(`/api/mutualfund/delete-sip-status/users?mutualFundOrderId=${mutualFundOrderId}`, {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(mutualFundOrderId),
  });
}
 async function editMutualFundTicket(id) {
  const response = await fetch("/api/assets/stocks/" + id);
  const body = await response.json();
  if (response.ok) {
    return body;
  } else {
    throw new Error(body.message);
  }
}

export {getMutualFundOrdersBySipStatus,deleteSIPStatus,editMutualFundTicket,getMutualFundOrdersCountBySipStatus};