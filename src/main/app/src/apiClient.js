import axios from "axios";

export default {
    get(endpoint) {
        return axios.get(endpoint);
    },
    post(endpoint, data, options) {
        return axios.post(endpoint, data, options);
    }
}