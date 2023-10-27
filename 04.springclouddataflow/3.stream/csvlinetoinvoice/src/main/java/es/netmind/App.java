package es.netmind;

import es.netmind.model.Recibo;
import es.netmind.util.CSVUtils;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("INIT");

        try {
            Recibo rec = CSVUtils.converCSVLineToRecibo("1,PO Box 93683,Apt 1344,2022-06-23,2022-05-20,Quinlan Impy,41,Pickerel - Fillets,79,54,,7,4564.62");
            System.out.println(rec);
            System.out.println(rec.esValido());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
