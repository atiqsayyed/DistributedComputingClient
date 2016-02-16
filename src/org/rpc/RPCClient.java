package org.rpc;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Vector;

public class RPCClient {
  public static void main(String[] args) {

    try {
      XmlRpcClient client = new XmlRpcClient();
      XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
      config.setServerURL(new URL("http://localhost:8080/"));
      client.setConfig(config);

      while (true) {
        displayMenu();
        int option = Integer.parseInt(readOption());

        switch (option) {
        case 1: {
          processLoginRequest(client);
          break;
        }
        case 2: {
          processRegistrationRequest(client);
          break;
        }
        case 3: {
          System.exit(0);
        }
        }
      }
    } catch (Exception exception) {
      System.err.println("JavaClient: " + exception);
    }
  }

  public static void displayMenu() {
    System.out.println("============================");
    System.out.println("|   SELECT Option          |");
    System.out.println("============================");
    System.out.println("| Options:                 |");
    System.out.println("|        1. Login          |");
    System.out.println("|        2. Register       |");
    System.out.println("|        3. Exit           |");
    System.out.println("============================");
  }

  public static String readOption() {
    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
    String s = null;
    try {
      s = bufferRead.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return s;
  }

  public static void processLoginRequest(XmlRpcClient client) {
    Vector params = new Vector();
    System.out.println("Please enter username for Login");
    String username = readOption();
    System.out.println("Please enter password for Login");
    String password = readOption();

    params.addElement(username);
    params.addElement(password);
    Object result = null;
    try {
      result = client.execute("rpc.login", params);
    } catch (XmlRpcException e) {
      e.printStackTrace();
    }

    String message = ((String) result);
    System.out.println(message);

  }

  public static void processRegistrationRequest(XmlRpcClient client) {
    Vector params = new Vector();
    System.out.println("Please enter username for Registration");
    String username = readOption();
    System.out.println("Please enter password for Registration");
    String password = readOption();

    params.addElement(username);
    params.addElement(password);
    Object result = null;
    try {
      result = client.execute("rpc.register", params);
    } catch (XmlRpcException e) {
      e.printStackTrace();
    }

    String message = ((String) result);
    System.out.println(message);

  }
}
