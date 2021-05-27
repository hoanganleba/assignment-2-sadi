import axios from "./axiosInstance";

export default {
    createInventoryReceiveDetail(obj) {
        return axios.post('/inventoryReceiveDetail',obj);
    },
    getAllInventoryReceiveDetails(){
        return axios.get('/inventoryReceiveDetails')
    },
    editInventoryReceiveDetail(id,obj){
        return axios.put(`/inventoryReceiveDetail/${id}`,obj)
    },
    getInventoryReceiveDetail(id){
        return axios.get(`/inventoryReceiveDetail/${id}`)
    },
    deleteInventoryReceiveDetail(id){
        return axios.delete(`/inventoryReceiveDetail/${id}`)
    },
}