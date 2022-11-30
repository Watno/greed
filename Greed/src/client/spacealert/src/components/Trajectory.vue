<template>
  <div class="trajectoryContainer">
    <img :src="imagePath" />
    <span>{{zone? zone: "INTERNAL"}}</span>
  </div>
</template>

<script lang="ts">
import TrajectoryModel from "@/models/TrajectoryModel";
import {defineComponent, computed} from "vue";

export default defineComponent({
  props: {
    trajectory:{
      type: TrajectoryModel,
      required: true
    },
    zone:{
      required: false
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
  height: 25em;
  width: auto;
}

span{
  text-align: center;
}
</style>
