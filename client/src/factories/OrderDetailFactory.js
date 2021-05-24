import axios from "./axiosInstance";

export default {
    createOrderDetail(obj, orderId) {
        return axios.post(`/orderDetail/${orderId}`,obj);
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