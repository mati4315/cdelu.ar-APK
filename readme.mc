# Diario CdelU - Aplicación Android

Esta es la aplicación móvil nativa del Diario Digital de Concepción del Uruguay, desarrollada con **Kotlin** y **Jetpack Compose**.

## 🚀 Características

### ✅ Implementadas
- **Arquitectura MVVM** con Hilt para inyección de dependencias
- **Jetpack Compose** para la interfaz de usuario
- **Navigation Component** para navegación entre pantallas
- **Retrofit** para comunicación con APIs
- **Coil** para carga de imágenes
- **Material Design 3** con tema personalizado
- **Estructura modular** y escalable

### 📱 Pantallas Disponibles
- **Feed Principal** - Lista de noticias y contenido
- **Encuestas Activas** - Sistema de votación
- **Transmisión en Vivo** - Reproductor de video
- **Loterías** - Sistema de participación
- **Autenticación** - Login y registro
- **Perfil de Usuario** - Gestión de cuenta

## 🏗️ Arquitectura

```
app/
├── data/
│   ├── api/           # Servicios de API
│   ├── model/         # Modelos de datos
│   └── repository/    # Repositorios
├── di/                # Módulos de Hilt
├── ui/
│   ├── components/    # Componentes reutilizables
│   ├── navigation/    # Navegación
│   ├── screens/       # Pantallas
│   └── theme/         # Temas y estilos
└── MainActivity.kt    # Actividad principal
```

## 🛠️ Tecnologías Utilizadas

- **Kotlin** - Lenguaje principal
- **Jetpack Compose** - UI declarativa
- **Material Design 3** - Sistema de diseño
- **Hilt** - Inyección de dependencias
- **Retrofit** - Cliente HTTP
- **Coil** - Carga de imágenes
- **Navigation Compose** - Navegación
- **Coroutines** - Programación asíncrona
- **Flow** - Flujos reactivos

## 📋 Requisitos

- **Android Studio** Arctic Fox o superior
- **Android SDK** 26+ (Android 8.0)
- **Kotlin** 2.0.21
- **Gradle** 8.11.1

## 🚀 Instalación

1. **Clonar el repositorio:**
   ```bash
   git clone <url-del-repositorio>
   cd cdelu.APK
   ```

2. **Abrir en Android Studio:**
   - Abrir Android Studio
   - Seleccionar "Open an existing project"
   - Navegar a la carpeta `cdelu.APK`

3. **Sincronizar dependencias:**
   - Esperar a que Gradle sincronice automáticamente
   - O hacer clic en "Sync Now" si aparece

4. **Configurar API:**
   - Editar `NetworkModule.kt`
   - Cambiar la URL base por tu servidor:
   ```kotlin
   .baseUrl("https://tu-servidor.com/api/v1/")
   ```

5. **Ejecutar la aplicación:**
   - Conectar dispositivo Android o usar emulador
   - Presionar F5 o hacer clic en "Run"

## 🔧 Configuración

### Variables de Entorno
La aplicación está configurada para usar:
- **URL Base API:** `http://localhost:3001/api/v1/` (desarrollo)
- **Min SDK:** 26 (Android 8.0)
- **Target SDK:** 36 (Android 14)

### Permisos Requeridos
- **Internet** - Para comunicación con APIs
- **Cámara** - Para subir fotos de perfil
- **Almacenamiento** - Para guardar archivos
- **Notificaciones** - Para notificaciones push

## 📱 Funcionalidades

### Feed Principal
- Lista infinita de noticias
- Sistema de likes y comentarios
- Filtros por tipo de contenido
- Carga lazy de imágenes

### Encuestas
- Encuestas activas en tiempo real
- Sistema de votación
- Resultados con porcentajes
- Actualización automática

### Transmisión en Vivo
- Reproductor HLS integrado
- Integración con Facebook Live
- Controles personalizados
- Estado de transmisión

### Loterías
- Loterías gratuitas y de pago
- Sistema de tickets
- Participación con múltiples tickets
- Gestión de ganadores

## 🎨 Diseño

La aplicación utiliza **Material Design 3** con:
- **Tema claro/oscuro** automático
- **Colores personalizados** del Diario CdelU
- **Tipografía** optimizada para móviles
- **Animaciones** fluidas y naturales

## 🔄 Estado del Proyecto

### ✅ Completado
- [x] Configuración base del proyecto
- [x] Arquitectura MVVM con Hilt
- [x] Navegación entre pantallas
- [x] Modelos de datos
- [x] Servicios de API
- [x] Componentes UI básicos
- [x] Feed principal
- [x] Sistema de encuestas
- [x] Transmisión en vivo
- [x] Sistema de loterías

### 🚧 En Desarrollo
- [ ] Autenticación completa
- [ ] Gestión de perfil
- [ ] Subida de archivos
- [ ] Notificaciones push
- [ ] Modo offline
- [ ] Tests unitarios

### 📋 Pendiente
- [ ] Optimización de performance
- [ ] Accesibilidad
- [ ] Internacionalización
- [ ] Analytics
- [ ] Crash reporting

## 🐛 Solución de Problemas

### Error de Compilación
```bash
# Limpiar y reconstruir
./gradlew clean
./gradlew build
```

### Error de Dependencias
```bash
# Invalidar caché y reiniciar
File > Invalidate Caches and Restart
```

### Error de API
- Verificar que el servidor esté funcionando
- Comprobar la URL base en `NetworkModule.kt`
- Revisar los logs de Retrofit

## 📞 Soporte

Para reportar bugs o solicitar funcionalidades:
1. Crear un issue en el repositorio
2. Incluir logs de error si aplica
3. Describir los pasos para reproducir

## 📄 Licencia

Este proyecto está bajo la licencia MIT. Ver `LICENSE` para más detalles.

---

**Desarrollado con ❤️ para el Diario CdelU**

# cdelu.ar-APK
version apk de mi app
