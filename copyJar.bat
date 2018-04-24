@echo on
cd /d %~dp0
copy /Y "target\remoteReplaceUtil-0.01.jar" "run\remoteReplaceUtil-0.01.jar"
copy /Y "target\dependency\*" "run\lib\"
copy /Y "config\*" "run\config\"

