import axios from "./axiosInstance";

export default {
    createProduct(obj) {
        return axios.post('/product',obj);
    },
    getAllProducts(){
        return axios.get('/products')
    },
    editProduct(id,obj){
        return axios.put(`/product/${id}`,obj)
    },
    getProduct(id){
        return axios.get(`/product/${id}`)
    },
    deleteProduct(id){
        return axios.delete(`/product/${id}`)
    },
}