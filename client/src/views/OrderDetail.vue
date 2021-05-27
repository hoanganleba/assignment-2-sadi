<template>
  <div>
    <OrderDetail :id="$route.params.id" />
  </div>
</template>

<script>
import OrderDetail from "@/components/OrderDetail";

export default {
  components: {OrderDetail}
}
</script>