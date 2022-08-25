FROM quay.io/keycloak/keycloak:18.0.2

ADD init-users.sh /
ADD startup.sh /

ENTRYPOINT ["/startup.sh"]