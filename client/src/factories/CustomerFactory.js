import axios from "./axiosInstance";

export default {
    createCustomer(obj) {
        return axios.post('/customer',obj);
    },
    searchCustomerName(search) {
        return axios.get(`customers?search=(name:'${search}')`)
    },
    searchCustomerAddress(search) {
        return axios.get(`customers?search=(address:'${search}')`)
    },
    searchCustomerPhoneNumber(search) {
        return axios.get(`customers?search=(phoneNumber:'${search}')`)
    },
    getAllCustomers(){
        return axios.get('/customers')
    },
    editCustomer(id,obj){
        return axios.put(`/customer/${id}`,obj)
    },
    getCustomer(id){
        return axios.get(`/customer/${id}`)
    },
    deleteCustomer(id){
        return axios.delete(`/customer/${id}`)
    },
}