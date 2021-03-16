import { toast } from "react-toastify";

export default function showToast(message) {
  return toast.dark(message, {
    position: "bottom-right",
    autoClose: 2000,
    hideProgressBar: true,
  });
}
