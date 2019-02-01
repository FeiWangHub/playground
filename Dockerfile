FROM debian

MAINTAINER Fei <fei.wang.cn@icloud.com>
RUN apt-get update && apt-get install -y cowsay fortune
COPY entrypoint.sh /

ENTRYPOINT ["/entrypoint.sh"]
