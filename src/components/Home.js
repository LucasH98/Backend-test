import React from 'react';
import Nav from './Nav'
import './home.css'
import LoginCompany  from '../components/login/loginCompany'

export  default function Home (){
    return(
        <div className='main'>
        <Nav/>
        <h2>BEM VINDO AO SEU SISTEMA DE ESTACIONAMENTO ONLINE</h2>
        <p>Faça o Login ou crie uma conta para sua Empresa Abaixo</p>
        <LoginCompany/>
        </div>
    )
}