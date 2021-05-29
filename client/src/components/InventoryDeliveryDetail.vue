<template>
  <v-data-table
      :headers="headers"
      :items="deliveryDetails"
      class="elevation-1"
      hide-default-footer
  >
    <template v-slot:top>
      <v-toolbar
          flat
      >
        <v-toolbar-title>Inventory Delivery Detail</v-toolbar-title>
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
              New Inventory Delivery Detail
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
                        v-model="editedInventoryDeliveryDetail.product"
                        label="Product"
                    ></v-text-field>
                  </v-col>
                  <v-col
                      cols="12"
                      sm="6"
                      md="6"
                  >
                    <v-text-field
                        v-model="editedInventoryDeliveryDetail.quantity"
                        label="Quantity"
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
              <v-btn color="blue darken-1" text @click="deleteInventoryDeliveryConfirm">OK</v-btn>
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
          @click="editInventoryDelivery(item)"
      >
        mdi-pencil
      </v-icon>
      <v-icon
          small
          @click="deleteInventoryDelivery(item)"
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
import InventoryDeliveryFactory from '@/factories/InventoryDeliveryFactory'
import InventoryDeliveryDetailFactory from "@/factories/InventoryDeliveryDetailFactory";

export default {
  data: () => ({
    dialog: false,
    dialogDelete: false,
    headers: [
      {text: 'Id', value: 'id'},
      {text: 'Product', value: 'product'},
      {text: 'Quantity', value: 'quantity'},
      {text: 'Actions', value: 'actions', sortable: false},
    ],
    deliveryDetails: [],
    editedIndex: -1,
    editedInventoryDeliveryDetail: {
      id: '',
      product: '',
      quantity: ''
    },
    defaultInventoryDeliveryDetail: {
      id: '',
      product: '',
      quantity: ''
    },
  }),

  props: ['id'],

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? 'New Inventory Delivery Detail' : 'Edit Inventory Delivery Detail'
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
      const {data} = await InventoryDeliveryFactory.getInventoryDelivery(this.id)
      this.deliveryDetails = data.inventoryDeliveryDetail
    },

    editInventoryDelivery(item) {
      this.editedIndex = this.deliveryDetails.indexOf(item)
      this.editedInventoryDeliveryDetail = Object.assign({}, item)
      this.dialog = true
    },

    deleteInventoryDelivery(item) {
      this.editedIndex = this.deliveryDetails.indexOf(item)
      this.editedInventoryDeliveryDetail = Object.assign({}, item)
      this.dialogDelete = true
    },

    async deleteInventoryDeliveryConfirm() {
      this.deliveryDetails.splice(this.editedIndex, 1)
      await InventoryDeliveryDetailFactory.deleteInventoryDeliveryDetail(this.editedInventoryDeliveryDetail.id)
      this.closeDelete()
    },

    close() {
      this.dialog = false
      this.$nextTick(() => {
        this.editedInventoryDeliveryDetail = Object.assign({}, this.defaultInventoryDeliveryDetail)
        this.editedIndex = -1
      })
    },

    closeDelete() {
      this.dialogDelete = false
      this.$nextTick(() => {
        this.editedInventoryDeliveryDetail = Object.assign({}, this.defaultInventoryDeliveryDetail)
        this.editedIndex = -1
      })
    },

    async save() {
      if (this.editedIndex > -1) {
        await InventoryDeliveryDetailFactory.editInventoryDeliveryDetail(this.editedInventoryDeliveryDetail.id, this.editedInventoryDeliveryDetail)
        Object.assign(this.deliveryDetails[this.editedIndex], this.editedInventoryDeliveryDetail)
      } else {
        await InventoryDeliveryDetailFactory.createInventoryDeliveryDetail(this.id, this.editedInventoryDeliveryDetail)
        this.deliveryDetails.push(this.editedInventoryDeliveryDetail)
      }
      this.close()
    },
  },
}
</script>