package com.account;

import java.util.Scanner;

public class BankApplication { // 구 버전
    private static Account[] accounts = new Account[100];
    private static int idx = 0;
    private static final String[] optionAccountList = {"계좌생성", "출금목록", "예금", "출금", "종료"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            optionListMessage();
            int optionId = Integer.parseInt(scanner.nextLine());
            selectedOptionMessage(optionId);

            if (optionId == 1) {
                String accountNumber = inputAccountNumber();

                if (existAccountNumber(accountNumber)) {
                    System.out.printf("이미 존재하는 (%s) 입니다!", accountNumber);
                    break;
                }

                String Owner = inputAccountOwner();
                int deposit = inputFirstDeposit();

                if (!isPositiveNumberDeposit(deposit)) {
                    System.out.printf("잘못된 초기입금액(%d) 입니다!", deposit);
                    break;
                }

                insertAccount(accountNumber, Owner, deposit);
                System.out.println("결과: 계좌가 생성되었습니다.");

            } else if (optionId == 2) {
                findAllAccount();

            } else if (optionId == 3) {
                String accountNumber = inputAccountNumber();
                if (existAccountNumber(accountNumber)) {
                    int deposit = inputDeposit();

                    if (!isPositiveNumberDeposit(deposit)) {
                        System.out.printf("잘못된 예금액(%d) 입니다!", deposit);
                        break;
                    }

                    int findIdx = findAccountIdx(accountNumber);
                    accounts[findIdx].addDeposit(deposit);
                } else {
                    System.out.printf("잘못된 계좌 번호(%s) 입니다!", accountNumber);
                }

            } else if (optionId == 4) {
                String accountNumber = inputAccountNumber();
                if (existAccountNumber(accountNumber)) {
                    int deposit = inputWithdrawal();

                    if (!isPositiveNumberDeposit(deposit)) {
                        System.out.printf("잘못된 예금액(%d) 입니다!", deposit);
                        break;
                    }

                    int findIdx = findAccountIdx(accountNumber);

                    if (isPositiveBalance(findIdx, deposit)) {
                        accounts[findIdx].subDeposit(deposit);
                    } else {
                        System.out.println("잔액이 부족합니다!");
                        break;
                    }
                } else {
                    System.out.printf("잘못된 계좌번호(%s) 입력입니다!", accountNumber);
                }
            } else if (optionId == 5) {
                System.out.println("프로그램 종료");
                break;
            } else {
                System.out.printf("(%d) 는 잘못된 선택입니다!", optionId);
            }


        }
    }

    private static String inputAccountNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("계좌번호: ");
        return scanner.nextLine();
    }

    private static String inputAccountOwner() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("계좌주: ");
        return scanner.nextLine();
    }

    private static int inputFirstDeposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("초기입금액: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private static int inputDeposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("예금액: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private static int inputWithdrawal() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("출금액: ");
        return Integer.parseInt(scanner.nextLine());
    }


    private static boolean isPositiveNumberDeposit(int number) {
        return number >= 0;
    }

    private static boolean isPositiveBalance(int idx, int deposit) {
        return accounts[idx].getDeposit() - deposit >= 0;
    }


    private static void insertAccount(String accountNumber, String name, int deposit) {
        accounts[idx++] = new Account(accountNumber, name, deposit);
    }

    private void updateAccount() {
    }

    private static boolean existAccount(Account account) {
        for (Account value : accounts) {
            if (value == null) {
                return false;
            }
            if (value == account) {
                return true;
            }
        }
        return false;
    }

    private static int findAccountIdx(String accountNumber) {
        int idx = 0;

        for (Account value : accounts) {
            if (value.getAccountNumber().equals(accountNumber)) {
                return idx;
            }
            idx++;
        }
        return idx;
    }

    private static boolean existAccountNumber(String accountNumber) {
        for (Account value : accounts) {
            if (value == null) {
                return false;
            }
            if (value.getAccountNumber().equals(accountNumber)) {
                return true;
            }
        }
        return false;
    }


    private static void findAllAccount() {
        for (Account value : accounts) {
            if (value == null) {
                break;
            }
            System.out.println(value.getAccountNumber() + "\t" + value.getName() + "\t" + value.getDeposit());
        }
    }

    private static void optionListMessage() {
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

    private static void selectedOptionMessage(int choiceNumber) {
        System.out.println("-----------");
        System.out.println(optionAccountList[choiceNumber - 1]);
        System.out.println("-----------");
    }


    public static Account[] getAccounts() {
        return accounts;
    }

    public static int getIdx() {
        return idx;
    }
}