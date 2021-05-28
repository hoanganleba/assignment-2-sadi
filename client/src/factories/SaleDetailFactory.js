import axios from "./axiosInstance";

export default {
    createSaleDetail(saleId, obj) {
        return axios.post(`/sale/${saleId}/saleDetail`,obj);
    },
    getAllSaleDetails(){
        return axios.get('/saleDetails')
    },
    editSaleDetail(id,obj){
        return axios.put(`/saleDetail/${id}`,obj)
    },
    getSaleDetail(id){
        return axios.get(`/saleDetail/${id}`)
    },
    deleteSaleDetail(id){
        return axios.delete(`/saleDetail/${id}`)
    },
}