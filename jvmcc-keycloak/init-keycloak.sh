#!/usr/bin/env bash

KEYCLOAK_HOST_PORT=${1:-"localhost:8084"}
echo
echo "KEYCLOAK_HOST_PORT: $KEYCLOAK_HOST_PORT"
echo

echo "Getting admin access token"
echo "=========================="

ADMIN_TOKEN=$(curl -s -X POST "http://$KEYCLOAK_HOST_PORT/realms/master/protocol/openid-connect/token" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "username=jvmcc" \
  -d 'password=jvmcc' \
  -d 'grant_type=password' \
  -d 'client_id=admin-cli' | jq -r '.access_token')

echo "ADMIN_TOKEN=$ADMIN_TOKEN"
echo

echo "Creating realm"
echo "=============="

curl -i -X POST "http://$KEYCLOAK_HOST_PORT/admin/realms" \
  -H "Authorization: Bearer $ADMIN_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"realm": "jvmcc-services", "enabled": true}'

echo "Creating client"
echo "==============="

CLIENT_ID=$(curl -si -X POST "http://$KEYCLOAK_HOST_PORT/admin/realms/jvmcc-services/clients" \
  -H "Authorization: Bearer $ADMIN_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"clientId": "simple-service", "directAccessGrantsEnabled": true, "redirectUris": ["http://localhost:9080/*"]}' \
  | grep -oE '[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}')

echo "CLIENT_ID=$CLIENT_ID"
echo

echo "Getting client secret"
echo "====================="

SIMPLE_SERVICE_CLIENT_SECRET=$(curl -s -X POST "http://$KEYCLOAK_HOST_PORT/admin/realms/jvmcc-services/clients/$CLIENT_ID/client-secret" \
  -H "Authorization: Bearer $ADMIN_TOKEN" | jq -r '.value')

echo "SIMPLE_SERVICE_CLIENT_SECRET=$SIMPLE_SERVICE_CLIENT_SECRET"
echo

echo "Creating client role"
echo "===================="

curl -i -X POST "http://$KEYCLOAK_HOST_PORT/admin/realms/jvmcc-services/clients/$CLIENT_ID/roles" \
-H "Authorization: Bearer $ADMIN_TOKEN" \
-H "Content-Type: application/json" \
-d '{"name": "USER"}'

ROLE_ID=$(curl -s "http://$KEYCLOAK_HOST_PORT/admin/realms/jvmcc-services/clients/$CLIENT_ID/roles" \
  -H "Authorization: Bearer $ADMIN_TOKEN" | jq -r '.[0].id')

echo "ROLE_ID=$ROLE_ID"
echo

echo "Configuring LDAP"
echo "================"

LDAP_ID=$(curl -si -X POST "http://$KEYCLOAK_HOST_PORT/admin/realms/jvmcc-services/components" \
  -H "Authorization: Bearer $ADMIN_TOKEN" \
  -H "Content-Type: application/json" \
  -d '@ldap/ldap-config.json' \
  | grep -oE '[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}')

echo "LDAP_ID=$LDAP_ID"
echo

echo "Sync LDAP Users"
echo "==============="

curl -i -X POST "http://$KEYCLOAK_HOST_PORT/admin/realms/jvmcc-services/user-storage/$LDAP_ID/sync?action=triggerFullSync" \
  -H "Authorization: Bearer $ADMIN_TOKEN"

echo
echo
echo "Get jvmcc id"
echo "============="

JVMCC_ID=$(curl -s "http://$KEYCLOAK_HOST_PORT/admin/realms/jvmcc-services/users?username=jvmcc" \
  -H "Authorization: Bearer $ADMIN_TOKEN"  | jq -r '.[0].id')

echo "JVMCC_ID=$JVMCC_ID"
echo

echo "Setting client role to jvmcc"
echo "============================="

curl -i -X POST "http://$KEYCLOAK_HOST_PORT/admin/realms/jvmcc-services/users/$JVMCC_ID/role-mappings/clients/$CLIENT_ID" \
  -H "Authorization: Bearer $ADMIN_TOKEN" \
  -H "Content-Type: application/json" \
  -d '[{"id":"'"$ROLE_ID"'","name":"USER"}]'

echo "Get dsobrado id"
echo "============"

DSOBRADO_ID=$(curl -s "http://$KEYCLOAK_HOST_PORT/admin/realms/jvmcc-services/users?username=dsobrado" \
  -H "Authorization: Bearer $ADMIN_TOKEN"  | jq -r '.[0].id')

echo "DSOBRADO_ID=$DSOBRADO_ID"
echo

echo "Setting client role to dsobrado"
echo "============================"

curl -i -X POST "http://$KEYCLOAK_HOST_PORT/admin/realms/jvmcc-services/users/$DSOBRADO_ID/role-mappings/clients/$CLIENT_ID" \
  -H "Authorization: Bearer $ADMIN_TOKEN" \
  -H "Content-Type: application/json" \
  -d '[{"id":"'"$ROLE_ID"'","name":"USER"}]'

echo "Getting jvmcc access token"
echo "==========================="

curl -s -X POST "http://$KEYCLOAK_HOST_PORT/realms/jvmcc-services/protocol/openid-connect/token" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "username=jvmcc" \
  -d "password=jvmcc" \
  -d "grant_type=password" \
  -d "client_secret=$SIMPLE_SERVICE_CLIENT_SECRET" \
  -d "client_id=simple-service" | jq -r .access_token
echo

echo "Getting admin access token"
echo "=========================="

curl -s -X POST "http://$KEYCLOAK_HOST_PORT/realms/jvmcc-services/protocol/openid-connect/token" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "username=admin" \
  -d "password=admin" \
  -d "grant_type=password" \
  -d "client_secret=$SIMPLE_SERVICE_CLIENT_SECRET" \
  -d "client_id=simple-service" | jq -r .access_token

echo
echo "============================"
echo "SIMPLE_SERVICE_CLIENT_SECRET=$SIMPLE_SERVICE_CLIENT_SECRET"
echo "============================"
