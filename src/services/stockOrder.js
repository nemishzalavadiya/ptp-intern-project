async function createStockOrder(order)
{
    const response = await fetch("/api/stock/orders", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(order),
      });
      const body = await response.json();
      console.log(body);
      if(response.ok)
      {
          return body;
      }
      else
      {
          throw new Error(body.message)
      }
}
export {createStockOrder}
