package com.dio.banco;

import com.dio.AggregateRoot;
import com.dio.validation.ValidationHandler;

import java.time.Instant;
import java.util.Objects;

public class Banco extends AggregateRoot<BancoID> implements Cloneable{
    private String numero;
    private String nome;
    private boolean ativo;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Banco(
            final BancoID id,
            final String numero,
            final String nome,
            final boolean isAtivo,
            final Instant creationDate,
            final Instant updateDate,
            final Instant deleteDate
    ) {
        super(id);
        this.numero = numero;
        this.nome = nome;
        this.ativo = isAtivo;
        this.createdAt = Objects.requireNonNull(creationDate, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(updateDate, "'updatedAt' should not be null");
        this.deletedAt = deleteDate;
    }


    public static Banco newBanco(
            final String numero,
            final String nome,
            final boolean isAtivo
    ) {
        final var id = BancoID.unique();
        final var now = Instant.now();
        final var deletedAt = isAtivo ? null : now;
        return new Banco(id, numero, nome, isAtivo, now, now, deletedAt);
    }

    public static Banco with(final Banco banco) {
        return new Banco(
                banco.getId(),
                banco.numero,
                banco.nome,
                banco.isAtivo(),
                banco.createdAt,
                banco.updatedAt,
                banco.deletedAt
        );
    }

    public static Banco with(
            final BancoID id,
            final String numero,
            final String nome,
            final boolean ativo,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        return new Banco(
                id,
                numero,
                nome,
                ativo,
                createdAt,
                updatedAt,
                deletedAt
        );
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new BancoValidator(this, handler).validate();
    }

    public Banco desativar() {
        if (getDeletedAt() == null) {
            this.deletedAt = Instant.now();
        }

        this.ativo = false;
        this.updatedAt = Instant.now();
        return this;
    }
    public Banco ativar() {
        this.deletedAt = null;
        this.ativo = true;
        this.updatedAt = Instant.now();
        return this;
    }

    public Banco update(final String numero, final String nome, final boolean isAtivo) {
        if (isAtivo) {
            this.ativar();
        } else {
            this.desativar();
        }
        this.numero = numero;
        this.nome = nome;
        this.updatedAt = Instant.now();
        return this;
    }

    @Override
    public BancoID getId() {
        return super.getId();
    }

    public String getNumero() {
        return numero;
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
    public Banco clone() {
        try {
            return (Banco) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
