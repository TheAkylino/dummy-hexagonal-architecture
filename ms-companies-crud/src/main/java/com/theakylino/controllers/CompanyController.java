package com.theakylino.controllers;

import com.theakylino.entities.Company;
import com.theakylino.services.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para la gestión de compañías.
 */
@RestController
@AllArgsConstructor
@RequestMapping(path = "company")
@Slf4j
@Tag(name = "Companies resource",
    description = "Servicios para la gestión de compañías")
public class CompanyController {

  private final CompanyService companyService;

  /**
   * Obtiene una compañía dado su nombre.
   *
   * @param name El nombre de la compañía a buscar.
   * @return La ResponseEntity con la compañía encontrada y el estado HTTP.
   */
  @Operation(summary = "Obtener una compañía por su nombre",
      description = "Devuelve una compañía dado su nombre",
      responses = {
          @ApiResponse(responseCode = "200", description = "Compañía encontrada exitosamente"),
          @ApiResponse(responseCode = "404", description = "Compañía no encontrada")
      })
  @GetMapping(path = "{name}")
  public ResponseEntity<Company> get(@PathVariable String name) {
    log.info("GET: company {}", name);
    return ResponseEntity.ok(this.companyService.readByName(name));
  }

  /**
   * Guarda una nueva compañía en la base de datos.
   *
   * @param company El objeto Company que contiene la información de la compañía a crear.
   * @return La ResponseEntity con la URI de la compañía creada y el estado HTTP.
   */
  @Operation(summary = "Guardar una nueva compañía",
      description = "Guarda una nueva compañía en la base de datos",
      responses = {
          @ApiResponse(responseCode = "201", description = "Compañía creada exitosamente"),
          @ApiResponse(responseCode = "400", description = "Solicitud inválida")
      })
  @PostMapping
  public ResponseEntity<Void> post(@RequestBody Company company) {
    log.info("POST: company {}", company.getName());
    return ResponseEntity.created(URI.create(companyService.create(company).getName())).build();
  }

  /**
   * Actualiza la información de una compañía existente.
   *
   * @param company El objeto Company que contiene la información actualizada.
   * @param name El nombre de la compañía a actualizar.
   * @return La ResponseEntity con la compañía actualizada y el estado HTTP.
   */
  @Operation(summary = "Actualizar una compañía por su nombre",
      description = "Actualiza la información de una compañía existente",
      responses = {
          @ApiResponse(responseCode = "200", description = "Compañía actualizada exitosamente"),
          @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
          @ApiResponse(responseCode = "404", description = "Compañía no encontrada")
      })
  @PutMapping(path = "{name}")
  public ResponseEntity<Company> put(@RequestBody Company company, @PathVariable String name) {
    log.info("PUT: company {}", name);
    return ResponseEntity.ok(companyService.update(company, name));
  }

  /**
   * Elimina una compañía de la base de datos.
   *
   * @param name El nombre de la compañía a eliminar.
   * @return La ResponseEntity con el estado HTTP correspondiente.
   */
  @Operation(summary = "Eliminar una compañía por su nombre",
      description = "Elimina una compañía de la base de datos",
      responses = {
          @ApiResponse(responseCode = "204", description = "Compañía eliminada exitosamente"),
          @ApiResponse(responseCode = "404", description = "Compañía no encontrada")
      })
  @DeleteMapping(path = "{name}")
  public ResponseEntity<?> delete(@PathVariable String name) {
    log.info("DELETE: company {}", name);
    this.companyService.delete(name);
    return ResponseEntity.noContent().build();
  }
}
