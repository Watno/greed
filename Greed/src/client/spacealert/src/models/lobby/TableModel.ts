import { IsArray, IsInt, IsString } from "class-validator"

export default class TableModel {
    @IsInt()
    numberOfPlayers: number

    @IsArray()
    @IsString()
    players: string[]
    
  constructor(numberOfPlayers: number, players: string[]) {
    this.numberOfPlayers = numberOfPlayers
    this.players = players
  }
}
