# Testing

- Start resource and authentication servers.

## Token request process

1- Get the code
- Open a browser and enter a request this:

```
https://127.0.0.1:9443/oauth2/authorize?response_type=code&client_id=[client_id]&redirect_uri=[redirect_url]/authorized&scope=openid [scope]
```
- Get the previous data from the configuration of auth server.

	+ For example: 
```
https://127.0.0.1:9443/oauth2/authorize?response_type=code&client_id=client1&redirect_uri=http://127.0.0.1:8080/authorized&scope=openid SCOPE_invoices
```

- You get a response like this: 
```
http://127.0.0.1:8080/authorized?code=[CODE]
```

2. Ask for the token:
- In the REST client send a POST request:
    + Url: http://127.0.0.1:9443/oauth2/token
    + Authorization tab:
        - Username: [client_id]
        - Password: [client_secret]
    + Body tab:
        - grant_type: authorization_code
        - code: [CODE]
        - redirect_uri: [redirect_uri]


3. The **access_token** value of the response is what you need to consume resources.


## Consuming resources
1. Obtain a token against the authorization server.
- Follow former steps
- Copy the access_token value in the response.

2. Import oauth cert into JVM:
- Copy rag.cert to "%JAVA_HOME%/lib\security"
  - for example: `C:\Program Files\jdk-11\lib\security`
- Import the cert:
  - `keytool -import -alias rag -keystore cacerts -file rag.crt`
  - Password: "changeit"
  - If asked: "yes"
- Restart the resource server. 

2. Make a request of type **Authorization Bearer**  to the resource endpoint. Remember add the token to the request!
- For example:
    + GET https://localhost:8443/recibos
    + Authorization Bearer:  [access_token]
