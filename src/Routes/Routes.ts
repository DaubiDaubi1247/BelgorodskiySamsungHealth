
export const auth = "/auth";
export const AuthRoutes = {
    authRoute : auth + "/login" ,
    registration : auth + "/registration"
} 

export const main = "/";
export const MainRoutes = {
    training : main + "training"
} 

export const diets = "/diets";
export const DietsRoutes = {
    fullDescription : diets + "/desc",
    redactDiets : diets + "/redact",
    createDiets : diets + "/create"
} 

export const admin = "/admin";
export const AdminRoutes = {
    redactOfTrain : admin + "/redact",
    createTraining : admin + "/create",
    redactDiets : admin + diets + "/redact",
    createDiets : admin + diets + "/create"
} 

export const profile = "/profile"





