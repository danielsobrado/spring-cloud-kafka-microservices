#!/bin/bash

AUTH_ENDPOINT=http://localhost:8080/
JVMCC_REALM=jvmcc-service	
INTERNAL_USERS_REALM=jvmcc-gs-internal
JVMCC_CLIENT_ID=jvmcc-1
ADMIN_CLIENT_ID=jvmcc-gs

export PATH=$PATH:/opt/keycloak/bin/

while ! curl -s --head  --request GET $AUTH_ENDPOINT | grep "200 OK" > /dev/null; do
  echo "Waiting for Keycloak server..."
  sleep 5s
done

kcadm.sh config credentials --server $AUTH_ENDPOINT --realm master --user $KEYCLOAK_ADMIN --password $KEYCLOAK_ADMIN_PASSWORD
realm1=$(kcadm.sh get realms/$JVMCC_REALM)
if [ -z "$realm1" ]; then
    kcadm.sh create realms -s realm=$JVMCC_REALM -s enabled=true
else
    echo "The realm $JVMCC_REALM has already been created."
fi

realm2=$(kcadm.sh get realms/$INTERNAL_USERS_REALM)
if [ -z "$realm1" ]; then
    kcadm.sh create realms -s realm=$INTERNAL_USERS_REALM -s enabled=true
else
    echo "The realm $INTERNAL_USERS_REALM has already been created."
fi

## Create clients
    kcadm.sh create clients -r $JVMCC_REALM -s clientId=$JVMCC_CLIENT_ID  -s 'redirectUris=["*"]' -s 'publicClient=false' -s 'serviceAccountsEnabled=true'
    kcadm.sh create clients -r $INTERNAL_USERS_REALM -s clientId=$ADMIN_CLIENT_ID -s 'redirectUris=["*"]' -s 'publicClient=false' -s 'directAccessGrantsEnabled=true'


## Create users and roles

    kcadm.sh create users -r $INTERNAL_USERS_REALM -s username=jvmcc -s enabled=true
    kcadm.sh set-password -r $INTERNAL_USERS_REALM  --username jvmcc --new-password jvmcc

    kcadm.sh create roles -r $INTERNAL_USERS_REALM -s name=USER
    kcadm.sh add-roles --uusername jvmcc --rolename USER -r $INTERNAL_USERS_REALM

    kcadm.sh create users -r $INTERNAL_USERS_REALM -s username=admin -s enabled=true
    kcadm.sh set-password -r $INTERNAL_USERS_REALM  --username admin --new-password admin

    kcadm.sh create roles -r $INTERNAL_USERS_REALM -s name=ADMIN
    kcadm.sh add-roles --uusername admin --rolename ADMIN -r $INTERNAL_USERS_REALM
    kcadm.sh add-roles --uusername admin --rolename USER -r $INTERNAL_USERS_REALM

    kcadm.sh create users -r $JVMCC_REALM -s username=jvmcc -s enabled=true
    kcadm.sh set-password -r $JVMCC_REALM  --username jvmcc --new-password jvmcc

    kcadm.sh create roles -r $JVMCC_REALM -s name=USER
    kcadm.sh add-roles --uusername jvmcc --rolename USER -r $JVMCC_REALM

    kcadm.sh create users -r $JVMCC_REALM -s username=admin -s enabled=true
    kcadm.sh set-password -r $JVMCC_REALM  --username admin --new-password admin

    kcadm.sh create roles -r $JVMCC_REALM -s name=ADMIN
    kcadm.sh add-roles --uusername admin --rolename ADMIN -r $JVMCC_REALM
    kcadm.sh add-roles --uusername admin --rolename USER -r $JVMCC_REALM