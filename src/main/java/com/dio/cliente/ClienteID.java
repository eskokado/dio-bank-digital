package com.dio.cliente;

import com.dio.Identifier;

import java.util.Objects;
import java.util.UUID;

public class ClienteID extends Identifier {
    private final String value;

    private ClienteID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static ClienteID unique() {
        return ClienteID.from(UUID.randomUUID());
    }

    public static ClienteID from(final String anId) {
        return new ClienteID(anId);
    }

    public static ClienteID from(final UUID anId) {
        return new ClienteID(anId.toString().toLowerCase());
    }
    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteID clienteID = (ClienteID) o;
        return Objects.equals(getValue(), clienteID.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
