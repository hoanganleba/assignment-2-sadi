<template>
  <v-data-table
      :headers="headers"
      :items="saleDetails"
      class="elevation-1"
  >
    <template v-slot:top>
      <v-toolbar
          flat
      >
        <v-toolbar-title>Sale Detail</v-toolbar-title>
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
              New Sale Detail
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
                    <v-text-field
                        v-model="editedSaleDetail.product"
                        label="Product"
                    ></v-text-field>
                  </v-col>
                  <v-col
                      cols="12"
                      sm="6"
                      md="6"
                  >
                    <v-text-field
                        v-model="editedSaleDetail.quantity"
                        label="Quantity"
                    ></v-text-field>
                  </v-col>
                  <v-col
                      cols="12"
                      sm="12"
                      md="12"
                  >
                    <v-text-field
                        v-model="editedSaleDetail.price"
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
              <v-btn color="blue darken-1" text @click="deleteSaleDetailConfirm">OK</v-btn>
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
          @click="editSaleDetail(item)"
      >
        mdi-pencil
      </v-icon>
      <v-icon
          small
          @click="deleteSaleDetail(item)"
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
import SaleDetailFactory from '@/factories/SaleDetailFactory'
import ProviderFactory from "@/factories/ProviderFactory";
import SaleFactory from "@/factories/SaleFactory";

export default {
  data: () => ({
    dialog: false,
    dialogDelete: false,
    headers: [
      {text: 'Id', value: 'id'},
      {text: 'Product', value: 'product'},
      {text: 'Quantity', value: 'quantity'},
      {text: 'Price', value: 'price'},
      {text: 'Actions', value: 'actions', sortable: false},
    ],
    saleDetails: [],
    editedIndex: -1,
    editedSaleDetail: {
      id: '',
      product: '',
      quantity: '',
      price: ''
    },
    defaultSaleDetail: {
      id: '',
      product: '',
      quantity: '',
      price: ''
    },
  }),

  props: ['id'],

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? 'New Sale Detail' : 'Edit Sale Detail'
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
      const {data} = await SaleFactory.getSale(this.id)
      const providers = await ProviderFactory.getAllProviders()
      this.saleDetails = data.saleDetails
      this.providers = providers.data
    },

    editSaleDetail(item) {
      this.editedIndex = this.saleDetails.indexOf(item)
      this.editedSaleDetail = Object.assign({}, item)
      this.dialog = true
    },

    deleteSaleDetail(item) {
      this.editedIndex = this.saleDetails.indexOf(item)
      this.editedSaleDetail = Object.assign({}, item)
      this.dialogDelete = true
    },

    async deleteSaleDetailConfirm() {
      this.saleDetails.splice(this.editedIndex, 1)
      await SaleDetailFactory.deleteSaleDetail(this.editedSaleDetail.id)
      this.closeDelete()
    },

    close() {
      this.dialog = false
      this.$nextTick(() => {
        this.editedSaleDetail = Object.assign({}, this.defaultSaleDetail)
        this.editedIndex = -1
      })
    },

    closeDelete() {
      this.dialogDelete = false
      this.$nextTick(() => {
        this.editedSaleDetail = Object.assign({}, this.defaultSaleDetail)
        this.editedIndex = -1
      })
    },

    async save() {
      if (this.editedIndex > -1) {
        await SaleDetailFactory.editSaleDetail(this.editedSaleDetail.id, this.editedSaleDetail)
        Object.assign(this.saleDetails[this.editedIndex], this.editedSaleDetail)
      } else {
        console.log(this.editedSaleDetail)
        await SaleDetailFactory.createSaleDetail(this.editedSaleDetail)
        this.saleDetails.push(this.editedSaleDetail)
      }
      this.close()
    },
  },
}
</script>