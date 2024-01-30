import React, { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import './vehicle.css';
import axios from 'axios';


const createVehicleUrl = 'http://localhost:8080/vehicle/add/';
//const createMovementRecordUrl = 'http://localhost:8080/record/generate/'//ADICIONAR_ID_VEHICLE'
//const getCompanyByIdCompanyUrl = 'http://localhost:8080/company/';


//todo: passar o id da company para este componente pois apenas é criado um vehicle se houve uma company associada



export default function VehicleRegistration({ CompanyId }) {

    const [vehicle, setVehicle] = useState({
        id: "",
        placa: "",
        marca: "",
        modelo: "",
        cor: "",
        tipo: "",
    })





    const handleSubmit = async (e) => {
        e.preventDefault();
        // how get the companyId?


        const vehicleData = {
            placa: vehicle.placa,
            marca: vehicle.marca,
            modelo: vehicle.modelo,
            cor: vehicle.cor,
            tipo: vehicle.tipo,
        }

        try {

            const response =  await axios.post(createVehicleUrl+CompanyId,vehicleData)
            console.log("API Response:", response.data);    


        }
        catch (e) {
            console.log(e);
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
            <h1 className="title">Cadastro do Veículo</h1>


            <form className="form" onSubmit={handleSubmit}>
                <section className="mainSection">
                    <div className="form-row">
                        <div className="form-group col-md-6">
                            <label >Placa </label>
                            <input type="text" className="form-control" />
                        </div>
                        <div className="form-group col-md-6">
                            <label >Marca </label>
                            <input type="text" className="form-control" />
                        </div>
                    </div>
                    <div className="form-group">
                        <label >Modelo</label>
                        <input type="text" className="form-control" />
                    </div>
                    <div className="form-group">
                        <label htmlFor="inputAddress2">Cor </label>
                        <input type="text" className="form-control" />
                    </div>
                    <label>Tipo</label>

                    <div className="form-group">
                        <select>
                            <option>Selecione o tipo</option>
                            <option>Carro</option>
                            <option>Moto</option>
                        </select>

                    </div>

                </section>

                <button type="submit" className="btn btn-primary">Registrar Entrada</button>
            </form>
        </div>
    )

}


