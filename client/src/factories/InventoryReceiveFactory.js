import axios from "./axiosInstance";

export default {
    createInventoryReceive(obj) {
        return axios.post('/inventoryReceive',obj);
    },
    getAllInventoryReceives(){
        return axios.get('/inventoryReceives')
    },
    getAllInventoryReceivesByPeriod(startDate, endDate) {
        return axios.get('/inventoryReceivesByPeriod', {params: {
                startDate,
                endDate
            }})
    },
    editInventoryReceive(id,obj){
        return axios.put(`/inventoryReceive/${id}`,obj)
    },
    getInventoryReceive(id){
        return axios.get(`/inventoryReceive/${id}`)
    },
    deleteInventoryReceive(id){
        return axios.delete(`/inventoryReceive/${id}`)
    },
}