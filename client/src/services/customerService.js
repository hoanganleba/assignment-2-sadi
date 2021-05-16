import axios from './axios';

export default {
    getCustomers() {
        return axios.get('/customers');
    },
    getCustomer(customerId) {
        return axios.get(`/customer/${customerId}`)
    },
    createCustomer(obj) {
        return axios.post('/customer', obj);
    },
    updateCustomer(customerId, obj)  {
        return axios.put(`/customer/${customerId}`, obj);
    },
    deleteCustomer (customerId) {
        return axios.delete(`/customer/${customerId}`)
    }
}