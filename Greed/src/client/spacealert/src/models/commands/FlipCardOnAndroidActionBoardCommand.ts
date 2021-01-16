import { IsUUID } from "class-validator"

export default class FlipCardOnAndroidActionBoardCommand {
  type = ".FlipCardOnAndroidActionBoardCommand"

  @IsUUID()
  cardId: string

  constructor(cardId: string) {
    this.cardId = cardId
  }
}