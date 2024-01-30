  import React, { useState } from "react";
import './CompanyRegistration.css';
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import axios from 'axios';


const createCompanyUrl = 'http://localhost:8080/company';

export default function CompanyRegistration() {

    const [company, setCompany] = useState({
        id: "",
        name: "",
        cnpj: "",
        endereco: "",
        telefone: "",
        qntVagaCarro: "",
        qntVagaMoto: "",
        vehicles:[],
    });


    //todo :compreender melhor esse trecho e como adicionar no array de veiculos cada veiculo criado

    const handleChange = (e) => {
        const {name,value} = e.target;

        setCompany((prevCompany) => ({
            ...prevCompany,
            [name]: value,
          }));
        };


     const  handleSubmit = async (e) => {
        e.preventDefault();

        const companyData= {
            name: company.name,
            cnpj: company.cnpj,
            endereco: company.endereco,
            telefone: company.telefone,
            qntVagaCarro: company.qntVagaCarro,
            qntVagaMoto:company.qntVagaMoto,
        }

        try{

            const response = await axios.post(createCompanyUrl,companyData)

            setCompany(prevCompany => ({
                ...prevCompany,
                id: response.data.id,
            }));


            console.log("API Response:", response.data);    
            console.log("ID "+response.data.id);
        }
        
        catch(e){
            console.log(e)
        }
    }

        return (
            <div className="main">
                <nav className="navbar navbar-expand-lg bg-body-tertiary">
                    <div className="container-fluid">
                        <a className="navbar-brand" href="#">Parkinglot System</a>
                        <button className="navbar-toggler" type="button"></button>
                        <div className="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                                <li className="nav-item">
                                    <a className="nav-link" href="#">Cadastrar</a>
                                </li>
                                <li className="nav-item">
                                    <a className="nav-link" href="#">Veiculos</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
                <h1 className="title">Cadastre seu Estabelecimento</h1>


                <form className="form" onSubmit={handleSubmit}>
                    <section className="mainSection">
                        <div className="form-row">
                            <div className="form-group col-md-6">
                                <label >Nome do Estabelecimento</label>
                                <input type="text" className="form-control" name="name" value={company.name} onChange={handleChange} />
                            </div>
                            <div className="form-group col-md-6">
                                <label >CNPJ</label>
                                <input type="text" className="form-control"  name="cnpj" value={company.cnpj} onChange={handleChange} />
                            </div>
                        </div>
                        <div className="form-group">
                            <label htmlFor="inputAddress">Endereço</label>
                            <input type="text" className="form-control"  name="endereco" value={company.endereco} onChange={handleChange} />
                        </div>
                        <div className="form-group">
                            <label htmlFor="inputAddress2">Telefone</label>
                            <input type="text" className="form-control" name="telefone" value={company.telefone} onChange={handleChange} />
                        </div>
                        <div className="form-row">
                            <div className="form-group col-md-6">
                                <label htmlFor="inputCity">Quantidade de carros</label>
                                <input type="number" className="form-control" name="qntVagaCarro" value={company.qntVagaCarro} onChange={handleChange} />
                            </div>
                        </div>
                        <div className="form-row">
                            <div className="form-group col-md-6">
                                <label htmlFor="inputCity">Quantidade de motos</label>
                                <input type="number" className="form-control" name="qntVagaMoto" value={company.qntVagaMoto} onChange={handleChange} />
                            </div>
                        </div>
                    </section>

                    <button type="submit" className="btn btn-primary">Registrar</button>
                </form>


            </div>
        )

    }
