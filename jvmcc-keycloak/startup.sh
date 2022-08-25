#!/bin/bash
echo "Creating keycloak realm and clients in own process..."
nohup bash -c /init-users.sh &
echo "Starting keycloak"
/opt/keycloak/bin/kc.sh start-dev
