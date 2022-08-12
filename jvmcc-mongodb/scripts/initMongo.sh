bash -c "/wait-for-it.sh 127.0.0.1:3306 -t 10 -- mongoimport -v --db jvmcc --collection reviews --type json --file /initDB.json" &
exec mongod --bind_ip_all
