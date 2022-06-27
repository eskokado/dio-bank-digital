package com.dio.cliente;

import com.dio.AggregateRoot;
import com.dio.validation.ValidationHandler;

import java.time.Instant;
import java.util.Objects;

public class Cliente extends AggregateRoot<ClienteID> implements Cloneable{
    private String nome;
    private boolean ativo;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Cliente(
            final ClienteID id,
            final String nome,
            final boolean isAtivo,
            final Instant creationDate,
            final Instant updateDate,
            final Instant deleteDate
    ) {
        super(id);
        this.nome = nome;
        this.ativo = isAtivo;
        this.createdAt = Objects.requireNonNull(creationDate, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(updateDate, "'updatedAt' should not be null");
        this.deletedAt = deleteDate;
    }


    public static Cliente newCliente(
            final String nome,
            final boolean isAtivo
    ) {
        final var id = ClienteID.unique();
        final var now = Instant.now();
        final var deletedAt = isAtivo ? null : now;
        return new Cliente(id, nome, isAtivo, now, now, deletedAt);
    }

    public static Cliente with(final Cliente cliente) {
        return new Cliente(
                cliente.getId(),
                cliente.nome,
                cliente.isAtivo(),
                cliente.createdAt,
                cliente.updatedAt,
                cliente.deletedAt
        );
    }

    public static Cliente with(
            final ClienteID id,
            final String nome,
            final boolean ativo,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        return new Cliente(
                id,
                nome,
                ativo,
                createdAt,
                updatedAt,
                deletedAt
        );
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new ClienteValidator(this, handler).validate();
    }

    public Cliente desativar() {
        if (getDeletedAt() == null) {
            this.deletedAt = Instant.now();
        }

        this.ativo = false;
        this.updatedAt = Instant.now();
        return this;
    }
    public Cliente ativar() {
        this.deletedAt = null;
        this.ativo = true;
        this.updatedAt = Instant.now();
        return this;
    }

    public Cliente update(final String nome, final boolean isAtivo) {
        if (isAtivo) {
            this.ativar();
        } else {
            this.desativar();
        }
        this.nome = nome;
        this.updatedAt = Instant.now();
        return this;
    }

    @Override
    public ClienteID getId() {
        return super.getId();
    }

    public String getNome() {
        return nome;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    @Override
    public Cliente clone() {
        try {
            return (Cliente) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
