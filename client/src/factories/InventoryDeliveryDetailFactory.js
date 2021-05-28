import axios from "./axiosInstance";

export default {
    createInventoryDeliveryDetail(inventoryId, obj) {
        return axios.post(`/inventoryDelivery/${inventoryId}/inventoryDeliveryDetail`,obj);
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