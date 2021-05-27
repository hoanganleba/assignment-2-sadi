import axios from "./axiosInstance";

export default {
    createSale(obj) {
        return axios.post('/sale',obj);
    },
    getAllSales(){
        return axios.get('/sales')
    },
    editSale(id,obj){
        return axios.put(`/sale/${id}`,obj)
    },
    getSale(id){
        return axios.get(`/sale/${id}`)
    },
    deleteSale(id){
        return axios.delete(`/sale/${id}`)
    },
}