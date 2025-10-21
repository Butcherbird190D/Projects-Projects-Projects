package Projects.Assignment3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

class Customer {
    int arrival;
    int departure;

    public Customer(int arrival) {
        this.arrival = arrival;
    }
}

class Teller {
    int id;
    int queueIdx;
    int servedCount = 0;
    int totalTransTime = 0;
    int idleTime = 0;
    int idleStart = 0;
    boolean busy = false;
    int transStart = 0;
    int transDuration = 0;

    public Teller(int id, int queueIdx) {
        this.id = id;
        this.queueIdx = queueIdx;
        this.idleStart = 0;
    }
}

public class BankSimulation {

    static Random rand = new Random();

    static boolean customerArrives() {
        return rand.nextInt(4) == 1;
    }

    static boolean tellerReady() {
        return rand.nextInt(3) == 1;
    }

    static int randomTransTime() {
        return rand.nextInt(10) + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Bank Simulation Program");
        System.out.print("Enter closing time: ");
        int closeTime = sc.nextInt();
        System.out.print("Enter number of tellers: ");
        int tellerCount = sc.nextInt();
        System.out.print("Enter number of queues: ");
        int queueCount = sc.nextInt();

        Queue<Customer>[] queues = new LinkedList[queueCount];
        for (int i = 0; i < queueCount; i++) {
            queues[i] = new LinkedList<>();
        }

        Teller[] tellers = new Teller[tellerCount];
        for (int i = 0; i < tellerCount; i++) {
            int qIdx = (queueCount == 1) ? 0 : i;
            tellers[i] = new Teller(i + 1, qIdx);
        }

        int time = 0, totalServed = 0, totalWaitTime = 0;
        boolean shutdown = false;

        while (!shutdown) {
            time++;

            if (time <= closeTime && customerArrives()) {
                Customer newCust = new Customer(time);
                int minQueue = 0;
                if (queueCount > 1) {
                    for (int i = 1; i < queueCount; i++) {
                        if (queues[i].size() < queues[minQueue].size()) {
                            minQueue = i;
                        }
                    }
                }
                queues[minQueue].offer(newCust);
            }

            for (Teller teller : tellers) {
                if (teller.busy && teller.transStart + teller.transDuration <= time) {
                    teller.servedCount++;
                    teller.totalTransTime += teller.transDuration;
                    totalServed++;
                    teller.busy = false;
                    teller.idleStart = time;
                }
                if (!teller.busy) {
                    teller.idleTime++;
                    if (tellerReady() && !queues[teller.queueIdx].isEmpty()) {
                        Customer cust = queues[teller.queueIdx].poll();
                        totalWaitTime += (time - cust.arrival);
                        teller.busy = true;
                        teller.transStart = time;
                        teller.transDuration = randomTransTime();
                    }
                }
            }

            boolean queuesEmpty = true;
            for (Queue<Customer> q : queues) {
                if (!q.isEmpty()) {
                    queuesEmpty = false;
                    break;
                }
            }
            boolean allIdle = true;
            for (Teller teller : tellers) {
                if (teller.busy) {
                    allIdle = false;
                    break;
                }
            }
            if (time > closeTime && queuesEmpty && allIdle) {
                shutdown = true;
            }
        }

        double avgWaitTime = totalServed > 0 ? (double) totalWaitTime / totalServed : 0;
        double totalTransSum = 0;
        int sumServed = 0;
        int totalIdleSum = 0;
        for (Teller teller : tellers) {
            totalTransSum += teller.totalTransTime;
            sumServed += teller.servedCount;
            totalIdleSum += teller.idleTime;
        }
        double avgTransTime = sumServed > 0 ? totalTransSum / sumServed : 0;
        double avgIdleTime = (double) totalIdleSum / tellers.length;

        System.out.println("=================================================================");
        System.out.println("                 RESULTS OF SIMULATION                  ");
        System.out.println("                 ---------------------                  ");
        System.out.println();
        System.out.printf("Queues used:                 %d%n", queueCount);
        System.out.printf("Tellers used:                %d%n", tellerCount);
        System.out.printf("Customers served:            %d%n", totalServed);
        System.out.printf("Avg wait time:               %.2f%n", avgWaitTime);
        System.out.println();
        System.out.println("Teller Statistics>");
        System.out.printf("      %-8s %-16s %-15s %-15s%n", "Teller#", "#Served", "AvgTransTime", "TotalIdleTime");
        for (Teller teller : tellers) {
            double tellerAvgTrans = teller.servedCount > 0 ? (double) teller.totalTransTime / teller.servedCount : 0;
            System.out.printf("      %-8d %-16d %-15.2f %-15d%n", teller.id, teller.servedCount, tellerAvgTrans, teller.idleTime);
        }
        System.out.printf("      Averages:      %.2f          %.2f          %.2f%n", 
                (double) sumServed / tellers.length, avgTransTime, avgIdleTime);
        System.out.println();
        System.out.println("Bank closed at: " + time);
        System.out.println("=================================================================");

        sc.close();
    }
}
