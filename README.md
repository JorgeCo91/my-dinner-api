# my-dinner-api
API REST development in Java Spring Framework

La BD esta dentro de la carpeta de src/main/resources

- Request para crear un cliente (POST)
```json
{
    "nombre": "jorge",
    "apellidoPaterno": "Serrano",
    "apellidoMaterno": "Peña",
    "email": "jorge@carlos.com",
    "domicilioPrincipal": "mar egeo",
    "telefono": "5545999"
}
```

- Request para editar un cliente (PUT)
```json
{
    "id": 3,
    "nombre": "carlos",
    "apellidoPaterno": "Serrano",
    "apellidoMaterno": "Peña",
    "email": "carlos@carlos.com",
    "domicilioPrincipal": "mar egeo",
    "domicilioSecundario": "mastuerzos",
    "telefono": "5545999"
}
```

- Request para crear un platillo (TIPO COCINA= 0 mexicana, 1 itaiana y 2 japonesa) (POST)
```json
{
    "nombre": "pizza",
    "descripcion": "comida italiana",
    "precio": 50.99,
    "estatus": 1,
    "tipoCocina": 1
}
```

- Request para editar un platillo (TIPO COCINA= 0 mexicana, 1 itaiana y 2 japonesa) (PUT)
```json
{
    "id": 3,
    "nombre": "pizza",
    "descripcion": "comida italiana",
    "precio": 50.99,
    "estatus": 1,
    "tipoCocina": 1
}
```

- Request para crear una orden (POST)
```json{
    "idCliente": 3,
    "horaPedido": "21:00",
    "platillos": [
        {
            "id": 3,
            "cantidad": 2
        },
        {
            "id": 4,
            "cantidad": 2
        },
        {
            "id": 5,
            "cantidad": 1
        }
    ]
}
```
