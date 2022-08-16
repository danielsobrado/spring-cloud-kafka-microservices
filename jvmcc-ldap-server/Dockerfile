FROM osixia/openldap:1.5.0

ENV LDAP_ORGANISATION="danielsobrado.com"
ENV LDAP_DOMAIN="jvmcc.com"
ENV LDAP_TLS_VERIFY_CLIENT=try
ENV TLS_REQCERT=never
COPY bootstrap.ldif /container/service/slapd/assets/config/bootstrap/ldif/50-bootstrap.ldif