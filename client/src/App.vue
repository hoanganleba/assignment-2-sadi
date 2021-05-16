<template>
  <div>
    <Navbar/>
    <b-container class="mt-5">
      <CustomerTable/>
    </b-container>
  </div>
</template>

<script>
import CustomerTable from "@/components/CustomerTable";
import Navbar from "@/components/Navbar";

export default {
  name: 'App',
  components: {
    Navbar,
    CustomerTable
  }
}
</script>

<style>
* {
  font-family: ui-rounded, "SF Pro Rounded", system-ui, serif;
}
</style>