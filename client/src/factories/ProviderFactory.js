import axios from "./axiosInstance";

export default {
    createProvider(obj) {
        return axios.post('/provider',obj);
    },
    getAllProviders(){
        return axios.get('/providers')
    },
    editProvider(id,obj){
        return axios.put(`/provider/${id}`,obj)
    },
    getProvider(id){
        return axios.get(`/provider/${id}`)
    },
    deleteProvider(id){
        return axios.delete(`/provider/${id}`)
    },
}