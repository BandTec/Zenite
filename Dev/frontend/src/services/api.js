import axios from "axios";

const api = axios.create({
  //baseURL: "http://localhost:8080",
  baseURL: "http://18.234.206.122:8080",
  // baseURL: "https://zenitebackend.azurewebsites.net",
});

export default api;
