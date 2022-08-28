FROM quay.io/keycloak/keycloak:18.0.2

# The realm import is giving an error, we'll have to fix it later.
ADD jvmcc-realm.json /opt/keycloak/data/import/

# ADD init-users.sh /
# ADD startup.sh /

# CMD ["start-dev --import-realm --http-port 8084"]
CMD ["start-dev --http-port 8084 --import-realm"]


