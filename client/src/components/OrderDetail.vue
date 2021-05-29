<template>
  <v-data-table
      :headers="headers"
      :items="orderDetails"
      class="elevation-1"
      hide-default-footer
  >
    <template v-slot:top>
      <v-toolbar
          flat
      >
        <v-toolbar-title>Order Detail</v-toolbar-title>
        <v-divider
            class="mx-4"
            inset
            vertical
        ></v-divider>
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
              New Order Detail
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
                        v-model="editedOrderDetail.product"
                        label="Product Name"
                    ></v-text-field>
                  </v-col>
                  <v-col
                      cols="12"
                      sm="6"
                      md="4"
                  >
                    <v-text-field
                        v-model="editedOrderDetail.quantity"
                        label="Quantity"
                    ></v-text-field>
                  </v-col>
                  <v-col
                      cols="12"
                      sm="6"
                      md="4"
                  >
                    <v-text-field
                        v-model="editedOrderDetail.price"
                        label="Price"
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
              <v-btn color="blue darken-1" text @click="deleteOrderDetailConfirm">OK</v-btn>
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
          @click="editOrderDetail(item)"
      >
        mdi-pencil
      </v-icon>
      <v-icon
          small
          @click="deleteOrderDetail(item)"
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
import OrderFactory from '@/factories/OrderFactory'
import OrderDetailFactory from '@/factories/OrderDetailFactory'

export default {
  data: () => ({
    dialog: false,
    dialogDelete: false,
    headers: [
      {text: 'Product Name', value: 'product'},
      {text: 'Quantity', value: 'quantity'},
      {text: 'Price', value: 'price'},
      {text: 'Actions', value: 'actions', sortable: false},
    ],
    orderDetails: [],
    editedIndex: -1,
    editedOrderDetail: {
      id: '',
      product: '',
      quantity: '',
      price: '',
    },
    defaultOrderDetail: {
      id: '',
      product: '',
      quantity: '',
      price: '',
    },
  }),

  props: ['id'],

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? 'New Order Detail' : 'Edit Order Detail'
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
      const {data} = await OrderFactory.getOrder(this.id)
      this.orderDetails = data.orderDetails
    },

    editOrderDetail(item) {
      this.editedIndex = this.orderDetails.indexOf(item)
      this.editedOrderDetail = Object.assign({}, item)
      this.dialog = true
    },

    deleteOrderDetail(item) {
      this.editedIndex = this.orderDetails.indexOf(item)
      this.editedOrderDetail = Object.assign({}, item)
      this.dialogDelete = true
    },

    async deleteOrderDetailConfirm() {
      this.orderDetails.splice(this.editedIndex, 1)
      await OrderDetailFactory.deleteOrderDetail(this.editedOrderDetail.id)
      this.closeDelete()
    },

    close() {
      this.dialog = false
      this.$nextTick(() => {
        this.editedOrderDetail = Object.assign({}, this.defaultOrderDetail)
        this.editedIndex = -1
      })
    },

    closeDelete() {
      this.dialogDelete = false
      this.$nextTick(() => {
        this.editedOrderDetail = Object.assign({}, this.defaultOrderDetail)
        this.editedIndex = -1
      })
    },

    async save() {
      if (this.editedIndex > -1) {
        await OrderDetailFactory.editOrderDetail(this.editedOrderDetail.id, this.editedOrderDetail)
        Object.assign(this.orderDetails[this.editedIndex], this.editedOrderDetail)
      } else {
        await OrderDetailFactory.createOrderDetail(this.id, this.editedOrderDetail)
        this.orderDetails.push(this.editedOrderDetail)
      }
      this.close()
    },
  },
}
</script>