async function updateUserDetails(user) {
  const response = await fetch("/api/users", {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(user),
  });
  const body = await response.json();
  if (response.ok) {
    return body;
  } else {
    throw new Error(body.message);
  }
}

async function getUser() {
  const response = await fetch(`/api/users/profile`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  });
  const body = await response.json();
  if (response.ok) {
    return body;
  } else {
    throw new Error(body.message);
  }
}

export { getUser, updateUserDetails };
