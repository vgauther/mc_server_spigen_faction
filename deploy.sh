sudo apt update
sudo apt upgrade -y

sudo apt install openjdk-17-jre-headless -y
sudo apt install git maven -y

chmod +x start.sh
chmod +x save.sh

mkdir ~/minecraft
cd ~/minecraft
wget https://papermc.io/api/v2/projects/paper/versions/1.20/builds/300/downloads/paper-1.20-300.jar -O paper.jar
java -Xms2G -Xmx4G -jar paper.jar nogui
echo "eula=true" > eula.txt

ip a