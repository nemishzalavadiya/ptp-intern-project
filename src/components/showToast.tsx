import { toast } from "react-toastify";

export default function showToast(message, isError) {
  return !isError
    ? toast.dark(message, {
        position: "bottom-right",
        autoClose: 2000,
        hideProgressBar: true,
      })
    : toast.error(message, {
        position: "bottom-right",
        autoClose: 2000,
        hideProgressBar: true,
      });
}
