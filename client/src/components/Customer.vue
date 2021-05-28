<template>
  <v-data-table
      :headers="headers"
      :items="customers"
      class="elevation-1"
  >
    <template v-slot:top>
      <v-toolbar
          flat
          height="120px"
      >
        <v-toolbar-title>Customer</v-toolbar-title>
        <v-divider
            class="mx-4"
            inset
            vertical
        ></v-divider>
        <div class="d-flex align-items-center">
          <v-text-field
              class="mx-1"
              outlined
              v-model="search"
              label="Search"
          ></v-text-field>
          <v-select
              class="mx-1"
              outlined
              v-model="select"
              :items="filters"
              label="Filter"
          ></v-select>
          <v-btn class="mt-2 ml-3" @click="searchCustomer">Search</v-btn>
          <v-btn class="mt-2 ml-3" @click="reset">Reset</v-btn>
        </div>
        <v-spacer></v-spacer>
        <v-dialog
            v-model="dialog"
            max-width="500px"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-btn
                color="primary"
                dark
                class="mb-2"
                v-bind="attrs"
                v-on="on"
            >
              New Customer
            </v-btn>
          </template>
          <v-card>
            <v-card-title>
              <span class="headline">{{ formTitle }}</span>
            </v-card-title>

            <v-card-text>
              <v-container>
                <v-row>
                  <v-col
                      cols="12"
                      sm="6"
                      md="4"
                  >
                    <v-text-field
                        v-model="editedCustomer.name"
                        label="Customer Name"
                    ></v-text-field>
                  </v-col>
                  <v-col
                      cols="12"
                      sm="6"
                      md="4"
                  >
                    <v-text-field
                        v-model="editedCustomer.address"
                        label="Address"
                    ></v-text-field>
                  </v-col>
                  <v-col
                      cols="12"
                      sm="6"
                      md="4"
                  >
                    <v-text-field
                        v-model="editedCustomer.phoneNumber"
                        label="Phone Number"
                    ></v-text-field>
                  </v-col>
                  <v-col
                      cols="12"
                      sm="6"
                      md="4"
                  >
                    <v-text-field
                        v-model="editedCustomer.fax"
                        label="Fax"
                    ></v-text-field>
                  </v-col>
                  <v-col
                      cols="12"
                      sm="6"
                      md="4"
                  >
                    <v-text-field
                        v-model="editedCustomer.email"
                        label="Email"
                    ></v-text-field>
                  </v-col>
                  <v-col
                      cols="12"
                      sm="6"
                      md="4"
                  >
                    <v-text-field
                        v-model="editedCustomer.contactPerson"
                        label="Contact Person"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                  color="blue darken-1"
                  text
                  @click="close"
              >
                Cancel
              </v-btn>
              <v-btn
                  color="blue darken-1"
                  text
                  @click="save"
              >
                Save
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
        <v-dialog v-model="dialogDelete" max-width="500px">
          <v-card>
            <v-card-title class="headline">Are you sure you want to delete this item?</v-card-title>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="closeDelete">Cancel</v-btn>
              <v-btn color="blue darken-1" text @click="deleteCustomerConfirm">OK</v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>
    </template>
    <template v-slot:item.actions="{ item }">
      <v-icon
          small
          class="mr-2"
          @click="editCustomer(item)"
      >
        mdi-pencil
      </v-icon>
      <v-icon
          small
          @click="deleteCustomer(item)"
      >
        mdi-delete
      </v-icon>
    </template>
    <template v-slot:no-data>
      No Data
    </template>
  </v-data-table>
</template>

<script>
import CustomerFactory from '@/factories/CustomerFactory'

export default {
  data: () => ({
    dialog: false,
    dialogDelete: false,
    headers: [
      {
        text: 'Name',
        align: 'start',
        sortable: false,
        value: 'name',
      },
      {text: 'Address', value: 'address'},
      {text: 'Phone Number', value: 'phoneNumber'},
      {text: 'Fax', value: 'fax'},
      {text: 'Email', value: 'email'},
      {text: 'Contact Person', value: 'contactPerson'},
      {text: 'Actions', value: 'actions', sortable: false},
    ],
    customers: [],
    filters: ['Name', 'Address', 'Phone Number'],
    select: '',
    editedIndex: -1,
    search: '',
    searchType: '',
    editedCustomer: {
      id: '',
      name: '',
      address: '',
      phoneNumber: '',
      fax: '',
      email: '',
      contactPerson: ''
    },
    defaultCustomer: {
      id: '',
      name: '',
      address: '',
      phoneNumber: '',
      fax: '',
      email: '',
      contactPerson: ''
    },
  }),

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? 'New Customer' : 'Edit Customer'
    },
  },

  watch: {
    dialog(val) {
      val || this.close()
    },
    dialogDelete(val) {
      val || this.closeDelete()
    },
  },

  created() {
    this.initialize()
  },

  methods: {
    async initialize() {
      const {data} = await CustomerFactory.getAllCustomers()
      this.customers = data
    },

    editCustomer(item) {
      this.editedIndex = this.customers.indexOf(item)
      this.editedCustomer = Object.assign({}, item)
      this.dialog = true
    },

    deleteCustomer(item) {
      this.editedIndex = this.customers.indexOf(item)
      this.editedCustomer = Object.assign({}, item)
      this.dialogDelete = true
    },

    async reset() {
      const customers = await CustomerFactory.getAllCustomers()
      return this.customers = customers.data
    },

    async searchCustomer() {
      switch (this.select) {
        case 'Name': {
          const {data} = await CustomerFactory.searchCustomerName(this.search)
          this.customers = data
          break;
        }
        case 'Address': {
          const {data} = await CustomerFactory.searchCustomerAddress(this.search)
          this.customers = data
          break;
        }
        case 'PhoneNumber': {
          const {data} = await CustomerFactory.searchCustomerPhoneNumber(this.search)
          this.customers = data
          break;
        }
        default: {
          const customers = await CustomerFactory.getAllCustomers()
          return this.customers = customers.data
        }
      }
    },

    async deleteCustomerConfirm() {
      this.customers.splice(this.editedIndex, 1)
      await CustomerFactory.deleteCustomer(this.editedCustomer.id)
      this.closeDelete()
    },

    close() {
      this.dialog = false
      this.$nextTick(() => {
        this.editedCustomer = Object.assign({}, this.defaultCustomer)
        this.editedIndex = -1
      })
    },

    closeDelete() {
      this.dialogDelete = false
      this.$nextTick(() => {
        this.editedCustomer = Object.assign({}, this.defaultCustomer)
        this.editedIndex = -1
      })
    },

    async save() {
      if (this.editedIndex > -1) {
        await CustomerFactory.editCustomer(this.editedCustomer.id, this.editedCustomer)
        Object.assign(this.customers[this.editedIndex], this.editedCustomer)
      } else {
        await CustomerFactory.createCustomer(this.editedCustomer)
        this.customers.push(this.editedCustomer)
      }
      this.close()
    },
  },
}
</script>