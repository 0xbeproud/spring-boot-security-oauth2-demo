1. /oauth/token

```bash

curl {security.oauth2.client.client-id}:{security.oauth2.client.client-secret}@주소/oauth/token -d grant_type=password -d client_id={security.oauth2.client.client-id} -d scope=read -d username={security.user.name} -d password={security.user.password}


curl -u app1:app1 localhost:8181/oauth/token -d grant_type=password -d client_id=app1 -d scope=read -d username=user -d password=pass
```


```json
{
  "access_token": "0dd31869-5c41-44af-9635-dda99066fb57",
  "token_type": "bearer",
  "refresh_token": "59928dd8-de18-4a43-b9be-f86e69eab203",
  "expires_in": 39318,
  "scope": "read"
}
```



2. /oauth/check_token

참고

```
#AuthorizationSerConfig
 
    @Override
    public void configure(final AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()"); <-- permitAll()로 변경해야 한다. 
    }
```


curl -X POST 'http://localhost:8181/oauth/check_token' -d 'token=9c55b01b-7aae-4636-8207-0cacf5835034'



```json
{
  "exp": 1519657493,
  "user_name": "user",
  "authorities": [
    "ROLE_USER",
    "ROLE_ACTUATOR"
  ],
  "client_id": "foo1",
  "scope": [
    "read"
  ]
}
```


3. /members

curl -v -H "Authorization: Bearer 8c691e03-4c7d-434d-9c1f-522b4299979b" "http://localhost:8080/members"


