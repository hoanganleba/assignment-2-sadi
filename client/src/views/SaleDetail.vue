<template>
  <div>
    <SaleDetail :id="$route.params.id"/>
  </div>
</template>

<script>
import SaleDetail from "@/components/SaleDetail";

export default {
  components: {SaleDetail}
}
</script>
