if [ $# -eq 0 ]
then
    echo "Usage: $0 port_number"
    exit 1
fi

export SERVER_PORT=$1
mvn spring-boot:run
