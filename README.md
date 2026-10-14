# Paint202620

**Proyecto académico de Ingeniería de Software I**

## Descripción

Paint202620 es un proyecto académico desarrollado en Java como parte de la asignatura Ingeniería de Software I. Su objetivo es desarrollar una aplicación de dibujo inspirada en las herramientas básicas de Paint, poniendo en práctica los conocimientos adquiridos durante la carrera de Informática.

El proyecto se desarrolla de manera grupal, utilizando Git y GitHub para gestionar las versiones del código fuente, colaborar entre los integrantes y dar seguimiento a los cambios realizados durante el desarrollo.

## Roles del proyecto
Liderazgo Central (2 personas)

1 Coordinador General: Organiza las entregas, reparte las tareas y representa al grupo ante el profesor.

1 Encargado de Código: Es el guardián del proyecto. Revisa las partes de todos antes de unirlas para asegurar que el programa principal no explote.

Los 5 Equipos
(Cada equipo internamente tendrá un líder que coordine, programadores, y alguien encargado de probar y buscar errores en su módulo).

Equipo 1: Pincel y Lienzo: Programan el área central de dibujo, el lápiz para trazo libre y la goma de borrar.

Equipo 2: Figuras Geométricas: Hacen la matemática de las líneas, rectángulos, círculos y la opción de ponerles relleno.

Equipo 3: Diseño e Interfaz: Arman la ventana principal, acomodan los botones visuales y conectan la paleta de colores.

Equipo 4: Archivos: Programan el menú superior para poder Guardar el dibujo, Abrir imágenes y crear un Nuevo documento en blanco.

Equipo 5: Herramientas Avanzadas: Le dan el toque profesional haciendo que funcione el Deshacer/Rehacer y el cambio de grosor del pincel.

** 3 Reglas para que esto funcione:

- Nadie toca el trabajo del otro: Si el Equipo 3 hace el botón del rectángulo, el Equipo 2 hace la lógica para que se dibuje. Funcionamos como piezas de un rompecabezas.

- Unión segura: Todo se unirá en un solo lugar (como GitHub). Nadie mete código a la versión final sin que el Encargado de Código lo apruebe.

- Reunión rápida: Los líderes de cada equipo hablan 15 minutos a la semana para asegurarse de que las partes van a encajar bien.

  
## Objetivos del proyecto

* Desarrollar una aplicación gráfica utilizando Java.
* Aplicar los conceptos de programación orientada a objetos.
* Organizar el código mediante clases y componentes independientes.
* Practicar el trabajo colaborativo y el control de versiones con Git y GitHub.
* Mejorar progresivamente la estructura, funcionalidad y calidad del software.

## Tecnologías utilizadas

* **Lenguaje de programación:** Java.
* **Entorno de desarrollo:** Apache NetBeans IDE 31.
* **Control de versiones:** Git.
* **Repositorio remoto:** GitHub.

## Estructura del proyecto

El código fuente está organizado en paquetes para facilitar su comprensión y mantenimiento.

```text
Paint202620/
├── src/
│   ├── figuras/
│   │   ├── Figura.java
│   │   ├── Linea.java
│   │   ├── Rectangulo.java
│   │   └── Trazo.java
│   │   └── Etc.java
│   │   └── Etc.java
│   │   └── Etc.java
│   │   └── Etc.java
│   │   └── Etc.java
│   │   └── Etc.java
│   └── ui/
│       ├── PanelDeDibujo.java
│       ├── VentanaPrincipal.java
│       └── VentanaPrincipal.form
├── nbproject/
├── build.xml
└── manifest.mf
```

La estructura puede modificarse a medida que se incorporen nuevas herramientas y funcionalidades.

## Instalación y ejecución

Para trabajar con el proyecto localmente, sigue estos pasos:

1. Instala un JDK compatible con el proyecto.

2. Instala Apache NetBeans IDE 31.

3. Clona el repositorio desde GitHub:

   ```bash
   git clone https://github.com/JaimeFernandezL/Paint202620.git
   ```

4. Abre NetBeans y selecciona **File → Open Project**.

5. Busca la carpeta `Paint202620` que acabas de clonar.

6. Abre el proyecto, espera a que se cargue y ejecútalo con **Run Project**.

## Trabajo colaborativo

Este proyecto se desarrolla en equipo. Para mantener un historial organizado y reducir conflictos en el código, se recomienda trabajar con ramas individuales y utilizar Pull Requests para proponer cambios.

Flujo de trabajo recomendado:

1. Actualizar el proyecto local.
2. Crear una rama para la tarea asignada.
3. Implementar y probar los cambios.
4. Registrar los cambios mediante un commit descriptivo.
5. Subir la rama a GitHub.
6. Crear un Pull Request para revisar los cambios antes de incorporarlos a `main`.

La rama `main` se utilizará como rama principal del proyecto.

## Estado del proyecto

**En desarrollo.**

Las funcionalidades se irán incorporando y mejorando durante el desarrollo de la asignatura.

## Propósito académico

Este proyecto representa una oportunidad para fortalecer los conocimientos de programación, diseño de software, organización del código y trabajo en equipo, aplicando buenas prácticas de desarrollo en un entorno colaborativo.

## Autoría y colaboración

Proyecto desarrollado por estudiantes de la carrera de Informática para la asignatura Ingeniería de Software I.

Las contribuciones de los integrantes del equipo forman parte del proceso de aprendizaje y evolución del proyecto.

---

*Proyecto académico — 2026.*
