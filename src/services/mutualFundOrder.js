export async function createMutualFundOrder(order)
{
    const response = await fetch("/api/mutualfund/orders", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(order),
      });
      const body = await response.json();
      if(response.ok)
      {
          return body;
      }
      else
      {
          throw new Error(body.message)
      }
}