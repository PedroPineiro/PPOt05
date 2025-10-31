# Generar un proyecto Spring Boot desde la terminal

Este comando permite **crear un nuevo proyecto Spring Boot** directamente desde la terminal, usando **Spring Initializr**.

## 🧩 Comando base

```bash
curl https://start.spring.io/starter.zip \
  -d dependencies=web,lombok,devtools,thymeleaf,cloud-starter,validation \
  -d groupId=com.pedro \
  -d artifactId=demo \
  -d name=demo \
  -d type=maven-project \
  -d javaVersion=21 \
  -d packaging=jar \
  -o demo.zip && unzip demo.zip -d PPOt && rm demo.zip
```

## 📋 Hay que modificar

- `name`: Nombre del proyecto.
- `artifactId`: Nombre del artefacto (generalmente igual que el nombre del proyecto)
- `PPOt`: Nombre de la carpeta donde se descomprimirá el proyecto.
