<template>
 <div>
   <Customer />
   <Staff class="mt-5" />
 </div>
</template>

<script>
  import Customer from "@/components/Customer";
  import Staff from "@/components/Staff";
  export default {
    name: 'Home',
    components: {Staff, Customer},
  }
</script>
