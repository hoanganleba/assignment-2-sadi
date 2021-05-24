<template>
  <v-data-table
      :headers="headers"
      :items="providers"
      sort-by="calories"
      class="elevation-1"
  >
    <template v-slot:top>
      <v-toolbar
          flat
      >
        <v-toolbar-title>Provider</v-toolbar-title>
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
              New Provider
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
                        v-model="editedProvider.name"
                        label="Provider Name"
                    ></v-text-field>
                  </v-col>
                  <v-col
                      cols="12"
                      sm="6"
                      md="4"
                  >
                    <v-text-field
                        v-model="editedProvider.address"
                        label="Address"
                    ></v-text-field>
                  </v-col>
                  <v-col
                      cols="12"
                      sm="6"
                      md="4"
                  >
                    <v-text-field
                        v-model="editedProvider.phoneNumber"
                        label="Phone Number"
                    ></v-text-field>
                  </v-col>
                  <v-col
                      cols="12"
                      sm="6"
                      md="4"
                  >
                    <v-text-field
                        v-model="editedProvider.fax"
                        label="Fax"
                    ></v-text-field>
                  </v-col>
                  <v-col
                      cols="12"
                      sm="6"
                      md="4"
                  >
                    <v-text-field
                        v-model="editedProvider.email"
                        label="Email"
                    ></v-text-field>
                  </v-col>
                  <v-col
                      cols="12"
                      sm="6"
                      md="4"
                  >
                    <v-text-field
                        v-model="editedProvider.contactPerson"
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
              <v-btn color="blue darken-1" text @click="deleteProviderConfirm">OK</v-btn>
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
          @click="editProvider(item)"
      >
        mdi-pencil
      </v-icon>
      <v-icon
          small
          @click="deleteProvider(item)"
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
import ProviderFactory from '@/factories/ProviderFactory'

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
    providers: [],
    editedIndex: -1,
    editedProvider: {
      id: '',
      name: '',
      address: '',
      phoneNumber: '',
      fax: '',
      email: '',
      contactPerson: ''
    },
    defaultProvider: {
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
      return this.editedIndex === -1 ? 'New Provider' : 'Edit Provider'
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
      const {data} = await ProviderFactory.getAllProviders()
      this.providers = data
    },

    editProvider(item) {
      this.editedIndex = this.providers.indexOf(item)
      this.editedProvider = Object.assign({}, item)
      this.dialog = true
    },

    deleteProvider(item) {
      this.editedIndex = this.providers.indexOf(item)
      this.editedProvider = Object.assign({}, item)
      this.dialogDelete = true
    },

    async deleteProviderConfirm() {
      this.providers.splice(this.editedIndex, 1)
      await ProviderFactory.deleteProvider(this.editedProvider.id)
      this.closeDelete()
    },

    close() {
      this.dialog = false
      this.$nextTick(() => {
        this.editedProvider = Object.assign({}, this.defaultProvider)
        this.editedIndex = -1
      })
    },

    closeDelete() {
      this.dialogDelete = false
      this.$nextTick(() => {
        this.editedProvider = Object.assign({}, this.defaultProvider)
        this.editedIndex = -1
      })
    },

    async save() {
      if (this.editedIndex > -1) {
        await ProviderFactory.editProvider(this.editedProvider.id, this.editedProvider)
        Object.assign(this.providers[this.editedIndex], this.editedProvider)
      } else {
        await ProviderFactory.createProvider(this.editedProvider)
        this.providers.push(this.editedProvider)
      }
      this.close()
    },
  },
}
</script>