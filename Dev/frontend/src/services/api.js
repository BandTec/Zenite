import axios from 'axios';

const api = axios.create({
    baseURL: 'https://backendzenite.azurewebsites.net:8080',
})

export default api;