import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080",
  // baseURL: "http://3.86.5.222:8080",
  // baseURL: "https://zenitebackend.azurewebsites.net",
});

export default api;
