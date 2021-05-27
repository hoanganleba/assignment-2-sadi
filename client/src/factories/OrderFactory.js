import axios from "./axiosInstance";

export default {
    createOrder(obj) {
        return axios.post('/order',obj);
    },
    getAllOrders(){
        return axios.get('/orders')
    },
    editOrder(id,obj){
        return axios.put(`/order/${id}`,obj)
    },
    getOrder(id){
        return axios.get(`/order/${id}`)
    },
    deleteOrder(id){
        return axios.delete(`/order/${id}`)
    },
}