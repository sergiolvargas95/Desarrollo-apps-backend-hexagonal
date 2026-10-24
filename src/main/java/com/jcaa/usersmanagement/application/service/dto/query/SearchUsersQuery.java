package com.jcaa.usersmanagement.application.service.dto.query;

/**
 * Query para la búsqueda de usuarios por nombre y/o email.
 *
 * <p>Ambos parámetros son opcionales. {@code null} o blank significa "sin filtro".
 * La búsqueda es parcial (LIKE %valor%) para facilitar búsquedas por fragmento.
 * Combinaciones posibles:
 * <ul>
 *   <li>nombre=null, email=null → todos los usuarios</li>
 *   <li>nombre=X,   email=null → filtrar solo por nombre</li>
 *   <li>nombre=null, email=X  → filtrar solo por email</li>
 *   <li>nombre=X,   email=X   → filtrar por ambos</li>
 * </ul>
 */
public record SearchUsersQuery(String nombre, String email) {}
