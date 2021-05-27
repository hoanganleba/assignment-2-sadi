<template>
  <div>
    <InventoryDeliveryDetail :id="$route.params.id" />
  </div>
</template>

<script>
import InventoryDeliveryDetail from "@/components/InventoryDeliveryDetail";

export default {
  components: {InventoryDeliveryDetail}
}
</script>
