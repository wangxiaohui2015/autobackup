@echo off

echo=

net stop Autobackup

cd ../bin

JavaService.exe -uninstall Autobackup

pause