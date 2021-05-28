<template>
 <div>
   <Customer />
   <Provider class="mt-5" />
   <Staff class="mt-5" />
   <Product class="mt-5" />
   <Category class="mt-5" />
 </div>
</template>

<script>
  import Customer from "@/components/Customer";
  import Staff from "@/components/Staff";
  import Provider from "@/components/Provider";
  import Product from "@/components/Product";
  import Category from "@/components/Category";
  export default {
    components: {Category, Product, Provider, Staff, Customer},
  }
</script>
