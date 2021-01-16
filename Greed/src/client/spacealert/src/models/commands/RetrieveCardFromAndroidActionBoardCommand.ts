import { IsEnum, IsUUID } from "class-validator"
import { ColorModel } from "../ColorModel";

export default class RetrieveCardFromAndroidActionBoardCommand {
  type = ".RetrieveCardFromAndroidActionBoardCommand"

  @IsUUID()
  cardId: string

  @IsEnum(ColorModel)
  color: ColorModel

  constructor(cardId: string, color: ColorModel) {
    this.cardId = cardId
    this.color = color
  }
}