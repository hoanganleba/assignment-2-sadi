import axios from "./axiosInstance";

export default {
    createOrderDetail(orderId,obj) {
        return axios.post(`/order/${orderId}/orderDetail`,obj);
    },
    getAllOrderDetails(){
        return axios.get('/orderDetails')
    },
    editOrderDetail(id,obj){
        return axios.put(`/orderDetail/${id}`,obj)
    },
    getOrderDetail(id){
        return axios.get(`/orderDetail/${id}`)
    },
    deleteOrderDetail(id){
        return axios.delete(`/orderDetail/${id}`)
    },
}