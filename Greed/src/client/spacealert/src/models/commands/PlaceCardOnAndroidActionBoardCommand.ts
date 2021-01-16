import { IsEnum, IsInt, IsUUID } from "class-validator"
import { ColorModel } from "../ColorModel";

export default class PlaceCardOnAndroidActionBoardCommand {
  type = ".PlaceCardOnAndroidActionBoardCommand"

  @IsUUID()
  cardId: string

  @IsInt()
  position: number

  @IsEnum(ColorModel)
  color: ColorModel

  constructor(cardId: string, position: number, color: ColorModel) {
    this.cardId = cardId
    this.position = position
    this.color = color
  }
}