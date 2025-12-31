Clash Royale API App 🏰📱
- Una aplicación Android moderna construida con Jetpack Compose que consume la API oficial de Clash Royale para mostrar información sobre arenas, cartas y jugadores.
- Incluye autenticación segura mediante Firebase.✨ CaracterísticasAutenticación con Firebase: Registro e inicio de sesión de usuarios de forma segura.
- Explorador de Arenas: Listado detallado de todas las arenas del juego.
- Consumo de API: Integración en tiempo real con los datos oficiales de Supercell.
- Interfaz Moderna: Diseño basado en Material Design 3 y animaciones con Compose.
- Arquitectura Limpia: Implementación de MVVM (Model-View-ViewModel) y Repositorios.

🛠️ Stack Tecnológico
- Lenguaje: Kotlin
- UI: Jetpack Compose (Declarative UI)
- Red: Retrofit & OkHttp para peticiones REST.
- Imágenes: Coil para la carga de imágenes por URL.
- Backend/Auth: Firebase Authentication.
- Inyección de Dependencias: (Opcional, menciona si usas Dagger Hilt o Koin).

🚀 Instalación y Configuración
Para ejecutar este proyecto localmente, sigue estos pasos:

1. Clonar el repositorio desde Bash GIT:
   git clone https://github.com/jorgevizu4/ClashRoyaleApi.git
2. Compilar y Ejecutar:
   - Abre el proyecto en Android Studio Jellyfish o superior.
   - Sincroniza Gradle y ejecuta en un emulador o dispositivo físico.

🏗️ Arquitectura
- El proyecto sigue la arquitectura recomendada por Google para Android:
- UI Layer: Componibles de Jetpack Compose que reaccionan al estado.
- ViewModel: Gestiona el estado de la UI y la comunicación con el repositorio.
- Data Layer: Maneja la lógica de red (Retrofit) y persistencia.

Desarrollado por Jorge Vizuete 👑
