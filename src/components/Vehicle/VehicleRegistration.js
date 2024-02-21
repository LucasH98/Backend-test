// VehicleRegistration.js
import React, { useState} from "react";
import Nav from '../Nav';
import './vehicle.css';
import axios from 'axios';
import { useCompany } from '../companyContext/companyContext';

const createVehicleUrl = 'http://localhost:8080/vehicle/add/';
const createMovementRecordUrl = 'http://localhost:8080/record/generate';
const entryValidatorUrl = 'http://localhost:8080/record/in';

export default function VehicleRegistration() {
    const { company } = useCompany();

    const [vehicle, setVehicle] = useState({
        id: "",
        placa: "",
        marca: "",
        modelo: "",
        cor: "",
        tipo: "",
    });
    const [selectedTipo, setSelectedTipo] = useState("");

    const handleChange = (e) => {
        const { name, value } = e.target;
        setVehicle((prevVehicle) => ({
            ...prevVehicle,
            [name]: value,
        }));
    };

    const handleTipoChange = (e) => {
        setSelectedTipo(e.target.value);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!company.id) {
            console.error("ID da empresa não disponível.");
            return;
        }

        const vehicleData = {
            placa: vehicle.placa,
            marca: vehicle.marca,
            modelo: vehicle.modelo,
            cor: vehicle.cor,
            tipo: selectedTipo,
        };

        console.log("ID da Empresa:", company.id);
        console.log("Dados do Veículo:", vehicleData);

        try {
            const response = await axios.post(`${createVehicleUrl}${company.id}/`, vehicleData);

            //console.log("API Response:", response.data);

            await axios.post(`${createMovementRecordUrl}?vehicleId=${response.data.id}`, {
                vehicleId: response.data.id,
            });

            await axios.post(`${entryValidatorUrl}?id=${response.data.id}`, {
                id: response.data.id,
                entryTime: response.data.entryTime
            });

        } catch (e) {
            console.log("Erro na solicitação do veículo:", e);
        }
    };

    return (
        <div className="main">
            <Nav />
            <h1 className="title">Cadastro do Veículo</h1>
            <form className="form" onSubmit={handleSubmit}>
                <section className="mainSection">
                    <div className="form-row">
                        <div className="form-group col-md-6">
                            <label >Placa </label>
                            <input type="text" className="form-control" name="placa" value={vehicle.placa} onChange={handleChange} />
                        </div>
                        <div className="form-group col-md-6">
                            <label >Marca </label>
                            <input type="text" className="form-control" name="marca" value={vehicle.marca} onChange={handleChange} />
                        </div>
                    </div>
                    <div className="form-group">
                        <label >Modelo</label>
                        <input type="text" className="form-control" name="modelo" value={vehicle.modelo} onChange={handleChange} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="inputAddress2">Cor </label>
                        <input type="text" className="form-control" />
                    </div>
                    <label>Tipo</label>

                    <div className="form-group">
                        <select value={selectedTipo} onChange={handleTipoChange}>
                            <option value="">Selecione o tipo</option>
                            <option value="Carro"> Carro</option>
                            <option value="Moto">Moto</option>
                        </select>
                    </div>
                </section>
                <button type="submit" className="btn btn-primary">Registrar Entrada</button>
            </form>
        </div>
    )
}
