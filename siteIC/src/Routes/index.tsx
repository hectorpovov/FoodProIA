import { LoginPage } from "../pages/Login"
import { Routes, Route } from "react-router-dom"
import { HomePage } from "../pages/Home"

export function AppRoutes() {
    return (
        <Routes>
            <Route path="/Login" element={<LoginPage />}></Route>
            <Route path= "/" element = {<HomePage />} ></Route>
        </Routes>
    )
}