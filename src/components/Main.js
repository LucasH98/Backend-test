import React, { Component } from "react";
import './Main.css';

import Nav from './Nav';

import axios from 'axios';

const url = 'http://localhost:8080/vehicle/';
const urlMovementRecord = 'http://localhost:8080/record/get?placa='
//const exitValidatorUrl = 'http://localhost:8080/record/out?id=65d0e7ca3b60de2dc73c9ee4&placa=666'


export default class Main extends Component {
    constructor(props) {
        super(props);
        this.state = {
            vehicles: [],
        };
    }


    componentDidMount() {
        this.getAllVehicles();
    }

    async getAllVehicles() {
        try {
            const response = await axios.get(url);

            const data = response.data;

            for (const vehicle of data) {
                const movementRecordResponse = await axios.get(urlMovementRecord + vehicle.placa);
                vehicle.movementRecord = movementRecordResponse.data;
            }

            this.setState({ vehicles: data });
        } catch (error) {
            console.log(error);
        }
    }
    handleExitButtonClick = async (id, placa) => {
        try {
            await axios.post(`http://localhost:8080/record/out?id=${id}&placa=${placa}`);
            this.setState(prevState => ({
                vehicles: prevState.vehicles.map(vehicle => {
                    if (vehicle.placa === placa) {
                        vehicle.movementRecord.exitTime = new Date().toString(); 
                    }
                    return vehicle;
                })
            }));
        } catch (e) {
            console.log(e);
        }
    }
    

    getMovementRecord(placa) {
        axios.get(urlMovementRecord + placa)
            .then(response => {
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
            <div className="main">
                <Nav />
                <div>
                <h1 style={{ marginTop: "10px" }}>Veiculos validados</h1>                    <div className="table-container">
                        <table className="table">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Tipo</th>
                                    <th scope="col">Modelo</th>
                                    <th scope="col">Placa</th>
                                    <th scope="col">Marca</th>
                                    <th scope="col">Hora da entrada</th>
                                    <th scope="col">Hora da saída</th>
                                </tr>
                            </thead>
                            <tbody>
                                {vehicles.map((vehicle, index) => (
                                    <tr key={index}>
                                        <th scope="row">{index + 1}</th>
                                        <td>{vehicle.tipo}</td>
                                        <td>{vehicle.modelo}</td>
                                        <td>{vehicle.placa}</td>
                                        <td>{vehicle.marca}</td>
                                        <td>{vehicle.movementRecord ? vehicle.movementRecord.entryTime || "N/A" : "N/A"}</td>
                                        <td>
                                            {vehicle.movementRecord && !vehicle.movementRecord.exitTime ? (
                                                <button onClick={() => this.handleExitButtonClick(vehicle.id,vehicle.placa)}>Validar Saída</button>
                                            ) : (
                                                vehicle.movementRecord && vehicle.movementRecord.exitTime || "N/A"
                                            )}
                                        </td>
                                    </tr>
                                ))}

                            </tbody>

                        </table>
                    </div>
                </div>
            </div>
        )
    }
}
