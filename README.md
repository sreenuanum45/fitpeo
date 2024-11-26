# HybridFramework

## Overview
HybridFramework is a comprehensive automation testing framework designed to simplify the process of creating and managing automated tests. It incorporates the best practices from various frameworks, providing a robust structure for your test automation needs.

## Directory Structure

The directory structure of HybridFramework is as follows:
HybridFramework/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── base/               # Contains the Base class for WebDriver setup and teardown.
│   │   │   ├── config/             # Contains configuration files (e.g., config.properties).
│   │   │   ├── pages/              # Page Object Model (POM) classes (locators and actions).
│   │   │   ├── testdata/           # Data handling logic (e.g., reading from Excel or JSON).
│   │   │   ├── utilities/          # Reusable utilities like Excel handling, logging, and reporting.
│   │   │   ├── reports/            # Custom report utilities like ExtentReports.
│   │   │   ├── constants/          # Framework constants for better readability and maintainability.
│   │   │   └── exceptions/         # Custom exception classes for the framework.
│   │
│   ├── test/
│   │   ├── java/
│   │   │   ├── tests/              # Test classes (organized by module or feature).
│   │   │   └── listeners/          # Custom TestNG listeners (e.g., for screenshots on failure).
│   │   └── resources/
│   │       └── testng.xml          # TestNG XML files for test execution.
│
├── reports/                        # Generated reports (ExtentReports, logs, etc.).
│
├── testdata/                       # Test data files (e.g., Excel, JSON, or CSV files).
│
├── logs/                           # Log files generated during test execution.
│
├── drivers/                        # WebDriver executables (e.g., ChromeDriver, GeckoDriver).
│
├── screenshots/                    # Screenshots captured during test execution (e.g., failures).
│
├── pom.xml                         # Maven configuration file for managing dependencies.
│
└── README.md                       # Documentation for the framework.


## Getting Started

### Prerequisites
- Java JDK 11 or higher
- Maven
- WebDriver executables (ChromeDriver, GeckoDriver, etc.)

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/sreenuanum45/RoofRocket-AI-.git
   ```

2. Navigate to the project directory:
   ```bash
   cd D:\Myprojects\RoofRocketAI
   ```

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the tests:
   ```bash
   mvn test

## Contributing
Contributions are welcome! Please fork the repository, make changes, and submit a pull request.

## License
HybridFramework is distributed under the [MIT License](LICENSE).

## Acknowledgments
We would like to extend our gratitude to the open-source community for their contributions to this project.

## Contact
- [GitHub](https://github.com/sreenuanum45/RoofRocket-AI-.git)
- [LinkedIn](https://www.linkedin.com/in/yourusername/)
- Email: [anumandlasreenu@gmail.com]
- Website: [yourwebsite.com]
- Blog: [yourblog.com]
- Twitter: [@yourtwitterhandle]
- Stack Overflow: [yourstackoverflowprofile]
- Slack: [yourslackworkspace]
- Discord: [yourdiscordserver]

## Support
If you have any questions or need further assistance, please don't hesitate to contact us. We're here to help!


## Credits
HybridFramework is a collaborative effort by [anumandla sreenu] and is based on the following open-source projects:

- [Project 1](https://github.com/sreenuanum45/RoofRocket-AI-.git)
- [Project 2](https://github.com/sreenuanum45/vinothqaacademy_HybridFramework.git)
- [Project 3](https://github.com/sreenuanum45/HybridFramework.git) 
- [Project 4](https://github.com/sreenuanum45/sleeplogik.git)
   
