package br.com.compasso.backend.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import springfox.documentation.annotations.ApiIgnore;

import java.awt.print.Pageable;
import java.util.List;

public interface IController<G, P, U> {

    @ApiOperation(value = "Retorna uma página do objeto.", responseContainer = "content")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma página do objeto."),
            @ApiResponse(code = 204, message = "Nenhum dado foi encontrado."),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção."), })
    ResponseEntity<Page<G>> getObjectList(@ApiIgnore Pageable page, List<Long> listaID);

    @ApiOperation(value = "Retorna um Objeto", responseContainer = "content")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o objeto."),
            @ApiResponse(code = 204, message = "Nenhum dado foi encontrado."),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção."), })
    ResponseEntity<G> getObject(@ApiParam(value = "Identificador do registro.", required = true) Long id);

    @ApiOperation(value = "Cria um objeto novo")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "O objeto foi criado."),
            @ApiResponse(code = 400, message = "Dados da requisição inválidos."),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção."), })
    ResponseEntity<G> postObject(@ApiParam(value = "Objeto a ser incluido.", required = true) P dto);

    @ApiOperation(value = "Atualiza um objeto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de objetos."),
            @ApiResponse(code = 204, message = "Nenhum dado foi encontrado."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção."), })
    ResponseEntity<G> updateObject(@ApiParam(value = "Identificador do registro.", required = true) Long id,
                                   @ApiParam(value = "Objeto a ser alterado.", required = true) U dto);

    @ApiOperation(value = "Remove um objeto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Registro excluído com sucesso."),
            @ApiResponse(code = 204, message = "Nenhuma informação foi encontrada."),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
    ResponseEntity<Void> deleteObject(@ApiParam(value = "Chave do Registro", required = true) Long id);
}
