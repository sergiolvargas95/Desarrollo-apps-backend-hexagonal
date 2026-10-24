package com.jcaa.usersmanagement.application.service.dto.query;

/**
 * Query para la búsqueda filtrada de cursos.
 *
 * <p>Ambos parámetros son opcionales. {@code null} significa "sin filtro".
 * Combinaciones posibles:
 * <ul>
 *   <li>nivel=null, modalidad=null  → todos los cursos</li>
 *   <li>nivel=X,   modalidad=null  → filtrar solo por nivel</li>
 *   <li>nivel=null, modalidad=X    → filtrar solo por modalidad</li>
 *   <li>nivel=X,   modalidad=X    → filtrar por ambos</li>
 * </ul>
 */
public record SearchCursosQuery(String nivel, String modalidad) {}
