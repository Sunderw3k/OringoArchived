#/bin/bash
mkdir -p certs && cd certs

keytool -genkeypair \
  -alias root \
  -keyalg RSA \
  -keysize 2048 \
  -validity 3650 \
  -dname "CN=Oringo Root CA" \
  -ext bc:c \
  -keystore root-ca.jks \
  -storepass changeit \
  -keypass changeit

keytool -exportcert \
  -alias root \
  -keystore root-ca.jks \
  -storepass changeit \
  -rfc \
  -file root-ca.pem

keytool -genkeypair \
  -alias server \
  -keyalg RSA \
  -keysize 2048 \
  -validity 365 \
  -dname "CN=shiyu.moe" \
  -keystore server.jks \
  -storepass changeit \
  -keypass changeit

keytool -certreq \
  -alias server \
  -keystore server.jks \
  -storepass changeit \
  -file server.csr

keytool -gencert \
  -alias root \
  -keystore root-ca.jks \
  -storepass changeit \
  -infile server.csr \
  -outfile server-cert.pem \
  -rfc \
  -ext "SAN=dns:shiyu.moe"

keytool -importcert \
  -alias root \
  -keystore server.jks \
  -storepass changeit \
  -file root-ca.pem \
  -noprompt

keytool -importcert \
  -alias server \
  -keystore server.jks \
  -storepass changeit \
  -file server-cert.pem

rm root-ca.jks
rm server-cert.pem
rm server.csr

echo
echo "Load $(pwd)/root-ca.pem into your trust store."
echo "You can also run the server and hit /ext/rootCA.pem to get it."