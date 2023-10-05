#! /bin/bash

sudo apt-get update; 

sudo apt-get upgrade; 
sudo apt-get install build-essential manpages-dev python3 git wget gpg -y; 

sudo apt install python3-pip; 

bash -c "$(curl -fsSL https://raw.githubusercontent.com/kontr0x/github-desktop-install/main/installGitHubDesktop.sh)"; 

sudo apt install curl gnupg apt-transport-https; 

curl -1sLf "https://keys.openpgp.org/vks/v1/by-fingerprint/0A9AF2115F4687BD29803A206B73A36E6026DFCA" | sudo gpg --dearmor | sudo tee /usr/share/keyrings/com.rabbitmq.team.gpg > /dev/null; 

curl -1sLf "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0xf77f1eda57ebb1cc" | sudo gpg --dearmor | sudo tee /usr/share/keyrings/net.launchpad.ppa.rabbitmq.erlang.gpg > /dev/null; 

curl -1sLf "https://packagecloud.io/rabbitmq/rabbitmq-server/gpgkey" | sudo gpg --dearmor | sudo tee /usr/share/keyrings/io.packagecloud.rabbitmq.gpg > /dev/null; 

sudo apt update; 

sudo apt install -y erlang-base \ erlang-asn1 erlang-crypto erlang-eldap erlang-ftp erlang-inets \ erlang-mnesia erlang-os-mon erlang-parsetools erlang-public-key \ erlang-runtime-tools erlang-snmp erlang-ssl \ erlang-syntax-tools erlang-tftp erlang-tools erlang-xmerl; 

sudo apt install rabbitmq-server -y --fix-missing; 

sudo systemctl start rabbitmq-server; 

sudo rabbitmq-plugins enable rabbitmq_management; 

sudo rabbitmqctl add_user cfns stage; 

sudo rabbitmqctl set_user_tags cfns administrator; 

sudo rabbitmqctl set_permissions -p / cfns ".*" ".*" ".*"; 

sudo rabbitmqctl add_user backendUser backendPass; 

sudo rabbitmqctl set_user_tags backendUser administrator; 

sudo rabbitmqctl set_permissions -p / backendUser ".*" ".*" ".*"; 

wget -qO- https://packages.microsoft.com/keys/microsoft.asc | gpg --dearmor > packages.microsoft.gpg;
sudo install -D -o root -g root -m 644 packages.microsoft.gpg /etc/apt/keyrings/packages.microsoft.gpg;
sudo sh -c 'echo "deb [arch=amd64,arm64,armhf signed-by=/etc/apt/keyrings/packages.microsoft.gpg] https://packages.microsoft.com/repos/code stable main" > /etc/apt/sources.list.d/vscode.list';
rm -f packages.microsoft.gpg;

sudo apt install default-jre;
sudo apt install default-jdk;

sudo apt update;
sudo apt install code;
