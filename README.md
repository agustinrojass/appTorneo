![Logo](https://raw.githubusercontent.com/agustinrojass/appTorneo/main/images/banner.png)

# [appTorneo](https://github.com/agustinrojass/appTorneo)

## Autores

- Agustin Rojas [ [GitHub](https://github.com/agustinrojass) - [LinkedIn](https://www.linkedin.com/in/agustinrojas259/) ]
- Jonas Febbro [ [GitHub](https://github.com/jonasFebbro) ]
- Luca Coppari [ [GitHub](https://github.com/lucacoppari12) ]

## Documentación

El propósito del sistema es permitir la navegación para visualizar la información de un torneo de fútbol (en este caso, la liga argentina) como Promiedos para el usuario común. 
Además, hay un usuario administrador que puede actualizar y exportar la información. 
Esto no se hace de manera manual, sino a través de una [API](https://www.api-football.com/).

El sistema está hecho en Java implementando la programación orientada a objetos. 
Para ello se utilizan las clases Torneo, Fecha, PartidoFutbol, Equipo, Persona, Jugador y Tecnico que se relacionan entre sí.
Esto se puede ver en el [diagrama de clases](https://raw.githubusercontent.com/agustinrojass/appTorneo/main/images/diagramaDeClases.png).
También hay otras clases como Menú, que maneja la navegabilidad por el sistema, ApiFootball, que gestiona la conexión con la API, JsonUtiles, que se encarga del flujo de la información del formato JSON a las clases ya mencionadas, y de estas a archivos binarios y viceversa. 
Luego, Torneo implementa la interfaz IExportarJson que permite exportar la clase a formato JSON. 
Asimismo, las primeras clases mencionadas implementan la interfaz Serializable que les permite ser guardadas en archivos binarios.

Luego, los archivos .json son guardados en la carpeta json del proyecto, que a su vez separa la tabla, el fixture y los goleadores de los jugadores y de los técnicos de cada club; y los archivos .bin en la carpeta bin.

Para facilitar su uso, dejamos el [manual de usuario](https://docs.google.com/presentation/d/1z5_tnjZ1heaFB4Dm5GAitRs76oC94rcz2PoUvFXQuEk/).

## Enlaces

- [Github](https://github.com/agustinrojass/appTorneo)
- [API-Football](https://www.api-football.com/)
- [Diagrama de clases](https://raw.githubusercontent.com/agustinrojass/appTorneo/main/images/diagramaDeClases.png).
- [Manual de usuario](https://docs.google.com/presentation/d/1z5_tnjZ1heaFB4Dm5GAitRs76oC94rcz2PoUvFXQuEk/)

## Licencia

- [GNU](https://www.gnu.org/licenses/gpl-3.0.html)
