<template>
  <div>
      <ActionCard v-for="card in cards" :card="card" :key="card.id" :selectedCard="selectedCard" @select="selectCard" @flip="flipCard" />
      <div class="returnToHand" v-if="cardOnBoardSelected" @click="returnSelectedCard"><div class="returnToHandText">Return selected card to hand</div></div>
  </div>
</template>

<script lang="ts">
import { Vue, Component, Prop, Emit } from 'vue-property-decorator'
import ActionCardModel from "../models/ActionCardModel";
import SelectedCardModel from "../models/SelectedCardModel";
import ActionCard from "./ActionCard.vue";
import { SelectedCardPositionModel } from '../models/SelectedCardPositionModel';

@Component({
    components: { ActionCard }
})
export default class Hand extends Vue {
    @Prop()
    cards!: ActionCardModel[];

    @Prop()
    selectedCard!: SelectedCardModel | null;

    get cardOnBoardSelected(): boolean {
        return (this.selectedCard != null) && this.selectedCard.position == SelectedCardPositionModel.ActionBoard;
    }

    @Emit()
    selectCard(id: string): string {
        return id;
    }

    @Emit()
    flipCard(id: string): string {
        return id;
    }

    @Emit()
    returnSelectedCard(){
        1+1;
    }
}

</script>

<style scoped>
.returnToHand {
  width: 50px;
  height: 100px;
  display: inline-block;
  border:2px;
  border-color:black;
  border-style:dotted;
}

.returnToHandText {
    height: 100%;
    width: 100%;
    font-size:15px;
    display: flex;
    text-align: center;
    justify-content: center;
    align-items: center;
}

div{
    display: flex;
    height:104px
}
</style>
