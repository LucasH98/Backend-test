
import React from 'react';
import axios from 'axios';
import Nav from '../Nav';
import { useCompany } from '../companyContext/companyContext';

const createCompanyUrl = 'http://localhost:8080/company';

export default function CompanyRegistration() {
  const { company, setCompany } = useCompany();
  
  const handleSubmit = async (e) => {
    e.preventDefault();

    const companyData = {
      name: company.name,
      cnpj: company.cnpj,
      endereco: company.endereco,
      telefone: company.telefone,
      qntVagaCarro: company.qntVagaCarro,
      qntVagaMoto: company.qntVagaMoto,
    };

    try {
        const response = await axios.post(createCompanyUrl, companyData);
    
        console.log("API Response (Company Registration):", response.data);
      
        setCompany((prevCompany) => ({
          ...prevCompany,
          id: response.data.id,
          name: '',
          cnpj: '',
          endereco: '',
          telefone: '',
          qntVagaCarro: 0, 
          qntVagaMoto: 0,
        }));
      
      } catch (e) {
        console.log("Erro na solicitação da empresa:", e);
      }
      
  };

  const handleChange = (e) => {
    const { name, value } = e.target;

    setCompany((prevCompany) => ({
      ...prevCompany,
      [name]: value,
    }));
  };


        return (
            <div className="main">
               <Nav/>
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
