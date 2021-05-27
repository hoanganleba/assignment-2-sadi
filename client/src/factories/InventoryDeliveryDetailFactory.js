import axios from "./axiosInstance";

export default {
    createInventoryDeliveryDetail(obj) {
        return axios.post('/inventoryDeliveryDetail',obj);
    },
    getAllInventoryDeliveryDetails(){
        return axios.get('/inventoryDeliveryDetails')
    },
    editInventoryDeliveryDetail(id,obj){
        return axios.put(`/inventoryDeliveryDetail/${id}`,obj)
    },
    getInventoryDeliveryDetail(id){
        return axios.get(`/inventoryDeliveryDetail/${id}`)
    },
    deleteInventoryDeliveryDetail(id){
        return axios.delete(`/inventoryDeliveryDetail/${id}`)
    },
}