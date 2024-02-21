import React from "react";
import { Link } from 'react-router-dom';
import './login.css'; 

export default function LoginCompany() {

    return (
        <div>
            <div>
                <form>
                    <input type="hidden" />
                    <div>
                        <label>CNPJ</label>
                        <input type="text" name="cnpj" />
                    </div>
                    <div>
                        <label>Senha</label>
                        <input type="password" />
                    </div>
                    <button type="submit">Entrar</button>
                </form>
                <p>Se você ainda não tem uma conta, faça seu cadastro abaixo:</p>
                <Link to="/company" className="custom-link">Cadastrar</Link>
            </div>
        </div>
    );
}
