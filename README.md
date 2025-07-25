# PesaLockBank - Ktor Backend

A simple banking backend service built with Ktor.

## Project Structure

```
PesaLockBank/
├── build.gradle.kts                # Project build configuration
├── gradle.properties              # Gradle properties
├── settings.gradle.kts            # Project settings
├── gradlew                        # Gradle wrapper (Unix)
├── gradlew.bat                    # Gradle wrapper (Windows)
├── README.md                      # Project documentation
│
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   ├── models/            # Data models
│   │   │   ├── routes/            # API endpoints
│   │   │   ├── services/          # Core services
│   │   │   │   ├── Application.kt # Main app entry
│   │   │   │   ├── Databases.kt   # Database config
│   │   │   │   ├── Routing.kt     # Route definitions
│   │   │   │   └── Serialization.kt # JSON config
│   │   └── resources/             # Config files
│   │
│   └── test/                      # Test files
│
└── gradle/                        # Gradle wrapper files
```

## Getting Started

1. **Run the application**:
   ```bash
   ./gradlew run
   ```

2. **Endpoints**:
   - `GET /` - Health check
   - `POST /register` - User registration
   - `POST /login` - User authentication

## Configuration

Edit these files for setup:
- `src/main/resources/application.conf` - Server settings
- `src/main/resources/dbconfig.properties` - Database config

## Dependencies

- Ktor Server
- H2 Database (for development)
- Kotlinx Serialization

## Development

```bash
# Build project
./gradlew build

# Run tests
./gradlew test
```


