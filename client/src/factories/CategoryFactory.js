import axios from "./axiosInstance";

export default {
    createCategory(obj) {
        return axios.post('/category',obj);
    },
    getAllCategories(){
        return axios.get('/categories')
    },
    editCategory(id,obj){
        return axios.put(`/category/${id}`,obj)
    },
    getCategory(id){
        return axios.get(`/category/${id}`)
    },
    deleteCategory(id){
        return axios.delete(`/category/${id}`)
    },
}