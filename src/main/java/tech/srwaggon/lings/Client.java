package tech.srwaggon.lings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import tech.srwaggon.lings.net.Connection;

@Component
public class Client implements Runnable {

  private Connection connection;

  private final Logger logger = LoggerFactory.getLogger(Client.class);

  @PostConstruct
  private void init() {
    connection = new Connection("localhost", 31337);
    run();
  }

  @Override
  public void run() {
    try {
      while (true) {
        if (connection.hasLine()) {
          connection.readLine();
        }

        String moveMsg = String.format("move id 0 x %d y 0", 3);
        connection.send(moveMsg);
        Thread.sleep(1000);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
