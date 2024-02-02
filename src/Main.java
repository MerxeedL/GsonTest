import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Locale.filter;
import static java.util.stream.Collectors.toList;

public class Main {
    public static <T, E> void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();

        String fileName = "src//tickets.json";

        Path path = Paths.get(fileName);
        Scanner sc = new Scanner(path);

        while (sc.hasNext()) {
            sb.append(sc.next());
        }

        Gson test = new Gson();
        Tickets ticket = test.fromJson(sb.toString(), Tickets.class);

        List<Ticket> listTickets = ticket.getTickets();


        System.out.println("Carrier: TK");
        System.out.println(listTickets.stream()
                                   .filter(c -> c.getCarrier().equals("TK"))
                                   .filter(o -> o.getOrigin().equals("VVO"))
                                   .filter(d -> d.getDestination().equals("TLV"))
                                   .map(Ticket::getDifferentTime)
                                   .min(Comparator.naturalOrder())
                                   .get() + " minutes");
        System.out.println("----------------");

        System.out.println("Carrier: S7");
        System.out.println(listTickets.stream()
                                   .filter(c -> c.getCarrier().equals("S7"))
                                   .filter(o -> o.getOrigin().equals("VVO"))
                                   .filter(d -> d.getDestination().equals("TLV"))
                                   .map(Ticket::getDifferentTime)
                                   .min(Comparator.naturalOrder())
                                   .get() + " minutes");
        System.out.println("----------------");

        System.out.println("Carrier: SU");
        System.out.println(listTickets.stream()
                                   .filter(c -> c.getCarrier().equals("SU"))
                                   .filter(o -> o.getOrigin().equals("VVO"))
                                   .filter(d -> d.getDestination().equals("TLV"))
                                   .map(Ticket::getDifferentTime)
                                   .min(Comparator.naturalOrder())
                                   .get() + " minutes");
        System.out.println("----------------");

        System.out.println("Carrier: BA");
        System.out.println(listTickets.stream()
                                   .filter(c -> c.getCarrier().equals("BA"))
                                   .filter(o -> o.getOrigin().equals("VVO"))
                                   .filter(d -> d.getDestination().equals("TLV"))
                                   .map(Ticket::getDifferentTime)
                                   .min(Comparator.naturalOrder())
                                   .get() + " minutes");
        System.out.println("----------------");

        System.out.println(listTickets.stream()
                                   .filter(o -> o.getOrigin().equals("VVO"))
                                   .filter(d -> d.getDestination().equals("TLV"))
                                   .mapToInt(Ticket::getPrice).average()
                                   .getAsDouble() + " - average price");

        List<Integer> listPrices = listTickets.stream()
                .filter(o -> o.getOrigin().equals("VVO"))
                .filter(d -> d.getDestination().equals("TLV"))
                .mapToInt(Ticket::getPrice)
                .sorted()
                .collect(Collectors.toList());

        System.out.println(listPrices);

    }
}