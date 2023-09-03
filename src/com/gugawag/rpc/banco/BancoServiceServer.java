package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {

    // private Map<String, Double> saldoContas;

    private List<Conta> contas;

    public BancoServiceServer() throws RemoteException {
        /*
         * saldoContas = new HashMap<>();
         * saldoContas.put("1", 100.0);
         * saldoContas.put("2", 156.0);
         * saldoContas.put("3", 950.0);
         */

        contas = new ArrayList<Conta>();
    }

    @Override
    public double saldo(String conta) throws RemoteException {
        Conta contaConsulta = this.contas.stream().filter(account -> conta.equals(account.getNumero())).findFirst()
                .orElse(null);
        return contaConsulta.getSaldo();
    }

    @Override
    public int quantidadeContas() throws RemoteException {
        return this.contas.size();
    }

    @Override
    public Conta adicionarConta(String numero, double saldo) throws RemoteException {
        Conta conta = new Conta(numero, saldo);
        this.contas.add(conta);
        return conta;
    }

}
