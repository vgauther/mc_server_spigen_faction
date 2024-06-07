sudo apt update
sudo apt upgrade -y

sh install_java_jdk21.sh


echo -e "\e[32 [Terminé] -------------------------- \e[0m"
echo -e "\e[32 [Terminé] Instalation de JAVA JDK 21 \e[0m"
echo -e "\e[32 [Terminé] -------------------------- \e[0m"


sudo apt install git maven -y

chmod +x start.sh
chmod +x save.sh

mkdir ~/spigot
cd ~/spigot
wget https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar
java -Xmx1024M -jar ~/spigot/BuildTools.jar

mkdir ~/minecraft-server
cp ~/spigot/spigot-*.jar ~/minecraft-server/spigot.jar

./start.sh

echo "eula=true" > ~/minecraft-server/eula.txt
echo -e "\e[32 [Terminé] eula=true \e[0m"

ip a
