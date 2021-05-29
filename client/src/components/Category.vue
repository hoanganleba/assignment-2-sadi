<template>
  <v-data-table
      :headers="headers"
      :items="categories"
      class="elevation-1"
      hide-default-footer
  >
    <template v-slot:top>
      <v-toolbar
          flat
      >
        <v-toolbar-title>Category</v-toolbar-title>
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
              New Category
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
                        v-model="editedCategory.name"
                        label="Category Name"
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
              <v-btn color="blue darken-1" text @click="deleteCategoryConfirm">OK</v-btn>
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
          @click="editCategory(item)"
      >
        mdi-pencil
      </v-icon>
      <v-icon
          small
          @click="deleteCategory(item)"
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
import CategoryFactory from '@/factories/CategoryFactory'

export default {
  data: () => ({
    dialog: false,
    dialogDelete: false,
    headers: [
      {text: 'Name', value: 'name',},
      {text: 'Actions', value: 'actions', sortable: false},
    ],
    categories: [],
    editedIndex: -1,
    editedCategory: {
      id: '',
      name: '',
    },
    defaultCategory: {
      id: '',
      name: '',
    },
  }),

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? 'New Category' : 'Edit Category'
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
      const {data} = await CategoryFactory.getAllCategories()
      this.categories = data
    },

    editCategory(item) {
      this.editedIndex = this.categories.indexOf(item)
      this.editedCategory = Object.assign({}, item)
      this.dialog = true
    },

    deleteCategory(item) {
      this.editedIndex = this.categories.indexOf(item)
      this.editedCategory = Object.assign({}, item)
      this.dialogDelete = true
    },

    async deleteCategoryConfirm() {
      this.categories.splice(this.editedIndex, 1)
      await CategoryFactory.deleteCategory(this.editedCategory.id)
      this.closeDelete()
    },

    close() {
      this.dialog = false
      this.$nextTick(() => {
        this.editedCategory = Object.assign({}, this.defaultCategory)
        this.editedIndex = -1
      })
    },

    closeDelete() {
      this.dialogDelete = false
      this.$nextTick(() => {
        this.editedCategory = Object.assign({}, this.defaultCategory)
        this.editedIndex = -1
      })
    },

    async save() {
      if (this.editedIndex > -1) {
        await CategoryFactory.editCategory(this.editedCategory.id, this.editedCategory)
        Object.assign(this.categories[this.editedIndex], this.editedCategory)
      } else {
        await CategoryFactory.createCategory(this.editedCategory)
        this.categories.push(this.editedCategory)
      }
      this.close()
    },
  },
}
</script>