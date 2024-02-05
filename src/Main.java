import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args){
        try {
            FileWriter writer = new FileWriter("src//FileForWrite", true);

            StringBuilder sb = new StringBuilder();
            double median;

            String fileName = "src//tickets.json";

            Path path = Paths.get(fileName);
            Scanner sc = new Scanner(path);

            while (sc.hasNext()) {
                sb.append(sc.next());
            }

            Gson test = new Gson();
            Tickets ticket = test.fromJson(sb.toString(), Tickets.class);

            List<Ticket> listTickets = ticket.getTickets();

            writer.write("Carrier: TK\n");
            writer.write(listTickets.stream()
                                 .filter(c -> c.getCarrier().equals("TK"))
                                 .filter(o -> o.getOrigin().equals("VVO"))
                                 .filter(d -> d.getDestination().equals("TLV"))
                                 .map(Ticket::getDifferentTime)
                                 .min(Comparator.naturalOrder())
                                 .get() + " minutes\n");
            writer.write("----------------\n");

            writer.write("Carrier: S7\n");
            writer.write(listTickets.stream()
                                 .filter(c -> c.getCarrier().equals("S7"))
                                 .filter(o -> o.getOrigin().equals("VVO"))
                                 .filter(d -> d.getDestination().equals("TLV"))
                                 .map(Ticket::getDifferentTime)
                                 .min(Comparator.naturalOrder())
                                 .get() + " minutes\n");
            writer.write("----------------\n");

            writer.write("Carrier: SU\n");
            writer.write(listTickets.stream()
                                 .filter(c -> c.getCarrier().equals("SU"))
                                 .filter(o -> o.getOrigin().equals("VVO"))
                                 .filter(d -> d.getDestination().equals("TLV"))
                                 .map(Ticket::getDifferentTime)
                                 .min(Comparator.naturalOrder())
                                 .get() + " minutes\n");
            writer.write("----------------\n");

            writer.write("Carrier: BA\n");
            writer.write(listTickets.stream()
                                 .filter(c -> c.getCarrier().equals("BA"))
                                 .filter(o -> o.getOrigin().equals("VVO"))
                                 .filter(d -> d.getDestination().equals("TLV"))
                                 .map(Ticket::getDifferentTime)
                                 .min(Comparator.naturalOrder())
                                 .get() + " minutes\n");
            writer.write("----------------\n");

            List<Integer> listPrices = listTickets.stream()
                    .filter(od -> od.getOrigin().equals("VVO") && od.getDestination().equals("TLV"))
                    .mapToInt(Ticket::getPrice)
                    .sorted()
                    .boxed()
                    .collect(Collectors.toList());

            OptionalDouble avg = listPrices.stream()
                    .mapToDouble(a -> a)
                    .average();

            writer.write(avg.getAsDouble() + " - average price\n");
            double average = avg.getAsDouble();

            if (listPrices.size() % 2 == 0) {
                median = (listPrices.get(listPrices.size() / 2 - 1) + listPrices.get(listPrices.size() / 2)) / 2.0;
            } else {
                median = listPrices.get(listPrices.size() / 2);
            }
            writer.write(median + " - median price\n");
            double result = average - median;

            writer.write(result + " - difference between average price and median\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Warning with adding in a file");
        }
    }
}