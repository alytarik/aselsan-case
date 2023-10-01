import axios from 'axios';

//get base url from environment variable
const baseURL = process.env.REACT_APP_API_URL || 'https://aselsan-backend.onrender.com/api/v1/';

export default axios.create({
    baseURL: baseURL
});