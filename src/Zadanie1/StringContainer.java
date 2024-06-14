package Zadanie1;

import java.io.*;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class StringContainer implements Serializable {
    private transient Node head;
    private int size;
    private Pattern pattern;
    private boolean duplicatedNotAllowed;

    public StringContainer(String patternStr, boolean duplicatedNotAllowed) {
        try {
            this.pattern = Pattern.compile(patternStr);
        } catch (PatternSyntaxException e) {
            throw new InvalidStringContainerPatternException("Invalid pattern: " + patternStr);
        }
        this.head = null;
        this.size = 0;
        this.duplicatedNotAllowed = duplicatedNotAllowed;
    }

    public StringContainer(String patternStr) {
        this(patternStr, false);
    }

    public void add(String value) {
        Matcher matcher = pattern.matcher(value);
        if (!matcher.matches()) {
            throw new InvalidStringContainerValueException("Invalid value: " + value);
        }
        if (duplicatedNotAllowed && contains(value)) {
            throw new DuplicatedElementOnListException("Duplicate value: " + value);
        }
        Node newNode = new Node(value, LocalDateTime.now());
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == 0) {
            head = head.next;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        size--;
    }

    public void remove(String value) {
        if (head == null) {
            throw new InvalidStringContainerValueException("Value not found: " + value);
        }
        if (head.value.equals(value)) {
            head = head.next;
            size--;
            return;
        }
        Node current = head;
        while (current.next != null && !current.next.value.equals(value)) {
            current = current.next;
        }
        if (current.next == null) {
            throw new InvalidStringContainerValueException("Value not found: " + value);
        }
        current.next = current.next.next;
        size--;
    }

    public int size() {
        return size;
    }

    public StringContainer getDataBetween(LocalDateTime dateFrom, LocalDateTime dateTo) {
        StringContainer result = new StringContainer(pattern.pattern(), duplicatedNotAllowed);
        Node current = head;
        while (current != null) {
            if ((dateFrom == null || !current.timestamp.isBefore(dateFrom)) &&
                    (dateTo == null || !current.timestamp.isAfter(dateTo))) {
                result.add(current.value);
            }
            current = current.next;
        }
        return result;
    }

    private boolean contains(String value) {
        Node current = head;
        while (current != null) {
            if (current.value.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void storeToFile(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static StringContainer fromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (StringContainer) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        Node current = head;
        while (current != null) {
            out.writeObject(current.value);
            out.writeObject(current.timestamp);
            current = current.next;
        }
        out.writeObject(null);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        Node previous = null;
        while (true) {
            String value = (String) in.readObject();
            if (value == null) {
                break;
            }
            LocalDateTime timestamp = (LocalDateTime) in.readObject();
            Node current = new Node(value, timestamp);
            if (previous == null) {
                head = current;
            } else {
                previous.next = current;
            }
            previous = current;
        }
    }
}
