import React, { Component } from "react";
import './Main.css';
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import axios from 'axios';

const url = 'http://localhost:8080/vehicle/';
const urlMovementRecord =  'http://localhost:8080/record/get?placa='

export default class Main extends Component {
    constructor(props) {
        super(props);
        this.state = {
            vehicles: [],
        };
    }


    componentDidMount() {
        this.getAllVehicles();
        this.getMovementRecord();
    }


    getAllVehicles() {
        axios.get(url)
            .then(response => {
                const data = response.data; //aqui eu pego a resposta vinda da url e boto numa vaariavel 
                this.setState({ vehicles: data }); //seto  o estado do meu vehicle com os dados vindos da resposta da requisição
                data.forEach(vehicle => this.getMovementRecord(vehicle.placa)); 
                //aqui para cada data(que no caso representa um veiculo) sera chamada a funcao getmovementrecord .
            })
            
            .catch(error => console.log(error));
    }

  
    getMovementRecord(placa) {

        axios.get(urlMovementRecord + placa)
            .then(response => {
                // Atualizar o estado do componente com os dados de movimento recebidos
                const { vehicles } = this.state;
                const updatedVehicles = vehicles.map(vehicle => {
                    if (vehicle.placa === placa) {
                        vehicle.movementRecord = response.data;
                    }
                    return vehicle;
                });
                this.setState({ vehicles: updatedVehicles });
            })
            .catch(error => console.log(error));
    }
   

    render() {
        const { vehicles } = this.state;

        return (
            <div>
                <nav className="navbar navbar-expand-lg bg-body-tertiary">
                    <div className="container-fluid">
                        <a className="navbar-brand" href="#">Parkinglot System</a>
                        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span className="navbar-toggler-icon"></span>
                        </button>
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
                
                <div>
                    <h1>Veiculos cadastrados</h1>
                    <table className="table">
                        <thead> 
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Tipo</th>
                                <th scope="col">Modelo</th>
                                <th scope="col">Placa</th>
                                <th scope="col">Hora da entrada</th>
                                <th scope="col">Hora da saída</th>
                            </tr>
                        </thead>
                        <tbody>
                            {vehicles.map((vehicle, index) => (

                                vehicle.movementRecord && vehicle.movementRecord.entryTime && (

                                <tr key={index}>
                                    <th scope="row">{index + 1}</th>
                                    <td>{vehicle.tipo}</td>
                                    <td>{vehicle.modelo}</td>
                                    <td>{vehicle.placa}</td>
                                    <td>{vehicle.movementRecord?.entryTime || "N/A"}</td>
                                    <td>{vehicle.movementRecord?.exitTime || "N/A"}</td>
                                </tr>
                                )
                            ))}
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}
