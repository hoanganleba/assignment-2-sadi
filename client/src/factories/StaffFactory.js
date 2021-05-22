import axios from "./axiosInstance";

export default {
    createStaff(obj) {
        return axios.post('/staff',obj);
    },
    getAllStaffs(){
        return axios.get('/staffs')
    },
    editStaff(id,obj){
        return axios.put(`/staff/${id}`,obj)
    },
    getStaff(id){
        return axios.get(`/staff/${id}`)
    },
    deleteStaff(id){
        return axios.delete(`/staff/${id}`)
    },
}