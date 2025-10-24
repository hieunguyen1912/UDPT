import axios from "axios";
import { CONFIG } from "./configuration";

const httpClient = axios.create({
  baseURL: CONFIG.API_GATEWAY,
  timeout: 300000,
  headers: {
    "Content-Type": "application/json",
  },
});

export default httpClient;
