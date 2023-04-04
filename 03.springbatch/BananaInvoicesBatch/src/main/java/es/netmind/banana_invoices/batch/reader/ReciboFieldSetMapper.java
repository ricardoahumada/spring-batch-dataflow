package es.netmind.banana_invoices.batch.reader;

import es.netmind.banana_invoices.models.Recibo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class ReciboFieldSetMapper implements FieldSetMapper<Recibo> {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public Recibo mapFieldSet(FieldSet fieldSet) throws BindException {
        Recibo rec = new Recibo();
        //rec.setName(fieldSet.readString("name"));
        return rec;
    }
}
