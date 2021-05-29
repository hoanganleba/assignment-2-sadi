<template>
  <v-data-table
      :headers="headers"
      :items="receiveDetails"
      class="elevation-1"
      hide-default-footer
  >
    <template v-slot:top>
      <v-toolbar
          flat
      >
        <v-toolbar-title>Inventory Receive Detail</v-toolbar-title>
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
              New Inventory Receive Detail
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
                        v-model="editedInventoryReceiveDetail.product"
                        label="Product"
                    ></v-text-field>
                  </v-col>
                  <v-col
                      cols="12"
                      sm="6"
                      md="6"
                  >
                    <v-text-field
                        v-model="editedInventoryReceiveDetail.quantity"
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
              <v-btn color="blue darken-1" text @click="deleteInventoryReceiveConfirm">OK</v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>
    </template>
    <template  v-slot:item.details="{ item }">
      <router-link :to="{ name: 'InventoryReceiveDetail', params: { id: item.id } }">
        Details
      </router-link>
    </template>
    <template v-slot:item.actions="{ item }">
      <v-icon
          small
          class="mr-2"
          @click="editInventoryReceive(item)"
      >
        mdi-pencil
      </v-icon>
      <v-icon
          small
          @click="deleteInventoryReceive(item)"
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
import InventoryReceiveFactory from '@/factories/InventoryReceiveFactory'
import InventoryReceiveDetailFactory from "@/factories/InventoryReceiveDetailFactory";

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
    receiveDetails: [],
    editedIndex: -1,
    editedInventoryReceiveDetail: {
      id: '',
      product: '',
      quantity: ''
    },
    defaultInventoryReceiveDetail: {
      id: '',
      product: '',
      quantity: ''
    },
  }),

  props: ['id'],

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? 'New Inventory Receive Detail' : 'Edit Inventory Receive Detail'
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
      const {data} = await InventoryReceiveFactory.getInventoryReceive(this.id)
      this.receiveDetails = data.inventoryReceiveDetails
    },

    editInventoryReceive(item) {
      this.editedIndex = this.receiveDetails.indexOf(item)
      this.editedInventoryReceiveDetail = Object.assign({}, item)
      this.dialog = true
    },

    deleteInventoryReceive(item) {
      this.editedIndex = this.receiveDetails.indexOf(item)
      this.editedInventoryReceiveDetail = Object.assign({}, item)
      this.dialogDelete = true
    },

    async deleteInventoryReceiveConfirm() {
      this.receiveDetails.splice(this.editedIndex, 1)
      await InventoryReceiveDetailFactory.deleteInventoryReceiveDetail(this.editedInventoryReceiveDetail.id)
      this.closeDelete()
    },

    close() {
      this.dialog = false
      this.$nextTick(() => {
        this.editedInventoryReceiveDetail = Object.assign({}, this.defaultInventoryReceiveDetail)
        this.editedIndex = -1
      })
    },

    closeDelete() {
      this.dialogDelete = false
      this.$nextTick(() => {
        this.editedInventoryReceiveDetail = Object.assign({}, this.defaultInventoryReceiveDetail)
        this.editedIndex = -1
      })
    },

    async save() {
      if (this.editedIndex > -1) {
        await InventoryReceiveDetailFactory.editInventoryReceiveDetail(this.editedInventoryReceiveDetail.id, this.editedInventoryReceiveDetail)
        Object.assign(this.receiveDetails[this.editedIndex], this.editedInventoryReceiveDetail)
      } else {
        await InventoryReceiveDetailFactory.createInventoryReceiveDetail(this.id, this.editedInventoryReceiveDetail)
        this.receiveDetails.push(this.editedInventoryReceiveDetail)
      }
      this.close()
    },
  },
}
</script>