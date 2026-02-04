import "./TopBar.css"

export function TopBar() {
    return (
        <div className="topbar">
            <a className="logo">FoodProIA</a>

            <label className="icons"></label>

            <div className="links">
                <a>Entrar</a>
                <a>Faça o  cadastro!</a>
            </div>
        </div>
    )
}