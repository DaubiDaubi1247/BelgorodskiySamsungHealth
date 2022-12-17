export interface IsmallDataAboutDiets {
    id : number
    label : string
    description : string
    dishes : null
}

export interface IdishDescription {
    label : string
    type : string
    calPerGram : number
    proteinsPerGram : number
    fatsPerGram : number
}

export type TDishDescriptionArr = Array<IdishDescription>

export type IsmallDataAboutDietsArr = Array<IsmallDataAboutDiets>

