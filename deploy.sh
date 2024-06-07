sudo apt update
sudo apt upgrade -y

sh install_java_jdk21.sh
if java -version 2>&1 | grep -q "openjdk version \"$JDK_VERSION\""; then
  echo -e "\e[32mJDK $JDK_VERSION est déjà installé.\e[0m"
else
  sh install_java_jdk21.sh
fi

echo -e "\e[32m [Terminé] -------------------------- \e[0m"
echo -e "\e[32m [Terminé] Instalation de JAVA JDK 21 \e[0m"
echo -e "\e[32m [Terminé] -------------------------- \e[0m"


sudo apt install git maven -y

chmod +x start.sh
chmod +x save.sh
echo -e "\e[32m [Terminé] chmod des sh \e[0m"

mkdir ~/spigot
cd ~/spigot
wget https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar
java -Xmx1024M -jar ~/spigot/BuildTools.jar
echo -e "\e[32 [Terminé] Execution de BuildTools.jar \e[0m"

mkdir ~/minecraft-server

#!/bin/bash

DIR="~/minecraft-server"

if [ ! -d "$DIR" ]; then
  mkdir "$DIR"
  echo -e "\e[32mLe dossier $DIR a été créé.\e[0m"
else
  echo -e "\e[31mLe dossier $DIR existe déjà.\e[0m"
fi

cp ~/spigot/spigot-*.jar ~/minecraft-server/spigot.jar

sh start.sh

echo "eula=true" > ~/minecraft-server/eula.txt
echo -e "\e[32m [Terminé] eula=true \e[0m"

ip a
