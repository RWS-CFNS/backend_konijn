#! /bin/bash

#update current packages and indexes before installations
sudo apt upgrade; 
sudo apt update; 


#general installations
sudo apt install -y build-essential manpages-dev python3 git wget gpg npm node; 
sudo apt install -y curl gnupg apt-transport-https python3-pip;

#install github desktop
bash -c "$(curl -fsSL https://raw.githubusercontent.com/kontr0x/github-desktop-install/main/installGitHubDesktop.sh)"; 

 
#download rabbitmq packages using curl package installer
curl -1sLf "https://keys.openpgp.org/vks/v1/by-fingerprint/0A9AF2115F4687BD29803A206B73A36E6026DFCA" | sudo gpg --dearmor | sudo tee /usr/share/keyrings/com.rabbitmq.team.gpg > /dev/null; 

curl -1sLf "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0xf77f1eda57ebb1cc" | sudo gpg --dearmor | sudo tee /usr/share/keyrings/net.launchpad.ppa.rabbitmq.erlang.gpg > /dev/null; 

curl -1sLf "https://packagecloud.io/rabbitmq/rabbitmq-server/gpgkey" | sudo gpg --dearmor | sudo tee /usr/share/keyrings/io.packagecloud.rabbitmq.gpg > /dev/null; 

#opdate indexes
sudo apt update; 

#install Erlang programming language packages. Rabbitmq is programmed in Erlang
sudo apt install -y erlang-base;
sudo apt install -y erlang-asn1 erlang-crypto erlang-eldap erlang-ftp erlang-inets;
sudo apt install -y erlang-mnesia erlang-os-mon erlang-parsetools erlang-public-key;
sudo apt install -y erlang-runtime-tools erlang-snmp erlang-ssl;
sudo apt install -y erlang-syntax-tools erlang-tftp erlang-tools erlang-xmerl;

#install rabbitmq and fix missing packages, this may prevent future errors
sudo apt install rabbitmq-server -y --fix-missing; 

#start server and enable plugins for management and communications
sudo systemctl start rabbitmq-server; 
sudo rabbitmq-plugins enable rabbitmq_shovel rabbitmq_management rabbitmq_management_agent rabbitmq_tracing;

#configure users and permissions
sudo rabbitmqctl add_user cfns stage; 
sudo rabbitmqctl set_user_tags cfns administrator; 
sudo rabbitmqctl set_permissions -p / cfns ".*" ".*" ".*"; 
sudo rabbitmqctl add_user backendUser backendPass; 
sudo rabbitmqctl set_user_tags backendUser administrator; 
sudo rabbitmqctl set_permissions -p / backendUser ".*" ".*" ".*"; 

#install Visual Studio Code
wget -qO- https://packages.microsoft.com/keys/microsoft.asc | gpg --dearmor > packages.microsoft.gpg;
sudo install -y -D -o root -g root -m 644 packages.microsoft.gpg /etc/apt/keyrings/packages.microsoft.gpg;
sudo sh -c 'echo "deb [arch=amd64,arm64,armhf signed-by=/etc/apt/keyrings/packages.microsoft.gpg] https://packages.microsoft.com/repos/code stable main" > /etc/apt/sources.list.d/vscode.list';
rm -f packages.microsoft.gpg;
sudo apt install -y code;

#install Java openJDK for backend
sudo apt install -y default-jre default-jdk;

#update packages and indexes one last time
sudo apt upgrade; 
sudo apt update;
