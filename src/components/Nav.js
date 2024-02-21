import { Link } from 'react-router-dom'
import './nav.css'
export default function Menu() {
    return (
        <nav >
            <div >
                <Link className="navbar-brand" to="/">Parkinglot System</Link>
                <div >
                    <ul>
                        <li className="nav-item">
                            <Link to="/cadastrar-veiculo">Cadastrar Entrada</Link>
                        </li>
                        <li>
                            <Link to="/vehicles/">Veiculos</Link>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    )

}