import { Inputs } from "../../slices/auth/Types"

export interface IResponse {
    status : number
    errorMessage : string | null
    userData : Inputs
}