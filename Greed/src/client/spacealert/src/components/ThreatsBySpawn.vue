<template>
  <div class="threatsBySpawn">
      <div v-for="(threatsBySpecificSpawn, key) in threatsBySpawn" :key="key">
        <div v-if="threatsBySpecificSpawn.length" class="threatContainer">
          <Threat v-for="(threat, innerKey) in threatsBySpecificSpawn" :key="innerKey" :threat="threat" :spawnTime="key"></Threat>
        </div>
      </div>
  </div>
</template>

<script lang="ts">
import ThreatModel from "@/models/ThreatModel";
import {defineComponent, PropType} from "vue";
import Threat from "./Threat.vue";

export default defineComponent({
  components: { Threat }, 
   props: {
    threatsBySpawn:{
      type: Array as PropType<Array<Array<ThreatModel>>>,
      required: true
    },
  }
});

</script>

<style scoped>
.threatContainer{
  display: content;
}

.threatsBySpawn{
  display: flex;
}
</style>

<!--

@ValidateNested()
trajectories: Map<ZoneModel,TrajectoryModel>;

@ValidateNested()
internalTrajectory: TrajectoryModel; -->