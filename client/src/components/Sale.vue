<template>
  <v-data-table
      :headers="headers"
      :items="sales"
      class="elevation-1"
      hide-default-footer
  >
    <template v-slot:top>
      <v-toolbar
          flat
          height="120px"
      >
        <v-toolbar-title>Sale</v-toolbar-title>
        <v-divider
            class="mx-4"
            inset
            vertical
        ></v-divider>
        <div class="d-flex align-items-center">
          <v-menu
              ref="menu"
              v-model="menu"
              :close-on-content-click="false"
              :return-value.sync="startDate"
              transition="scale-transition"
              offset-y
              min-width="auto"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-text-field
                  v-model="startDate"
                  label="Start Date"
                  outlined
                  readonly
                  v-bind="attrs"
                  v-on="on"
              ></v-text-field>
            </template>
            <v-date-picker
                v-model="startDate"
                no-title
                scrollable
            >
              <v-spacer></v-spacer>
              <v-btn
                  text
                  color="primary"
                  @click="menu = false"
              >
                Cancel
              </v-btn>
              <v-btn
                  text
                  color="primary"
                  @click="$refs.menu.save(startDate)"
              >
                OK
              </v-btn>
            </v-date-picker>
          </v-menu>
          <v-menu
              ref="menu1"
              v-model="menu1"
              :close-on-content-click="false"
              :return-value.sync="endDate"
              transition="scale-transition"
              offset-y
              min-width="auto"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-text-field
                  v-model="endDate"
                  label="End Date"
                  outlined
                  readonly
                  v-bind="attrs"
                  v-on="on"
              ></v-text-field>
            </template>
            <v-date-picker
                v-model="endDate"
                no-title
                scrollable
            >
              <v-spacer></v-spacer>
              <v-btn
                  text
                  color="primary"
                  @click="menu1 = false"
              >
                Cancel
              </v-btn>
              <v-btn
                  text
                  color="primary"
                  @click="$refs.menu1.save(endDate)"
              >
                OK
              </v-btn>
            </v-date-picker>
          </v-menu>
          <v-btn class="mt-2 ml-3" @click="filterTime">Filter</v-btn>
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
              New Sale
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
                      md="6"
                  >
                    <v-select
                        v-model="editedSale.staff"
                        :items="staffs"
                        label="Staff"
                        item-text="staff"
                    ></v-select>
                  </v-col>
                  <v-col
                      cols="12"
                      sm="6"
                      md="6"
                  >
                    <v-select
                        v-model="editedSale.customer"
                        :items="customers"
                        label="Customer"
                        item-text="customer"
                    ></v-select>
                  </v-col>
                  <v-col
                      cols="12"
                      sm="6"
                      md="6"
                  >
                    <v-text-field
                        v-model="editedSale.totalPrice"
                        label="Total Price"
                    ></v-text-field>
                  </v-col>
                  <v-col
                      cols="12"
                      sm="12"
                      md="12"
                  >
                    <v-date-picker v-model="editedSale.date"></v-date-picker>
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
              <v-btn color="blue darken-1" text @click="deleteSaleConfirm">OK</v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>
    </template>
    <template  v-slot:item.details="{ item }">
      <router-link :to="{ name: 'SaleDetail', params: { id: item.id } }">
        Details
      </router-link>
    </template>
    <template v-slot:item.actions="{ item }">
      <v-icon
          small
          class="mr-2"
          @click="editSale(item)"
      >
        mdi-pencil
      </v-icon>
      <v-icon
          small
          @click="deleteSale(item)"
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
import SaleFactory from '@/factories/SaleFactory'
import StaffFactory from '@/factories/StaffFactory'
import CustomerFactory from "@/factories/CustomerFactory";

export default {
  data: () => ({
    dialog: false,
    dialogDelete: false,
    headers: [
      {text: 'Id', value: 'id'},
      {text: 'Date', value: 'date'},
      {text: 'Staff', value: 'staff.name'},
      {text: 'Customer', value: 'customer.name'},
      {text: 'Total Price', value: 'totalPrice'},
      {text: 'Details', value: 'details'},
      {text: 'Actions', value: 'actions', sortable: false},
    ],
    sales: [],
    staffs: [],
    customers: [],
    menu: false,
    menu1: false,
    startDate: '',
    endDate: '',
    editedIndex: -1,
    editedSale: {
      id: '',
      date: '',
      staff: '',
      customer: '',
      totalPrice: ''
    },
    defaultSale: {
      id: '',
      date: '',
      staff: '',
      customer: '',
      totalPrice: ''
    },
  }),

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? 'New Sale' : 'Edit Sale'
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
      const sales = await SaleFactory.getAllSales()
      const staffs = await StaffFactory.getAllStaffs()
      const customers = await CustomerFactory.getAllCustomers()
      this.sales = sales.data
      this.staffs = staffs.data
      this.customers = customers.data
    },

    async filterTime() {
      const sales = await SaleFactory.getAllSalesByPeriod(this.startDate, this.endDate)
      this.sales = sales.data
    },

    async reset() {
      const sales = await SaleFactory.getAllSales()
      this.sales = sales.data
    },

    editSale(item) {
      this.editedIndex = this.sales.indexOf(item)
      this.editedSale = Object.assign({}, item)
      this.dialog = true
    },

    deleteSale(item) {
      this.editedIndex = this.sales.indexOf(item)
      this.editedSale = Object.assign({}, item)
      this.dialogDelete = true
    },

    async deleteSaleConfirm() {
      this.sales.splice(this.editedIndex, 1)
      await SaleFactory.deleteSale(this.editedSale.id)
      this.closeDelete()
    },

    close() {
      this.dialog = false
      this.$nextTick(() => {
        this.editedSale = Object.assign({}, this.defaultSale)
        this.editedIndex = -1
      })
    },

    closeDelete() {
      this.dialogDelete = false
      this.$nextTick(() => {
        this.editedSale = Object.assign({}, this.defaultSale)
        this.editedIndex = -1
      })
    },

    async save() {
      if (this.editedIndex > -1) {
        await SaleFactory.editSale(this.editedSale.id, this.editedSale)
        Object.assign(this.sales[this.editedIndex], this.editedSale)
      } else {
        console.log(this.editedSale)
        await SaleFactory.createSale(this.editedSale)
        this.sales.push(this.editedSale)
      }
      this.close()
    },
  },
}
</script>