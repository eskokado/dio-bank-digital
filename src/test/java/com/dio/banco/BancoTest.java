package com.dio.banco;

import com.dio.exception.DomainException;
import com.dio.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BancoTest {
    @Test
    public void givenAValidParams_whenCallNewBanco_thenInstantiateBanco() {
        final var expectedNumero = "123456-3";
        final var expectedNome = "Banco 1";
        final var expectedIsAtivo = true;

        final var actualBanco = Banco.newBanco(expectedNumero, expectedNome, expectedIsAtivo);

        Assertions.assertNotNull(actualBanco);
        Assertions.assertNotNull(actualBanco.getId());
        Assertions.assertEquals(expectedNumero, actualBanco.getNumero());
        Assertions.assertEquals(expectedNome, actualBanco.getNome());
        Assertions.assertEquals(expectedIsAtivo, actualBanco.isAtivo());
        Assertions.assertNotNull(actualBanco.getCreatedAt());
        Assertions.assertNotNull(actualBanco.getUpdatedAt());
        Assertions.assertNull(actualBanco.getDeletedAt());
    }
    @Test
    public void givenAnInvalidNullNumero_whenCallNewBancoAndValidate_thenShouldReceveidErrors() {
        final String expectedNumero = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'numero' should not be null";
        final var expectedNome = "Banco 1";
        final var expectedIsAtivo = true;

        final var actualBanco = Banco.newBanco(expectedNumero, expectedNome, expectedIsAtivo);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualBanco.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenAnInvalidEmptyNumero_whenCallNewBancoAndValidate_thenShouldReceveidErrors() {
        final var expectedNumero = "  ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'numero' should not be empty";
        final var expectedNome = "Banco 1";
        final var expectedIsAtivo = true;

        final var actualBanco = Banco.newBanco(expectedNumero, expectedNome, expectedIsAtivo);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualBanco.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenAnInvalidNumeroLengthLessThan3_whenCallNewCategoryAndValidate_thenShouldReceveidErrors() {
        final var expectedNumero = "123";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'numero' deve ser entre 8 and 20 characters";
        final var expectedNome = "Banco 1";
        final var expectedIsAtivo = true;

        final var actualBanco = Banco.newBanco(expectedNumero, expectedNome, expectedIsAtivo);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualBanco.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenAnInvalidNumeroLengthMoreThan20_whenCallNewBancoAndValidate_thenShouldReceveidErrors() {
        final var expectedNumero = "1234567890123456789012345";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'numero' deve ser entre 8 and 20 characters";
        final var expectedNome = "Banco 1";
        final var expectedIsAtivo = true;

        final var actualBanco = Banco.newBanco(expectedNumero, expectedNome, expectedIsAtivo);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualBanco.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }
    @Test
    public void givenAValidFalseIsAtivo_whenCallNewBancoAndValidate_thenShouldReceveidErrors() {
        final var expectedNumero = "1234567890";
        final var expectedNome = "Banco 1";
        final var expectedIsAtivo = false;

        final var actualBanco = Banco.newBanco(expectedNumero, expectedNome, expectedIsAtivo);

        Assertions.assertDoesNotThrow(() -> actualBanco.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(actualBanco);
        Assertions.assertNotNull(actualBanco.getId());
        Assertions.assertEquals(expectedNumero, actualBanco.getNumero());
        Assertions.assertEquals(expectedNome, actualBanco.getNome());
        Assertions.assertEquals(expectedIsAtivo, actualBanco.isAtivo());
        Assertions.assertNotNull(actualBanco.getCreatedAt());
        Assertions.assertNotNull(actualBanco.getUpdatedAt());
        Assertions.assertNotNull(actualBanco.getDeletedAt());
    }

    @Test
    public void givenAValidAtivarBanco_whenCallDesativar_thenReturnBancoDesativado() {
        final var expectedNumero = "1234567890";
        final var expectedNome = "Banco 1";
        final var expectedIsAtivo = false;

        final var banco = Banco.newBanco(expectedNumero, expectedNome, true);

        Assertions.assertDoesNotThrow(() -> banco.validate(new ThrowsValidationHandler()));

        final var createdAt = banco.getCreatedAt();
        final var updatedAt = banco.getUpdatedAt();

        Assertions.assertTrue(banco.isAtivo());
        Assertions.assertNull(banco.getDeletedAt());

        final var actualBanco = banco.desativar();

        Assertions.assertDoesNotThrow(() -> actualBanco.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(banco.getId(), actualBanco.getId());
        Assertions.assertEquals(expectedNumero, actualBanco.getNumero());
        Assertions.assertEquals(expectedNome, actualBanco.getNome());
        Assertions.assertEquals(expectedIsAtivo, actualBanco.isAtivo());
        Assertions.assertEquals(createdAt, actualBanco.getCreatedAt());
        Assertions.assertTrue(actualBanco.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(actualBanco.getDeletedAt());
    }
    @Test
    public void givenAValidDesativadoBanco_whenCallAtivar_thenReturnBancoAtivado() {
        final var expectedNumero = "1234567890";
        final var expectedNome = "Banco 1";
        final var expectedIsAtivo = true;

        final var banco = Banco.newBanco(expectedNumero, expectedNome, false);

        Assertions.assertDoesNotThrow(() -> banco.validate(new ThrowsValidationHandler()));

        final var createdAt = banco.getCreatedAt();
        final var updatedAt = banco.getUpdatedAt();

        Assertions.assertFalse(banco.isAtivo());
        Assertions.assertNotNull(banco.getDeletedAt());

        final var actualBanco = banco.ativar();

        Assertions.assertDoesNotThrow(() -> actualBanco.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(banco.getId(), actualBanco.getId());
        Assertions.assertEquals(expectedNumero, actualBanco.getNumero());
        Assertions.assertEquals(expectedNome, actualBanco.getNome());
        Assertions.assertEquals(expectedIsAtivo, actualBanco.isAtivo());
        Assertions.assertEquals(createdAt, actualBanco.getCreatedAt());
        Assertions.assertTrue(actualBanco.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actualBanco.getDeletedAt());
    }

    @Test
    public void givenAValidBanco_whenCallUpdate_thenReturnBancoUpdated() {
        final var expectedNumero = "1234567890";
        final var expectedNome = "Banco 1";
        final var expectedIsAtivo = true;

        final var banco = Banco.newBanco("12345678", "Bank", false);

        Assertions.assertDoesNotThrow(() -> banco.validate(new ThrowsValidationHandler()));

        final var createdAt = banco.getCreatedAt();
        final var updatedAt = banco.getUpdatedAt();

        final var actualBanco = banco.update(expectedNumero, expectedNome, expectedIsAtivo);

        Assertions.assertDoesNotThrow(() -> actualBanco.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(banco.getId(), actualBanco.getId());
        Assertions.assertEquals(expectedNumero, actualBanco.getNumero());
        Assertions.assertEquals(expectedNome, actualBanco.getNome());
        Assertions.assertEquals(expectedIsAtivo, actualBanco.isAtivo());
        Assertions.assertEquals(createdAt, actualBanco.getCreatedAt());
        Assertions.assertTrue(actualBanco.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actualBanco.getDeletedAt());
    }
    @Test
    public void givenAValidBanco_whenCallUpdateToDesativo_thenReturnBancoUpdated() {

        final var expectedNumero = "1234567899";
        final var expectedNome = "Banco 1";
        final var expectedIsAtivo = false;

        final var banco = Banco.newBanco("12345678", "Bank 1", true);

        Assertions.assertDoesNotThrow(() -> banco.validate(new ThrowsValidationHandler()));
        Assertions.assertTrue(banco.isAtivo());
        Assertions.assertNull(banco.getDeletedAt());

        final var createdAt = banco.getCreatedAt();
        final var updatedAt = banco.getUpdatedAt();

        final var actualBanco = banco.update(expectedNumero, expectedNome, expectedIsAtivo);

        Assertions.assertDoesNotThrow(() -> actualBanco.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(banco.getId(), actualBanco.getId());
        Assertions.assertEquals(expectedNumero, actualBanco.getNumero());
        Assertions.assertEquals(expectedNome, actualBanco.getNome());
        Assertions.assertEquals(expectedIsAtivo, actualBanco.isAtivo());
        Assertions.assertEquals(createdAt, actualBanco.getCreatedAt());
        Assertions.assertTrue(actualBanco.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(actualBanco.getDeletedAt());
    }
    @Test
    public void givenAValidBanco_whenCallUpdateWithInvalidParams_thenReturnBancoUpdated() {

        final String expectedNumero = null;
        final var expectedNome = "Banco 1";
        final var expectedIsAtivo = true;

        final var banco = Banco.newBanco("12345678", "Banck 1", false);

        final var createdAt = banco.getCreatedAt();
        final var updatedAt = banco.getUpdatedAt();

        final var actualBanco = banco.update(expectedNumero, expectedNome, expectedIsAtivo);

        Assertions.assertEquals(banco.getId(), actualBanco.getId());
        Assertions.assertEquals(expectedNumero, actualBanco.getNumero());
        Assertions.assertEquals(expectedNome, actualBanco.getNome());
        Assertions.assertEquals(expectedIsAtivo, actualBanco.isAtivo());
        Assertions.assertEquals(createdAt, actualBanco.getCreatedAt());
        Assertions.assertTrue(actualBanco.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actualBanco.getDeletedAt());
    }
}
