<template>
  <div>
      <ActionBoardSpace v-for="(card, position) in cards" :card="card" :position="position+1" :selectedCard="selectedCard" :key="position" @select-card="selectCard" @flip-card="flipCard" @place-card="placeCard"/>
  </div>
</template>

<script lang="ts">
import { Vue, Component, Prop } from 'vue-property-decorator'
import ActionCardModel from "../models/ActionCardModel";
import ActionBoardModel from "../models/ActionBoardModel";
import SelectedCardModel from "../models/SelectedCardModel";
import ActionBoardSpace from "./ActionBoardSpace.vue";

@Component({
    components: { ActionBoardSpace }
})
export default class ActionBoard extends Vue {
    @Prop()
    board!: ActionBoardModel

    @Prop()
    selectedCard!: SelectedCardModel | null

    get cards(): (ActionCardModel | null)[]{
        return this.board.cards;
    }

    get id(): string{
        return this.board.id;
    }

    selectCard(id: string) {
        this.$emit("select-card", this.id, id);
    }

    flipCard(id: string) {
        this.$emit("flip-card", this.id, id);
    }

    placeCard(position: number) {
         this.$emit("place-card", this.id, position);

    }
}

</script>

<style scoped>
div{
    display: flex;
}
</style>
