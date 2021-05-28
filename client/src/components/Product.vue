<template>
  <v-data-table
      :headers="headers"
      :items="products"
      class="elevation-1"
  >
    <template v-slot:top>
      <v-toolbar
          flat
      >
        <v-toolbar-title>Product</v-toolbar-title>
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
              New Product
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
                        v-model="editedProduct.name"
                        label="Product Name"
                    ></v-text-field>
                  </v-col>
                  <v-col
                      cols="12"
                      sm="6"
                      md="4"
                  >
                    <v-text-field
                        v-model="editedProduct.model"
                        label="Model"
                    ></v-text-field>
                  </v-col>
                  <v-col
                      cols="12"
                      sm="6"
                      md="4"
                  >
                    <v-text-field
                        v-model="editedProduct.brand"
                        label="Brand"
                    ></v-text-field>
                  </v-col>
                  <v-col
                      cols="12"
                      sm="6"
                      md="4"
                  >
                    <v-text-field
                        v-model="editedProduct.company"
                        label="Company"
                    ></v-text-field>
                  </v-col>
                  <v-col
                      cols="12"
                      sm="6"
                      md="4"
                  >
                    <v-text-field
                        v-model="editedProduct.description"
                        label="Description"
                    ></v-text-field>
                  </v-col>
                  <v-col
                      cols="12"
                      sm="6"
                      md="4"
                  >
                    <v-text-field
                        v-model="editedProduct.price"
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
              <v-btn color="blue darken-1" text @click="deleteProductConfirm">OK</v-btn>
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
          @click="editProduct(item)"
      >
        mdi-pencil
      </v-icon>
      <v-icon
          small
          @click="deleteProduct(item)"
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
import ProductFactory from '@/factories/ProductFactory'

export default {
  data: () => ({
    dialog: false,
    dialogDelete: false,
    headers: [
      {text: 'Name', value: 'name',},
      {text: 'Model', value: 'model'},
      {text: 'Brand', value: 'brand'},
      {text: 'Company', value: 'company'},
      {text: 'Description', value: 'description'},
      {text: 'Category', value: 'category'},
      {text: 'Price', value: 'price'},
      {text: 'Actions', value: 'actions', sortable: false},
    ],
    products: [],
    editedIndex: -1,
    editedProduct: {
      id: '',
      name: '',
      model: '',
      brand: '',
      company: '',
      description: '',
      category: '',
      price: ''
    },
    defaultProduct: {
      id: '',
      name: '',
      model: '',
      brand: '',
      company: '',
      description: '',
      category: '',
      price: ''
    },
  }),

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? 'New Product' : 'Edit Product'
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
      const {data} = await ProductFactory.getAllProducts()
      this.products = data
    },

    editProduct(item) {
      this.editedIndex = this.products.indexOf(item)
      this.editedProduct = Object.assign({}, item)
      this.dialog = true
    },

    deleteProduct(item) {
      this.editedIndex = this.products.indexOf(item)
      this.editedProduct = Object.assign({}, item)
      this.dialogDelete = true
    },

    async deleteProductConfirm() {
      this.products.splice(this.editedIndex, 1)
      await ProductFactory.deleteProduct(this.editedProduct.id)
      this.closeDelete()
    },

    close() {
      this.dialog = false
      this.$nextTick(() => {
        this.editedProduct = Object.assign({}, this.defaultProduct)
        this.editedIndex = -1
      })
    },

    closeDelete() {
      this.dialogDelete = false
      this.$nextTick(() => {
        this.editedProduct = Object.assign({}, this.defaultProduct)
        this.editedIndex = -1
      })
    },

    async save() {
      if (this.editedIndex > -1) {
        await ProductFactory.editProduct(this.editedProduct.id, this.editedProduct)
        Object.assign(this.products[this.editedIndex], this.editedProduct)
      } else {
        await ProductFactory.createProduct(this.editedProduct)
        this.products.push(this.editedProduct)
      }
      this.close()
    },
  },
}
</script>