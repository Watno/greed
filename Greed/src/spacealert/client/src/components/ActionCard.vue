<template>
  <div v-bind:class="['card', { flipped: flipped }, {selected: selected}]" @click="select" @contextmenu.prevent="flip">
    <img :src="actionHalfImage" width="50px" height="50px" />
    <img :src="movementHalfImage" width="50px" height="50px" class="bottom" />
  </div>
</template>

<script lang="ts">
import { Vue, Component, Prop, Emit } from 'vue-property-decorator'
import ActionCardModel from "../models/ActionCardModel";
import {CardOrientationModel} from "../models/CardOrientationModel";
import SelectedCardModel from '../models/SelectedCardModel';

@Component
export default class ActionCard extends Vue {
    @Prop()
    card!: ActionCardModel;

    @Prop()
    selectedCard!: SelectedCardModel | null

    get flipped(): boolean {
      return this.card.orientation == CardOrientationModel.MOVEMENT;
    }
    get movementHalfImage(): string {
      return `./${this.card.movementHalf.type}.png`;
    }
    get actionHalfImage(): string {
      return `./${this.card.actionHalf.type}.png`;
    }

    get selected(): boolean {
      if (this.selectedCard == null){
        return false;
      }
      return this.selectedCard.id == this.card.id;
    }

    @Emit() 
    select(): string {
      return this.card.id;
    }

    @Emit() 
    flip(): string {
      return this.card.id;
    }
}
</script>

<style scoped>
.card {
  width: 50px;
  height:100px;
  display: inline-block;
  border:2px;
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
}
</style>
