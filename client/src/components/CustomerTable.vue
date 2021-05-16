<template>
  <div>
    <h3>Customer</h3>
    <b-form class="d-flex my-4" inline @submit="createCustomer">
      <b-form-input
          v-model="customer.name"
          placeholder="Name"
      ></b-form-input>
      <b-form-input
          v-model="customer.address"
          placeholder="Address"
      ></b-form-input>
      <b-form-input
          v-model="customer.phoneNumber"
          placeholder="Phone Number"
      ></b-form-input>
      <b-form-input
          v-model="customer.fax"
          placeholder="Fax"
      ></b-form-input>
      <b-form-input
          v-model="customer.email"
          placeholder="Email"
      ></b-form-input>
      <b-form-input
          v-model="customer.contactPerson"
          placeholder="Contact Person"
      ></b-form-input>
      <b-button type="submit" variant="primary">Add</b-button>
    </b-form>
    <table class="table mt-3">
      <thead>
      <tr>
        <th scope="col">Name</th>
        <th scope="col">Address</th>
        <th scope="col">Phone Number</th>
        <th scope="col">Fax</th>
        <th scope="col">Email</th>
        <th scope="col">Contact</th>
        <th scope="col">Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(customer, index) in customers" :key="index">
        <th>{{ customer.name }}</th>
        <td>{{ customer.address }}</td>
        <td>{{ customer.phoneNumber }}</td>
        <td>{{ customer.fax }}</td>
        <td>{{ customer.email }}</td>
        <td>{{ customer.contactPerson }}</td>
        <td>
          <b-button variant="info">Update</b-button>
          <b-button variant="danger" @click="deleteCustomer(customer.id)">Delete</b-button>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import customerService from "@/services/customerService";

export default {
  name: "CustomerTable",
  data() {
    return {
      customer: {
        name: '',
        address: '',
        phoneNumber: '',
        fax: '',
        email: '',
        contactPerson: ''
      },
      customers: []
    }
  },
  created() {
    this.getCustomers()
  },
  methods: {
    async getCustomers() {
      const {data} = await customerService.getCustomers()
      this.customers = data
    },
    async createCustomer(event) {
      event.preventDefault()
      await customerService.createCustomer(this.customer)
      alert("Created customer success")
      window.location.reload()
    },
    async deleteCustomer(id) {
      await customerService.deleteCustomer(id)
      alert("Deleted customer success")
      window.location.reload()
    }
  }
}
</script>

<style scoped>

</style>