
docker rm -f api
docker rmi -f api
docker build -t api .
docker run  --name api  --restart=always   -p 8081:8081  -d  api