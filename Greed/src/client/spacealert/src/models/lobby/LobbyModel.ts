import { Type } from "class-transformer"
import { IsArray } from "class-validator"
import TableModel from "./TableModel"

export default class LobbyModel {
    tablenumber: number | undefined

    @IsArray()
    @Type(()=>TableModel)
    tables: TableModel[]
    
  constructor(tablenumber: number | undefined, tables: TableModel[]) {
    this.tablenumber = tablenumber
    this.tables = tables
  }
}
