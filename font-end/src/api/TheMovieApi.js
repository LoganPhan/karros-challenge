
import axios from 'axios';
const TheMovieAPI = () => {
    const API_KEY = `?api_key=${process.env.REACT_APP_API_KEY}`;

    const API_URL = process.env.REACT_APP_API_DOMAIN_URL;
    const API_VERSION = process.env.REACT_APP_API_VERSION;
    const client = axios.create({baseURL: `${API_URL}\\${API_VERSION}`});
    const defaultOptions = {
        headers: {
            "Content-type": "application/json;charset=utf8",
        },
    }

    client.interceptors.response.use(
        response => response,
        error => {
            return Promise.reject(error);
        })

    return {
        get: (url, options = {}) => client.get(url + API_KEY, { ...defaultOptions, ...options }),
        post: (url, data, options = {}) => client.post(url + API_KEY, data, { ...defaultOptions, ...options }),
        put: (url, data, options = {}) => client.put(url + API_KEY, data, { ...defaultOptions, ...options }),
        delete: (url, options = {}) => client.delete(url + API_KEY, { ...defaultOptions, ...options })
    };
}

export default TheMovieAPI;