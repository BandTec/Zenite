import axios from 'axios';

const api = axios.create({
    baseURL: 'http://backendzenite.azurewebsites.net',
})

export default api;
