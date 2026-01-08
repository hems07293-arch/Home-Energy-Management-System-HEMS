package com.project.hems.auth_service_hems.GridDataGenerated;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class SiteIdGenerator implements IdentifierGenerator {

    private static final String PREFIX = "SITE_";
    private static final int TOTAL_DIGITS = 12;

    @Override
    public Serializable generate(
            SharedSessionContractImplementor session,
            Object object
    ) {

        // 1. Generate unique number from MySQL
        session.createNativeMutationQuery(
                "INSERT INTO site_sequence VALUES ()"
        ).executeUpdate();

        Long sequenceValue = ((Number) session
                .createNativeQuery("SELECT LAST_INSERT_ID()")
                .getSingleResult())
                .longValue();

        // 2. Zero-pad to 12 digits
        String paddedNumber = String.format("%0" + TOTAL_DIGITS + "d", sequenceValue);

        // 3. Final ID
        return PREFIX + paddedNumber;
    }
}
