<template>
  <div class="space">
      <div v-if="!hasCard" class="position" @click="placeCard">{{position}}</div>
      <ActionCard v-if="hasCard" class="card" :card="card" :selectedCard="selectedCard"  @select="selectCard" @flip="flipCard"/>
  </div>
</template>

<script lang="ts">
import {defineComponent, computed} from "vue";
import ActionCardModel from "../models/ActionCardModel";
import SelectedCardModel from "../models/SelectedCardModel";
import ActionCard from "./ActionCard.vue";
export default defineComponent({
    components: { ActionCard },
    props: {
        position:{
            type: Number,
        },
        card:{
            type: ActionCardModel,
        },
        selectedCard:{
            type: SelectedCardModel,
        }
  },
  setup(props, {emit}) {
      const hasCard = computed(() => props.card != null)

      function selectCard(id: string) {
        emit('select-card', id);
      } 

      function flipCard(id: string) {
        emit('flip-card', id);
      } 

      function placeCard(){
          emit('place-card', props.position)
      }
      return {hasCard, selectCard, flipCard, placeCard}
  } 
})
</script>

<style scoped>
.space {
  width: 54px;
  height: 104px;
  display: inline-block;
  border:2px;
  margin:2px;
  border-color:black;
  border-style:solid;
}

.position {
    height: 100%;
    width: 100%;
    font-size:50px;
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>
