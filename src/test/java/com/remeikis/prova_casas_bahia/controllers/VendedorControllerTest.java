package com.remeikis.prova_casas_bahia.controllers;

import com.remeikis.prova_casas_bahia.models.FilialMock;
import com.remeikis.prova_casas_bahia.models.Vendedor;
import com.remeikis.prova_casas_bahia.models.VendedorMock;
import com.remeikis.prova_casas_bahia.models.dto.CreateVendedorDtoMock;
import com.remeikis.prova_casas_bahia.models.enums.TipoContratacao;
import com.remeikis.prova_casas_bahia.repositories.VendedorRepository;
import com.remeikis.prova_casas_bahia.services.FilialService;
import com.remeikis.prova_casas_bahia.services.VendedorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class VendedorControllerTest {
    @InjectMocks
    private VendedorService vendedorService;

    @Mock
    private VendedorRepository vendedorRepository;

    @Mock
    private FilialService filialService;

    @Captor
    private ArgumentCaptor<Vendedor> vendedorArgumentCaptor;

    @Nested
    class FindByMatricula {
        @Test
        @DisplayName("Should return Vendedor with success")
        public void shouldReturnVendedorWithSuccess() {
            // Arrange
            Vendedor vendedorMock = new Vendedor(
                "1_CLT",
                "Teste",
                new Date(),
                "111.111.111-11",
                "teste@email.com",
                TipoContratacao.CLT,
                1,
                null
            );

            doReturn(Optional.of(vendedorMock))
                .when(vendedorRepository).findById("1_CLT");

            // Act
            var output = vendedorService.findByMatricula("1_CLT");

            // Assert
            assertEquals(vendedorMock, output);
        }
    }

    @Nested
    class Create {
        @Test
        @DisplayName("Should create Vendedor with success")
        public void shouldCreateVendedorWithSuccess() {
            // Arrange
            var filialMock = FilialMock.single();

            var mock = VendedorMock.single();
            mock.setMatricula("1_CLT");
            mock.setFilial(filialMock);

            doReturn(mock)
                .when(vendedorRepository).save(vendedorArgumentCaptor.capture());

            doReturn(filialMock)
                .when(filialService).findById(mock.getIdFilial());

            var input = CreateVendedorDtoMock.single();

            // Act
            var output = vendedorService.create(input);

            // Assert
            assertNotNull(output);

            var captured = vendedorArgumentCaptor.getValue();
            assertNull(captured.getMatricula());
            assertEquals(mock.getMatricula(), output.getMatricula());
            assertEquals(mock.getDtNascimento(), output.getDtNascimento());
            assertEquals(mock.getIdentificador(), output.getIdentificador());
            assertEquals(mock.getEmail(), output.getEmail());
            assertEquals(mock.getTipoContratacao(), output.getTipoContratacao());
            assertEquals(mock.getIdFilial(), output.getIdFilial());
            assertEquals(filialMock, output.getFilial());
        }

        @Test
        @DisplayName("Should invalidate Filial")
        public void shouldInvalidateFilial() {
            // Arrange
            doReturn(null)
                .when(filialService).findById(1);

            var input = CreateVendedorDtoMock.single();

            // Act & Assert
            assertThrows(
                IllegalArgumentException.class,
                () -> vendedorService.create(input)
            );
        }

        @Test
        @DisplayName("Should invalidate CPF")
        public void shouldInvalidateCpf() {
            // Arrange
            var filialMock = FilialMock.single();

            doReturn(filialMock)
                .when(filialService).findById(1);

            var input = CreateVendedorDtoMock.single();
            input.setIdentificador("111.111.111-11");

            // Act & Assert
            assertThrows(
                IllegalArgumentException.class,
                () -> vendedorService.create(input)
            );
        }

        @Test
        @DisplayName("Should invalidate CPF by TipoContratacao PJ")
        public void shouldInvalidateCpfByTipoContratacaoPj() {
            // Arrange
            var filialMock = FilialMock.single();

            doReturn(filialMock)
                    .when(filialService).findById(1);

            var input = CreateVendedorDtoMock.single();
            input.setTipoContratacao(TipoContratacao.PJ);

            // Act & Assert
            assertThrows(
                IllegalArgumentException.class,
                () -> vendedorService.create(input)
            );
        }

        @Test
        @DisplayName("Should invalidate CNPJ")
        public void shouldInvalidateCnpj() {
            // Arrange
            var filialMock = FilialMock.single();

            doReturn(filialMock)
                .when(filialService).findById(1);

            var input = CreateVendedorDtoMock.single();
            input.setIdentificador("11.111.111/1111-11");
            input.setTipoContratacao(TipoContratacao.PJ);

            // Act & Assert
            assertThrows(
                IllegalArgumentException.class,
                () -> vendedorService.create(input)
            );
        }

        @Test
        @DisplayName("Should invalidate CNPJ by TipoContratacao CLT")
        public void shouldInvalidateCnpjByTipoContratacaoClt() {
            // Arrange
            var filialMock = FilialMock.single();

            doReturn(filialMock)
                .when(filialService).findById(1);

            var input = CreateVendedorDtoMock.single();
            input.setIdentificador("28.489.288/0001-29");

            // Act & Assert
            assertThrows(
                IllegalArgumentException.class,
                () -> vendedorService.create(input)
            );
        }
    }

    @Nested
    class CastCreateVendedorDtoToVendedor {
        @Test
        @DisplayName("Should cast DTO with success")
        public void shouldCastDtoWithSuccess() {
            // Arrange
            var input = CreateVendedorDtoMock.single();

            // Act
            var output = vendedorService.castCreateVendedorDtoToVendedor(input);

            // Assert
            assertNotNull(output);
            assertNull(output.getMatricula());
            assertEquals(input.getNome(), output.getNome());
            assertEquals(input.getDtNascimento(), output.getDtNascimento());
            assertEquals(input.getIdentificador(), output.getIdentificador());
            assertEquals(input.getEmail(), output.getEmail());
            assertEquals(input.getTipoContratacao(), output.getTipoContratacao());
            assertEquals(input.getIdFilial(), output.getIdFilial());
            assertNull(output.getFilial());
        }
    }
}