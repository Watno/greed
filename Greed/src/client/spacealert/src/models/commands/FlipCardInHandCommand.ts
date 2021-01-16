import { IsUUID } from "class-validator"

export default class FlipCardInHandCommand {
  type = ".FlipCardInHandCommandaa"

  @IsUUID()
  cardId: string

  constructor(cardId: string) {
    this.cardId = cardId
  }
}