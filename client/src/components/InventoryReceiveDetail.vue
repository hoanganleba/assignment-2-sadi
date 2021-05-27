<template>
  <v-data-table
      :headers="headers"
      :items="receiveDetails"
      class="elevation-1"
  >
    <template v-slot:top>
      <v-toolbar
          flat
      >
        <v-toolbar-title>Inventory Receive</v-toolbar-title>
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
              New Inventory Receive
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
                        v-model="editedInventoryReceive.staff"
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
                        v-model="editedInventoryReceive.provider"
                        :items="providers"
                        label="Provider"
                        item-text="provider"
                    ></v-select>
                  </v-col>
                  <v-col
                      cols="12"
                      sm="12"
                      md="12"
                  >
                    <v-date-picker v-model="editedInventoryReceive.date"></v-date-picker>
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
      <v-btn
          color="primary"
          @click="initialize"
      >
        Reset
      </v-btn>
    </template>
  </v-data-table>
</template>

<script>
import InventoryReceiveFactory from '@/factories/InventoryReceiveFactory'
import StaffFactory from '@/factories/StaffFactory'
import ProviderFactory from "@/factories/ProviderFactory";

export default {
  data: () => ({
    dialog: false,
    dialogDelete: false,
    headers: [
      {text: 'Id', value: 'id'},
      {text: 'Date', value: 'date'},
      {text: 'Staff', value: 'staff'},
      {text: 'Provider', value: 'provider'},
      {text: 'Details', value: 'details'},
      {text: 'Actions', value: 'actions', sortable: false},
    ],
    receiveDetails: [],
    staffs: [],
    providers: [],
    date: '',
    editedIndex: -1,
    editedInventoryReceive: {
      id: '',
      date: '',
      staff: '',
      provider: ''
    },
    defaultInventoryReceive: {
      id: '',
      date: '',
      staff: '',
      provider: ''
    },
  }),

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? 'New InventoryReceive' : 'Edit InventoryReceive'
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
      const receiveDetails = await InventoryReceiveFactory.getAllInventoryDeliveries()
      const staffs = await StaffFactory.getAllStaffs()
      const providers = await ProviderFactory.getAllProviders()
      this.receiveDetails = receiveDetails.data
      this.staffs = staffs.data
      this.providers = providers.data
    },

    editInventoryReceive(item) {
      this.editedIndex = this.receiveDetails.indexOf(item)
      this.editedInventoryReceive = Object.assign({}, item)
      this.dialog = true
    },

    deleteInventoryReceive(item) {
      this.editedIndex = this.receiveDetails.indexOf(item)
      this.editedInventoryReceive = Object.assign({}, item)
      this.dialogDelete = true
    },

    async deleteInventoryReceiveConfirm() {
      this.receiveDetails.splice(this.editedIndex, 1)
      await InventoryReceiveFactory.deleteInventoryReceive(this.editedInventoryReceive.id)
      this.closeDelete()
    },

    close() {
      this.dialog = false
      this.$nextTick(() => {
        this.editedInventoryReceive = Object.assign({}, this.defaultInventoryReceive)
        this.editedIndex = -1
      })
    },

    closeDelete() {
      this.dialogDelete = false
      this.$nextTick(() => {
        this.editedInventoryReceive = Object.assign({}, this.defaultInventoryReceive)
        this.editedIndex = -1
      })
    },

    async save() {
      if (this.editedIndex > -1) {
        await InventoryReceiveFactory.editInventoryReceive(this.editedInventoryReceive.id, this.editedInventoryReceive)
        Object.assign(this.receiveDetails[this.editedIndex], this.editedInventoryReceive)
      } else {
        console.log(this.editedInventoryReceive)
        await InventoryReceiveFactory.createInventoryReceive(this.editedInventoryReceive)
        this.receiveDetails.push(this.editedInventoryReceive)
      }
      this.close()
    },
  },
}
</script>