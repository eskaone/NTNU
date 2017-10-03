echo "E-mail check for $1"
echo "======================="


RES=$(nslookup -type=txt $1 | grep 'v=spf1')
echo $RES
echo "======================="

IP=$(echo $RES | cut -d ':' -f 3,4 )

echo $IP

echo "======================="

IP2=$(echo $RES | sed 's/: /\n/g' )

echo $IP2

echo "======================="

IP3=$(echo $IP2 | cut  -c 1)

echo $IP3
