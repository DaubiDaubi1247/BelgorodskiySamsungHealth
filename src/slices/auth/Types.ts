export type Inputs = {
    email: string,
    password: string,   // убрать
    login: string,
    id: number
    isAdmin : boolean | null
};

export enum ResponseStatus {
    SUCCESS,

}