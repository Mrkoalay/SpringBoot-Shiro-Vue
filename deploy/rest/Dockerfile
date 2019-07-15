FROM java:8
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ADD api.jar api.jar
RUN bash -c 'touch /api.jar'
ENTRYPOINT ["java","-jar","/api.jar"]