package com.Bank;


import com.account.Account;

import java.util.Scanner;

public class BankApplication { // 최적화 버전
    private static final int MAX_ACCOUNTS = 100;
    private static Account[] accounts = new Account[MAX_ACCOUNTS];
    private static int numAccounts = 0;
    private static final String[] optionAccountList = {"계좌생성", "출금목록", "예금", "출금", "종료"};
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        BankApplication bank = new BankApplication();
        bank.run();
    }

    public void run() {
        while (true) {
            optionListMessage();
            int optionId = Integer.parseInt(scanner.nextLine());

            if (optionId == 1) {
                createAccount();
            } else if (optionId == 2) {
                findAllAccounts();
            } else if (optionId == 3) {
                deposit();
            } else if (optionId == 4) {
                withdraw();
            } else if (optionId == 5) {
                System.out.println("프로그램 종료");
                break;
            } else {
                System.out.printf("(%d) 는 잘못된 선택입니다!", optionId);
            }
        }
    }

    private void createAccount() {
        String accountNumber = inputAccountNumber();

        if (existAccountNumber(accountNumber)) {
            System.out.printf("이미 존재하는 (%s) 입니다!", accountNumber);
            return;
        }

        String owner = inputAccountOwner();
        int deposit = inputFirstDeposit();

        if (!isPositiveNumberDeposit(deposit)) {
            System.out.printf("잘못된 초기입금액(%d) 입니다!", deposit);
            return;
        }

        insertAccount(accountNumber, owner, deposit);
        System.out.println("결과: 계좌가 생성되었습니다.");
    }

    private void findAllAccounts() {
        for (int i = 0; i < numAccounts; i++) {
            System.out.println(accounts[i]);
        }
    }

    private void deposit() {
        String accountNumber = inputAccountNumber();
        int findIdx = findAccountIdx(accountNumber);

        if (findIdx == -1) {
            System.out.printf("잘못된 계좌 번호(%s) 입니다!", accountNumber);
            return;
        }

        int depositAmount = inputDeposit();
        if (!isPositiveNumberDeposit(depositAmount)) {
            System.out.printf("잘못된 예금액(%d) 입니다!", depositAmount);
            return;
        }

        accounts[findIdx].addDeposit(depositAmount);
    }

    private void withdraw() {
        String accountNumber = inputAccountNumber();
        int findIdx = findAccountIdx(accountNumber);

        if (findIdx == -1) {
            System.out.printf("잘못된 계좌번호(%s) 입력입니다!", accountNumber);
            return;
        }

        int withdrawalAmount = inputWithdrawal();
        if (!isPositiveNumberDeposit(withdrawalAmount)) {
            System.out.printf("잘못된 출금액(%d) 입니다!", withdrawalAmount);
            return;
        }

        if (isPositiveBalance(findIdx, withdrawalAmount)) {
            accounts[findIdx].subDeposit(withdrawalAmount);
        } else {
            System.out.println("잔액이 부족합니다!");
        }
    }

    private String inputAccountNumber() {
        System.out.print("계좌번호: ");
        return scanner.nextLine();
    }

    private String inputAccountOwner() {
        System.out.print("계좌주: ");
        return scanner.nextLine();
    }

    private int inputFirstDeposit() {
        System.out.print("초기입금액: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private int inputDeposit() {
        System.out.print("예금액: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private int inputWithdrawal() {
        System.out.print("출금액: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private boolean isPositiveNumberDeposit(int number) {
        return number >= 0;
    }

    private boolean isPositiveBalance(int idx, int deposit) {
        return accounts[idx].getDeposit() - deposit >= 0;
    }

    private void insertAccount(String accountNumber, String owner, int deposit) {
        if (numAccounts < MAX_ACCOUNTS) {
            accounts[numAccounts++] = new Account(accountNumber, owner, deposit);
        } else {
            System.out.println("더 이상 계좌를 생성할 수 없습니다. 용량이 꽉 찼습니다.");
        }
    }

    private boolean existAccountNumber(String accountNumber) {
        for (int i = 0; i < numAccounts; i++) {
            if (accounts[i].getAccountNumber().equals(accountNumber)) {
                return true;
            }
        }
        return false;
    }

    private int findAccountIdx(String accountNumber) {
        for (int i = 0; i < numAccounts; i++) {
            if (accounts[i].getAccountNumber().equals(accountNumber)) {
                return i;
            }
        }
        return -1;
    }

    private void optionListMessage() {
        System.out.println("---------------------------------------------");
        for (int i = 0; i < optionAccountList.length; i++) {
            if (i != optionAccountList.length - 1) {
                System.out.printf("%d.%s | ", i+1, optionAccountList[i]);
            } else {
                System.out.printf("%d.%s", i+1, optionAccountList[i]);
            }
        }
        System.out.println("\n---------------------------------------------");
        System.out.print("선택> ");
    }
}