FROM nginx
ADD dist /usr/share/nginx/html
ENV JAVA_OPTS=""
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
