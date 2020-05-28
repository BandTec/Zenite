import axios from 'axios';

const api = axios.create({
    baseURL: 'https://backendzenite.azurewebsites.net',
})

export default api;
