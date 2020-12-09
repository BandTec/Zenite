import axios from "axios";

const api = axios.create({
  // baseURL: "http://localhost:8080",
  baseURL: "http://3.213.187.255:8080",
  // baseURL: "https://zenitebackend.azurewebsites.net",
});

export default api;
