<template>
 <div>
   <Customer />
   <Provider class="mt-5" />
   <Staff class="mt-5" />
 </div>
</template>

<script>
  import Customer from "@/components/Customer";
  import Staff from "@/components/Staff";
  import Provider from "@/components/Provider";
  export default {
    name: 'Home',
    components: {Provider, Staff, Customer},
  }
</script>
