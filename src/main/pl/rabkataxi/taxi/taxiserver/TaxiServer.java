package pl.rabkataxi.taxi.taxiserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaxiServer {
    public static final int CUSTOMER_SERVER_SOCKET_NUMBER = 5000;

    private static final Logger logger = Logger.getLogger(TaxiServer.class.getName());

    ArrayList<Socket> customerSocketList;

    ArrayList<Customer> customerConnectedList;

    public static void main(String[] args) {
        TaxiServer taxiServer = new TaxiServer();
        taxiServer.start();
    }

    TaxiServer() {
        logger.info("TaxiServer start");
    }

    private void start() {
        customerInit();
    }

    private void customerInit() {
        customerSocketList = new ArrayList();
        try {
            ServerSocket serverSocket = new ServerSocket(CUSTOMER_SERVER_SOCKET_NUMBER);

            while (true) {
                Socket socket = serverSocket.accept();
                customerConnectedHandling(socket);
            }
        } catch (IOException ex) {
            logger.info("TaxiServer fail");
            ex.printStackTrace();
        }
    }

    private void customerConnectedHandling(Socket socket) {
        logger.info("customer connected");

        customerSocketList.add(socket);

        Customer customer = new Customer(socket);
        customerConnectedList.add(customer);

        Thread thread = new Thread(customer);
        thread.start();
    }
}
