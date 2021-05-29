<template>
  <v-data-table
      :headers="headers"
      :items="receives"
      class="elevation-1"
      hide-default-footer
  >
    <template v-slot:top>
      <v-toolbar
          flat
          height="120px"
      >
        <v-toolbar-title>Inventory Receive</v-toolbar-title>
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
                    <v-text-field
                        v-model="editedInventoryReceive.name"
                        label="Name"
                    ></v-text-field>
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
      No Data
    </template>
  </v-data-table>
</template>

<script>
import InventoryReceiveFactory from '@/factories/InventoryReceiveFactory'

export default {
  data: () => ({
    dialog: false,
    dialogDelete: false,
    headers: [
      {text: 'Id', value: 'id'},
      {text: 'Date', value: 'date'},
      {text: 'Name', value: 'name'},
      {text: 'Details', value: 'details'},
      {text: 'Actions', value: 'actions', sortable: false},
    ],
    menu: false,
    menu1: false,
    receives: [],
    startDate: '',
    endDate: '',
    editedIndex: -1,
    editedInventoryReceive: {
      id: '',
      date: '',
      name: ''
    },
    defaultInventoryReceive: {
      id: '',
      date: '',
      name: ''
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
      const receives = await InventoryReceiveFactory.getAllInventoryReceives()
      this.receives = receives.data
    },

    async filterTime() {
      const receives = await InventoryReceiveFactory.getAllInventoryReceivesByPeriod(this.startDate, this.endDate)
      this.receives = receives.data
    },

    async reset() {
      const receives = await InventoryReceiveFactory.getAllInventoryReceives()
      this.receives = receives.data
    },

    editInventoryReceive(item) {
      this.editedIndex = this.receives.indexOf(item)
      this.editedInventoryReceive = Object.assign({}, item)
      this.dialog = true
    },

    deleteInventoryReceive(item) {
      this.editedIndex = this.receives.indexOf(item)
      this.editedInventoryReceive = Object.assign({}, item)
      this.dialogDelete = true
    },

    async deleteInventoryReceiveConfirm() {
      this.receives.splice(this.editedIndex, 1)
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
        Object.assign(this.receives[this.editedIndex], this.editedInventoryReceive)
      } else {
        console.log(this.editedInventoryReceive)
        await InventoryReceiveFactory.createInventoryReceive(this.editedInventoryReceive)
        this.receives.push(this.editedInventoryReceive)
      }
      this.close()
    },
  },
}
</script>