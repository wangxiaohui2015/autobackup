#Welcome to AUTOBACKUP

This tool can help you to backup anything on your Windows computer, and the output files are encrypted by AES-128.

####Development
This is a standard Maven project, import this project to Eclipse for development.

####Build
Go to root location of this project, then execute: mvn package

####Installation
1. Unzip file autobackup-VERSION.zip
2. Click install.bat with Administrator permission to install Autobackup, the target location is: C:\Program Files\autobackup
3. Go to C:\Program Files\autobackup\
3. Configure conf\backup\_config.properties and conf\service_config.properties
4. Click bin\startup_service.bat with Administrator permission to startup service

###New Features
#####1.0.0
1. Basic backup/restore
2. Support AES-128 encryption

#####1.1.0
1. Embedded JRE
2. Refactor installation process
3. Refactor restore command UI
4. Optimize system configurations