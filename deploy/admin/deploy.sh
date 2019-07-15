docker rm -f admin
docker rmi -f admin
docker build -t admin .
docker run  --name admin   --restart=always   -p 8082:80  -d  admin
