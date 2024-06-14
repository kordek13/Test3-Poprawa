package Zadanie1;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        try {
            StringContainer st = new StringContainer("\\d{2}[-]\\d{3}", true);

            st.add("02-495");
            try {
                st.add("02-495");
            } catch (DuplicatedElementOnListException e) {
                System.out.println(e.getMessage());
            }
            st.add("01-120");
            st.add("05-123");
            st.add("00-000");
            try {
                st.add("ala ma kota");
            } catch (InvalidStringContainerValueException e) {
                System.out.println(e.getMessage());
            }

            for (int i = 0; i < st.size(); i++) {
                System.out.println(st.get(i));
            }

            st.remove(0);
            st.remove("00-000");

            System.out.println("po usunieciu");
            for (int i = 0; i < st.size(); i++) {
                System.out.println(st.get(i));
            }

            LocalDateTime dateFrom = LocalDateTime.now().minusDays(1);
            LocalDateTime dateTo = LocalDateTime.now().plusDays(1);
            StringContainer stBetween = st.getDataBetween(dateFrom, dateTo);
            System.out.println("Between dates:");
            for (int i = 0; i < stBetween.size(); i++) {
                System.out.println(stBetween.get(i));
            }

            st.storeToFile("postalCodes.txt");
            StringContainer fromFile = StringContainer.fromFile("postalCodes.txt");
            System.out.println("From file:");
            for (int i = 0; i < fromFile.size(); i++) {
                System.out.println(fromFile.get(i));
            }
        } catch (InvalidStringContainerPatternException e) {
            System.out.println(e.getMessage());
        }
    }

}