# Segundo Parcial – Plataforma de Reservas de Espacios Coworking

## Indicaciones Generales

Lea detenidamente el enunciado y asegúrese de comprender los requisitos funcionales, las reglas de negocio y los entregables antes de comenzar. El ejercicio debe resolverse **individual**, aplicando correctamente la **arquitectura N-Capas** y las buenas prácticas vistas en clase.

> ⚠️ **No se aceptarán commits realizados después de la hora límite establecida para el parcial.**

---

## Plataforma de Reservas de Espacios Coworking

**Descripción:** Desarrollar una API REST que permita gestionar los espacios de trabajo de una plataforma de coworking, implementando las operaciones básicas de CRUD, validaciones, reglas de negocio y manejo adecuado de respuestas HTTP.

---

## Entidad `Space`

| Campo | Tipo | Reglas |
|-------|------|--------|
| `id` | Long | Autogenerado |
| `name` | String | Requerido, único, no vacío |
| `description` | String | Opcional |
| `type` | Enum | Uno de: `DESK`, `PRIVATE_OFFICE`, `MEETING_ROOM`, `EVENT_HALL` |
| `capacity` | Integer | Obligatorio, ≥ 1 |
| `pricePerHour` | BigDecimal | Obligatorio, mayor a 0 |
| `available` | Boolean | Se gestiona según ocupación actual |
| `floor` | Integer | Obligatorio, ≥ 0 |
| `amenities` | String | Opcional (ej: "WiFi, Proyector, Aire acondicionado") |

---

## Reglas de Negocio

- **Nombre único:** No se permite registrar dos espacios con el mismo nombre sin importar mayúsculas/minúsculas. No puede existir un espacio sin nombre ni type.
- **Precio válido:** El precio por hora debe ser mayor a cero. No se permiten espacios gratuitos.
- **Capacidad mínima:** Todo espacio debe tener capacidad de al menos 1 persona.
- **Disponibilidad:** Si `available = false`, el espacio no puede ser reservado. Un espacio solo puede marcarse como `available = false` manualmente por administración o por tener una reserva activa.
- **Protección de eliminación:** No se puede eliminar un espacio que tenga `available = false` (indica que está en uso o bloqueado).
- **Actualización de precio:** El nuevo precio debe ser siempre mayor a 0; no se permite bajar el precio a 0 o valores negativos.
- **Filtrado:** El listado debe soportar filtros por `type` y `available`.
    - Ejemplo: `GET /api/spaces?type=MEETING_ROOM&available=true`
- **Piso válido:** El número de piso no puede ser negativo.

---

## Operaciones CRUD

| Método HTTP | Endpoint | Descripción |
|-------------|----------|-------------|
| `POST` | `/api/spaces` | Registrar un nuevo espacio |
| `GET` | `/api/spaces` | Listar todos los espacios (con filtros opcionales) |
| `GET` | `/api/spaces/{id}` | Obtener un espacio por ID |
| `PUT` | `/api/spaces/{id}` | Actualizar la información de un espacio |
| `DELETE` | `/api/spaces/{id}` | Eliminar un espacio (respetando reglas de negocio) |

---

## Manejo de Excepciones

Se requieren **al menos 2 excepciones personalizadas**, por ejemplo:

- `ResourceNotFoundException` → HTTP `404`
- `BusinessRuleException` → HTTP `400`

Deben manejarse con un `@RestControllerAdvice` global.

---

## Códigos de Estado HTTP Esperados

| Situación | Código |
|-----------|--------|
| Creación exitosa | `201 Created` |
| Consulta exitosa | `200 OK` |
| Recurso no encontrado | `404 Not Found` |
| Datos inválidos / regla de negocio violada | `400 Bad Request` |
| Eliminación exitosa | `200 OK` o `204 No Content` |

---

## Entregables

Repositorio en **GitHub** con el nombre:

```
pnc-segundo-parcial-<carnet_estudiante>
```

El repositorio debe contener:

1. Código fuente del proyecto Spring Boot.

---

## Rúbrica de Evaluación (90% práctica)

| Criterio                                                    | Porcentaje |
|-------------------------------------------------------------|-----------|
| CRUD completo y funcional                                   | 22% |
| Reglas de negocio implementadas correctamente               | 23% |
| Uso de anotaciones, entities, DTOs y validaciones           | 15% |
| Estructura en capas y claridad del código                   | 10% |
| Manejo de excepciones personalizado (mínimo 2)              | 10% |
| Conexión a base de datos                                    | 5% |
| Buen manejo de códigos de estado HTTP (404, 400, 201, etc.) | 5% |
| **Total**                                                   | **90%** |

> El **10% restante** de la nota del parcial corresponde a la parte teórica, evaluada por separado.