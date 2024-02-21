// companyContext.js
import React, { createContext, useContext, useState, useEffect } from 'react';
import PropTypes from 'prop-types';

const CompanyContext = createContext();

export const useCompany = () => {
  return useContext(CompanyContext);
};

export const CompanyProvider = ({ children }) => {
  const [company, setCompany] = useState(() => {
    // Recuperar o ID da Empresa do localStorage ao inicializar
    const storedCompanyId = localStorage.getItem('companyId');
    return {
      name: '',
      cnpj: '',
      endereco: '',
      telefone: '',
      qntVagaCarro: 0,
      qntVagaMoto: 0,
      id: storedCompanyId || null,
    };
  });

  useEffect(() => {
    // Atualizar o ID da Empresa no localStorage sempre que ele for alterado
    localStorage.setItem('companyId', company.id);
  }, [company.id]);

  return (
    <CompanyContext.Provider value={{ company, setCompany }}>
      {children}
    </CompanyContext.Provider>
  );
};

CompanyProvider.propTypes = {
  children: PropTypes.node.isRequired,
};
