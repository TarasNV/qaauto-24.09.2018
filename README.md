Tools installation

    - IntelliJ IDEA Community version
      IntelliJ IDEA is a Java integrated development environment (IDE) for developing computer software.
    - JDK 10.0.2
      The Java Development Kit (JDK) is a software development environment used for developing Java applications and applets.
    - Geckodriver v0.22.0
      Gecko Driver is the link between your tests in Selenium and browser. GeckoDriver is a proxy for using W3C WebDriver-compatible clients to interact with Gecko-based browsers i.e. Mozilla Firefox

Tools setup

    - Install IntelliJ IDEA Community version
    - Install JDK 10.0.2
    - For Windows x64 unzrchive geckodriver-v0.22.0-win64.zip and put geckodriver.exe to C:\Windows\System32

**pom.xml file**
The pom.xml file contains information of project and configuration information for the maven to build the project such as dependencies, build directory, source directory,

**.gitignore file**
The .gitignore file contains list of folders and files that should be ignored when creating or updating your project on Github

Current test scenario can be run in the following way
    - Launch IntelliJ IDEA
    - Open qaauto-24.09.2018 project (File -> Open -> choose project -> Ok)
    - Run BadCodeExample class (Run -> Run 'BadCodeExample')
