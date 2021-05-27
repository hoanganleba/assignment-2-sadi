<template>
  <div>
    <InventoryReceiveDetail :id="$route.params.id"/>
  </div>
</template>

<script>
import InventoryReceiveDetail from "@/components/InventoryReceiveDetail";

export default {
  components: {InventoryReceiveDetail}
}
</script>