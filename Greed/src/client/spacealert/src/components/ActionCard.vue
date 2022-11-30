<template>
  <div v-bind:class="['card', { flipped: flipped }, {selected: selected}]" @click="select" @contextmenu.prevent="flip">
    <img :src="actionHalfImage" />
    <img :src="movementHalfImage" class="bottom" />
  </div>
</template>

<script lang="ts">
import {defineComponent, computed} from "vue";
import ActionCardModel from "../models/ActionCardModel";
import {CardOrientationModel} from "../models/CardOrientationModel";
import SelectedCardModel from '../models/SelectedCardModel';
export default defineComponent({
  props: {
    card:{
      type: ActionCardModel,
      required: true
    },
    selectedCard:{
      type: SelectedCardModel,
    }
  },
  setup(props, {emit}) {
    const movementHalfImage = computed(() => `./images/actioneffects/${props.card.movementHalf.type}.png`);
    const actionHalfImage = computed(() => `./images/actioneffects/${props.card.actionHalf.type}.png`);

    const flipped = computed(() => props.card.orientation == CardOrientationModel.MOVEMENT) ;
    const flip = () => emit('flip', props.card.id);

    const selected = computed(() => {
      if (props.selectedCard == null){
        return false;
      }
      return props.selectedCard.id == props.card.id;
    });
    const select = () => emit('select', props.card.id);
    return {movementHalfImage, actionHalfImage, flipped, flip, selected, select};
  }
});

</script>

<style scoped>
.card {
  width: 3em;
  height: 6em;
  display: inline-block;
  border: 0.1em;
  border-style:solid;
  border-color: transparent;
}

.flipped {
  transform: rotate(180deg);
}
.bottom {
  transform: rotate(180deg);
}
.selected {
  border-color:red;
  border-style:solid;
}

img {
  display: block;
  width: 3em;
  height: 3em;
}
</style>
