import { IsUUID } from "class-validator"

export default class RetrieveCardFromOwnActionBoardCommand {
  type = ".RetrieveCardFromOwnActionBoardCommand"

  @IsUUID()
  cardId: string

  constructor(cardId: string) {
    this.cardId = cardId
  }
}