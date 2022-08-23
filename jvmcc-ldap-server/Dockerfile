FROM osixia/openldap:1.5.0

ENV LDAP_ORGANISATION="jvmcc"
ENV LDAP_DOMAIN="jvmcc"
ENV LDAP_TLS_VERIFY_CLIENT=try
ENV TLS_REQCERT=never
COPY bootstrap.ldif /container/service/slapd/assets/config/bootstrap/ldif/custom/
RUN ls -la /container/service/slapd/assets/config/bootstrap/ldif/custom/bootstrap.ldif