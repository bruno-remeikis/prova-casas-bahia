package com.remeikis.prova_casas_bahia.sequences;

import com.remeikis.prova_casas_bahia.models.TipoContratacao;
import com.remeikis.prova_casas_bahia.models.Vendedor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.query.Query;

import java.text.DecimalFormat;

public class VendedorSequence implements IdentifierGenerator {

    //public static final String SEQUENCE_NAME = "vendedor_seq";
    private static final DecimalFormat FORMAT = new DecimalFormat("0000");

    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        TipoContratacao tipoContratacao = ((Vendedor) o).getTipoContratacao();

        if (tipoContratacao == null) {
            throw new IllegalArgumentException("TipoContratacao não pode ser nulo");
        }

        String tipo = tipoContratacao.name();

        // Consulta para obter o próximo número sequencial
        Query<Long> query = sharedSessionContractImplementor.createQuery("select max(cast(substring(id, 1, length(id) - 2) as long)) from Vendedor where id like :prefix", Long.class);
        query.setParameter("prefix", "%_" + tipo);
        Long maxId = query.getSingleResult();

        long nextId = (maxId == null) ? 1 : maxId + 1;

        return FORMAT.format(nextId) + "_" + tipo;
    }
}
