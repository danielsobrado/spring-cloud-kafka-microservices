FROM mongo
COPY ./scripts/initDB.json /initDB.json
COPY ./scripts/initMongo.sh /initMongo.sh
COPY ./scripts/wait-for-it.sh /wait-for-it.sh
RUN chmod +x /initMongo.sh /wait-for-it.sh
CMD /initMongo.sh
EXPOSE 27017