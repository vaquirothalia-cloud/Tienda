# Tienda Online - Final (Thalia)

Proyecto configurado para MySQL `tienda_online` con usuario root y la contraseña proporcionada.

## Cómo ejecutar
1. Asegúrate MySQL esté corriendo y exista la base `tienda_online`.
2. Importa el proyecto en IntelliJ (File -> Open) y selecciona la carpeta del proyecto (donde está pom.xml).
3. Ejecuta la clase `com.example.tiendaonline.TiendaApplication`.
4. Usa Postman para las colecciones y casos de prueba.

## Endpoints clave
- POST /clientes
- GET /clientes
- POST /productos
- POST /productos/{id}/categorias
- GET /productos?categoria=Nombre
- POST /clientes/{clienteId}/pedidos
- GET /clientes/{clienteId}/pedidos/all
- PUT /pedidos/{id}/estado?valor=ENVIADO


## Notas
- Evita crear clientes con el mismo email (campo único).
- No se permiten productos duplicados en un mismo pedido (se devuelve 400).
- Reglas simples para cambio de estado: CREADO -> ENVIADO -> ENTREGADO, CANCELADO es terminal.

