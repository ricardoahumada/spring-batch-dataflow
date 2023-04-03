# Testing
### A. For testing endpoint WITHOUT security
1. Launch server with "dev" profile (uses H2 database)
- `mvn clean spring-boot:run -Dspring-boot.run.profiles=dev`
- or, add VM option: "-Dspring.profiles.active=dev"

2. Consume resources
- API URI: http://localhost:9090
- All endpoints are open.

### B. For testing endpoint WITH security: JWT
1. Launch server with "prod" profile (uses Mysql database)
- `mvn clean spring-boot:run -Dspring-boot.run.profiles=prod`
- or, add VM option: "-Dspring.profiles.active=prod"
- In application.yml:
	+ `security.oauth: false`

2. Create users
- Open any REST Client.

- Get users: https://localhost:9443/users
- Add your users: 
	+ POST https://localhost:9443/users {"email":"admin@mail.com", "password":"my_pass", "role":"ADMIN"}
	+ POST https://localhost:9443/users {"email":"user@mail.com", "password":"my_pass", "role":"USER"}

2. Authenticate
- Authenticate against the authentication endpoint https://localhost:9443/auth/login with this your user data (POST request):
- 
```
	{"email":"admin@mail.com", "password":"my_pass"}
	{"email":"user@mail.com", "password":"my_pass"}
```

- Response:

```
{
  "email": "admin@mail.com",
  "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLG5hbUBjb2RlamF2YS5..."
}
```


3. Consume a resource
- Send a request to restricted endpoint using the token.
- Authorization:
	+ Bearer [accessTokem]
	+ Example:
		* GET https://localhost:9443/recibos
		* Custom Authorization: `Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLG5hbUBjb2RlamF2YS5...`

### C. For testing endpoint WITH security: OAUTH
1. Launch server with "prod" profile  (uses Mysql database)
- `mvn clean spring-boot:run -Dspring-boot.run.profiles=prod`
- or, add VM option: "-Dspring.profiles.active=prod"
- In application.yml:
	+ `security.oauth: true`
	
2. Follow instructions in oauth2-authorization-server/README.MD
