wget https://download.oracle.com/java/21/archive/jdk-21.0.2_linux-x64_bin.deb

sudo dpkg -i jdk-21.0.2_linux-x64_bin.deb
sudo apt-get install -f

rm -rf jdk-21.0.2_linux-x64_bin.deb

java -version
