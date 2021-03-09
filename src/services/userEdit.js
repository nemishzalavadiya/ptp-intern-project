async function userEdit(user) {
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

async function getUserById(userId) {
  const response = await fetch(`/api/users/${userId}`, {
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

export { getUserById, userEdit };
