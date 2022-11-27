<template>
  <div class="trajectoryContainer">
    <span>{{zone? "ZONE "+ zone: "INTERNAL"}}</span>
    <img :src="imagePath" />
  </div>
</template>

<script lang="ts">
import TrajectoryModel from "@/models/TrajectoryModel";
import { ZoneModel } from "@/models/ZoneModel";
import {defineComponent, computed} from "vue";

export default defineComponent({
  props: {
    trajectory:{
      type: TrajectoryModel,
      required: true
    },
    zone:{
      type: ZoneModel,
    }
  },
  setup(props) {
    const imagePath = computed(() => `./images/trajectories/${props.trajectory.name}.png`);

    return {imagePath};
  }
});
</script>

<style scoped>
.trajectoryContainer {
  display: flex;
  flex-direction: column;
  margin: 0 0.5em;
}

img{
  height: 10em;
  width: auto;
}
</style>
