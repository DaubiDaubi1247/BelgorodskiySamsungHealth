import { Inputs } from "../../slices/auth/Types"

export interface IResponse {
    status : number
    message : string | null
    usersOfApp : Inputs
}