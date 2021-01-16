import { IsInt, IsUUID } from "class-validator"

export default class PlaceCardOnOwnActionBoardCommand {
  type = ".PlaceCardOnOwnActionBoardCommand"

  @IsUUID()
  cardId: string

  @IsInt()
  position: number

  constructor(cardId: string, position: number) {
    this.cardId = cardId
    this.position = position
  }
}