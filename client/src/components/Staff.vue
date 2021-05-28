<template>
  <v-data-table
      :headers="headers"
      :items="staffs"
      class="elevation-1"
  >
    <template v-slot:top>
      <v-toolbar
          flat
      >
        <v-toolbar-title>Staff</v-toolbar-title>
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
              New Staff
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
                        v-model="editedStaff.name"
                        label="Name"
                    ></v-text-field>
                  </v-col>
                  <v-col
                      cols="12"
                      sm="6"
                      md="4"
                  >
                    <v-text-field
                        v-model="editedStaff.address"
                        label="Address"
                    ></v-text-field>
                  </v-col>
                  <v-col
                      cols="12"
                      sm="6"
                      md="4"
                  >
                    <v-text-field
                        v-model="editedStaff.phoneNumber"
                        label="Phone Number"
                    ></v-text-field>
                  </v-col>
                  <v-col
                      cols="12"
                      sm="6"
                      md="4"
                  >
                    <v-text-field
                        v-model="editedStaff.email"
                        label="Email"
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
              <v-btn color="blue darken-1" text @click="deleteStaffConfirm">OK</v-btn>
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
          @click="editStaff(item)"
      >
        mdi-pencil
      </v-icon>
      <v-icon
          small
          @click="deleteStaff(item)"
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
import StaffFactory from '@/factories/StaffFactory'

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
      {text: 'Email', value: 'email'},
      {text: 'Actions', value: 'actions', sortable: false},
    ],
    staffs: [],
    editedIndex: -1,
    editedStaff: {
      id: '',
      name: '',
      address: '',
      phoneNumber: '',
      email: '',
    },
    defaultStaff: {
      id: '',
      name: '',
      address: '',
      phoneNumber: '',
      email: '',
    },
  }),

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? 'New Staff' : 'Edit Staff'
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
      const {data} = await StaffFactory.getAllStaffs()
      this.staffs = data
    },

    editStaff(item) {
      this.editedIndex = this.staffs.indexOf(item)
      this.editedStaff = Object.assign({}, item)
      this.dialog = true
    },

    deleteStaff(item) {
      this.editedIndex = this.staffs.indexOf(item)
      this.editedStaff = Object.assign({}, item)
      this.dialogDelete = true
    },

    async deleteStaffConfirm() {
      this.staffs.splice(this.editedIndex, 1)
      await StaffFactory.deleteStaff(this.editedStaff.id)
      this.closeDelete()
    },

    close() {
      this.dialog = false
      this.$nextTick(() => {
        this.editedStaff = Object.assign({}, this.defaultStaff)
        this.editedIndex = -1
      })
    },

    closeDelete() {
      this.dialogDelete = false
      this.$nextTick(() => {
        this.editedStaff = Object.assign({}, this.defaultStaff)
        this.editedIndex = -1
      })
    },

    async save() {
      if (this.editedIndex > -1) {
        await StaffFactory.editStaff(this.editedStaff.id, this.editedStaff)
        Object.assign(this.staffs[this.editedIndex], this.editedStaff)
      } else {
        await StaffFactory.createStaff(this.editedStaff)
        this.staffs.push(this.editedStaff)
      }
      this.close()
    },
  },
}
</script>