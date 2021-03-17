export const capitalize = (str) => str.charAt(0).toUpperCase() + str.slice(1).toLowerCase();
export const underscoreToCapitalize = (str) =>
  str
    .toLowerCase()
    .split("_")
    .map((word) => word.charAt(0).toUpperCase() + word.slice(1))
    .join(" ");
