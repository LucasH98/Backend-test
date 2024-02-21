import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { CompanyProvider} from './components/companyContext/companyContext'
import Home from './components/Home'
import CompanyRegistration from './components/Company/CompanyRegistration';
import VehicleRegistration from './components/Vehicle/VehicleRegistration';
import Main from './components/Main';

function App() {
    return (
      <CompanyProvider>{/*Garantindo que o contexto seja fornecido de maneira abrangente a toda app*/}
        <Router>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/company" element={<CompanyRegistration />} />
            <Route path="/cadastrar-veiculo" element={<VehicleRegistration />} />
            <Route path="/vehicles" element={<Main />} />
          </Routes>
        </Router>
      </CompanyProvider>
    );
  }
  
  export default App;
