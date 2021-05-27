import axios from "./axiosInstance";

export default {
    createInventoryDelivery(obj) {
        return axios.post('/inventoryDelivery',obj);
    },
    getAllInventoryDeliveries(){
        return axios.get('/inventoryDeliveries')
    },
    editInventoryDelivery(id,obj){
        return axios.put(`/inventoryDelivery/${id}`,obj)
    },
    getInventoryDelivery(id){
        return axios.get(`/inventoryDelivery/${id}`)
    },
    deleteInventoryDelivery(id){
        return axios.delete(`/inventoryDelivery/${id}`)
    },
}