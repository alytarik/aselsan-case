import axios from 'axios';

//get base url from environment variable
const baseURL = process.env.REACT_APP_API_URL || 'http://localhost:8080/api/v1/';

export default axios.create({
    baseURL: baseURL
});