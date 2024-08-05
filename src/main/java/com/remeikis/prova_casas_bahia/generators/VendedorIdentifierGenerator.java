package com.remeikis.prova_casas_bahia.generators;

import com.remeikis.prova_casas_bahia.models.Vendedor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.query.Query;

public class VendedorIdentifierGenerator implements IdentifierGenerator {

    private static final String FORMAT = "%d_%s";

    @Override
    public Object generate(SharedSessionContractImplementor session, Object o) {
        String tipo = ((Vendedor) o).getTipoContratacao().name();

        // Busca ultimo ID do tipo
        Query<Long> query = session.createQuery("select max(cast(substring(id, 1, length(id) - 2) as long)) from Vendedor where id like :prefix", Long.class);
        query.setParameter("prefix", "%_" + tipo);
        Long maxId = query.getSingleResult();

        long nextId = (maxId == null) ? 1 : maxId + 1;

        return String.format(FORMAT, nextId, tipo);
    }
}
