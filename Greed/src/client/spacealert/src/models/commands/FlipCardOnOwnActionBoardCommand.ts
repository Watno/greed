import { IsUUID } from "class-validator"

export default class FlipCardOnOwnActionBoardCommand {
  type = ".FlipCardOnOwnActionBoardCommand"

  @IsUUID()
  cardId: string

  constructor(cardId: string) {
    this.cardId = cardId
  }
}