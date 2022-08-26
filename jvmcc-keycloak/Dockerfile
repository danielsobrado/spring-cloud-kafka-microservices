FROM quay.io/keycloak/keycloak:18.0.2

# The realm import is giving an error, we'll have to fix it later.
# ADD jvmcc-realm.json /opt/keycloak/data/import/

ADD init-users.sh /
ADD startup.sh /

ENTRYPOINT ["/startup.sh"]