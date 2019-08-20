
AWS: Security Groups inbound ports

## 01: Install Docker

https://gist.github.com/brianz/8458fc666f5156fdbbc2

``` bash
#!/bin/bash
  #  http://docs.aws.amazon.com/AmazonECS/latest/developerguide/docker-basics.html#install_docker
sudo yum update -y &&
sudo yum install -y docker &&
sudo service docker start &&
sudo usermod -a -G docker ec2-user 
  # log out and log in to pickup the added group

  # Also install some common sense stuff
sudo yum install -y git && sudo yum -y groupinstall "Development Tools"
``` 

``` bash
sudo curl -L https://github.com/docker/compose/releases/download/1.22.0/docker-compose-$(uname -s)-$(uname -m) -o /usr/local/bin/docker-compose

  # Fix permissions after download:

sudo chmod +x /usr/local/bin/docker-compose
docker-compose version
``` 

``` bash
sudo yum -y install tmux 
tmux new -s t0
tmux set -g status-right '#[fg=black][%H:%M]#[default]' 
tmux set -g status-left '' 
``` 

``` bash
export PS1="\W "
alias dc='docker-compose'
``` 

## Wordpress setup 

Follow https://github.com/mjstealey/wordpress-nginx-docker

``` bash
git clone https://github.com/mjstealey/wordpress-nginx-docker
tmux new -s t0
tmux attach -d -t t0
cd wordpress-nginx-docker
``` 

``` bash
mkdir -p certs/ certs-data/ logs/nginx/ mysql/ wordpress/
docker-compose up -d
``` 

http://18.196.1.112

### SSL setup

``` bash
cp nginx/default_https.conf.template nginx/default.conf
``` 

``` bash
vim nginx/default.conf
``` 

``` bash
%s/FQDN_OR_IP/b2metric.com/g
``` 

``` bash
FQDN_OR_IP=b2metric.com
letsencrypt/letsencrypt-init.sh $FQDN_OR_IP
``` 

contact@b2metric.com

