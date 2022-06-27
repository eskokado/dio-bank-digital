package com.dio.banco;

import com.dio.Identifier;

import java.util.Objects;
import java.util.UUID;

public class BancoID extends Identifier {
    private final String value;

    private BancoID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static BancoID unique() {
        return BancoID.from(UUID.randomUUID());
    }

    public static BancoID from(final String anId) {
        return new BancoID(anId);
    }

    public static BancoID from(final UUID anId) {
        return new BancoID(anId.toString().toLowerCase());
    }
    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BancoID bancoID = (BancoID) o;
        return Objects.equals(getValue(), bancoID.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
