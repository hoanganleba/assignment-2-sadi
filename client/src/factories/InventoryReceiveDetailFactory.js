import axios from "./axiosInstance";

export default {
    createInventoryReceiveDetail(inventoryId, obj) {
        return axios.post(`/inventoryReceive/${inventoryId}/inventoryReceiveDetail`,obj);
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