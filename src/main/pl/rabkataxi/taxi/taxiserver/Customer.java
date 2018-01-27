package pl.rabkataxi.taxi.taxiserver;

import java.net.Socket;

public class Customer implements Runnable {
    private Socket socket;

    Customer(Socket socket) {
        this.socket = socket;
    }

    public void run() {

    }
}
