package com.gugawag.rpc.banco;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AppClienteBanco {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        // procura o serviço no RMI Registry local. Perceba que o cliente não connhece a
        // implementação do servidor,
        // apenas a interface

        Registry registry = LocateRegistry.getRegistry("192.168.0.191", 1099);
        BancoServiceIF banco = (BancoServiceIF) registry.lookup("BancoService");
        menu();
        Scanner entrada = new Scanner(System.in);
        int opcao = entrada.nextInt();

        while (opcao != 9) {
            switch (opcao) {
                case 1: {
                    System.out.println("Digite o número da conta:");
                    String conta = entrada.next();
                    // chamada ao método remoto, como se fosse executar localmente
                    System.out.println(banco.saldo(conta));
                }
                case 2: {
                    // chamada ao método remoto, como se fosse executar localmente
                    System.out.println(banco.quantidadeContas());
                }
                case 3: {
                    System.out.println("Digite o numero da conta:");
                    String num = entrada.next();
                    entrada.nextLine();
                    System.out.println("Digite o numero do saldo: ");
                    try {
                        double saldo = entrada.nextDouble();
                        banco.adicionarConta(num, saldo);
                    } catch (InputMismatchException err) {
                        System.out.println("Erro! O valor digitado não é válido. Tente novamente!");
                    }

                }
            }
            menu();
            opcao = entrada.nextInt();
        }
        entrada.close();
    }

    public static void menu() {

        System.out.println("\n=== Luís Henrique Ferreira Freire ===");
        System.out.println("\n=== BANCO RMI (ou FMI?!) ===");
        System.out.println("1 - Saldo da conta");
        System.out.println("2 - Quantidade de contas");
        System.out.println("3 - Cadastrar Conta");
        System.out.println("9 - Sair");
    }

}
