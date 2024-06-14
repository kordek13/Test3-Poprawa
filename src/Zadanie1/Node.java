package Zadanie1;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Node implements Serializable {
    String value;
    LocalDateTime timestamp;
    Node next;

    Node(String value, LocalDateTime timestamp) {
        this.value = value;
        this.timestamp = timestamp;
        this.next = null;
    }
}
