# 🔥 gdx-ignite - LibGDX Project Generator

Modern IntelliJ IDEA plugin for creating LibGDX projects with support for multiple platforms.

## What is gdx-ignite?

Ignite is an IntelliJ IDEA plugin that generates complete LibGDX game projects through the IDE's project wizard. It provides a clean, template-based approach to creating multi-platform game projects with proper Gradle configuration and platform-specific modules.

## Features

- **Multi-Platform Support**: Desktop (LWJGL3), Android, iOS (RoboVM), and HTML (TeaVM)
- **Modern Setup**: LibGDX 1.14.0 with Gradle build system
- **Customizable**: Configure project name, package structure, and main class
- **Template-Based**: Clean code generation system
- **IDE Integration**: Seamless integration with IntelliJ IDEA's project wizard

## Project Structure

```
your-project/
├── assets/                 # Game assets
├── core/                   # Core game logic
│   ├── src/main/java/
│   └── build.gradle
├── lwjgl3/                 # Desktop launcher
│   ├── src/main/java/
│   └── build.gradle
├── build.gradle            # Root build configuration
└── settings.gradle         # Gradle settings
```

## Installation & Testing

### Requirements
- IntelliJ IDEA 2025.1.4.1 or later
- JDK 21 or higher

### Run Plugin in Test IDE
```bash
./gradlew runIde
```

This launches a new IntelliJ IDEA instance with the plugin installed for testing.

### Build Plugin Distribution
```bash
./gradlew buildPlugin
```

The plugin ZIP will be available in `build/distributions/`.

## Usage

1. Run the plugin with `./gradlew runIde`
2. In the test IDE: **File → New → Project**
3. Select **LibGDX** from the generators
4. Configure your project settings
5. Click **Create**

## Roadmap

### High Priority
- [ ] Android module implementation
- [ ] iOS module implementation  
- [ ] HTML module implementation
- [ ] Third-party library selection wizard
- [ ] Gradle wrapper generation

## Contributing

Contributions are welcome! 

1. Fork the repository
2. Create a feature branch
3. Make your changes following the existing code style
4. Test with `./gradlew runIde`
5. Submit a Pull Request

### Coding Standards
- Follow existing code style please

---
