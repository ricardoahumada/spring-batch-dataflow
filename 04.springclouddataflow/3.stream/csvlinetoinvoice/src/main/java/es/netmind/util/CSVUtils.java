package es.netmind.util;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import es.netmind.model.Recibo;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;

public class CSVUtils {

    public static Recibo converCSVLineToRecibo(String line) throws IOException {

        StringReader linereader = new StringReader(line);

        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(Recibo.class);
        String[] memberFieldsToBindTo = {"id_remoto", "direccion_contacto", "direccion_envio", "fecha_emision", "fecha_vencimiento", "nombre_contacto", "propietario", "nombre_producto", "cantidad", "precio_unitario", "base_imponible", "impuestos", "total"};
        strategy.setColumnMapping(memberFieldsToBindTo);

        CsvToBean<Recibo> csvToBean = new CsvToBeanBuilder(linereader)
                .withMappingStrategy(strategy)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        Iterator<Recibo> myRecIterator = csvToBean.iterator();

        while (myRecIterator.hasNext()) {
            Recibo myRec = myRecIterator.next();
            System.out.println("Leeido:"+myRec);
            return myRec;
        }
        return null;
    }

}
