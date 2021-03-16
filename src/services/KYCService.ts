async function addKYCDetails(formData) {
  const response = await fetch("/api/kyc", {
    method: "POST",
    body: formData,
  });

  if (response.ok) {
    return response;
  } else {
    const body = await response.json();
    throw new Error(body.message);
  }
}

async function isKycVerified() {
  const response = await fetch("/api/kyc");
  if (response.ok) {
    const body = await response.json();
    return body;
  } else {
    throw new Error("Something went wrong");
  }
}

export { addKYCDetails, isKycVerified };
