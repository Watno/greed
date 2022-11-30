<template>
  <div class="threat">
    <span>T+{{spawnTime+1}}: {{threat.zone? "ZONE "+ threat.zone: "INTERNAL"}}</span>
    <img :src="imagePath" />
  </div>
</template>

<script lang="ts">
import ThreatModel from "@/models/ThreatModel";
import {defineComponent, computed} from "vue";

export default defineComponent({
  props: {
    threat:{
      type: ThreatModel,
      required: true
    },
    spawnTime:{
      type: Number,
      required: true
    }
  },
  setup(props) {
    const imagePath = computed(() => `./images/threats/${props.threat.type}.png`);

    return {imagePath};
  }
});

</script>

<style scoped>
.threat {
  display: flex;
  flex-direction: column;
  margin: 0 0.5em;
}

.threat img{
  height: 20em;
  width: auto;
}
</style>
