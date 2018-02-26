1. /oauth/token

curl foo1:bar1@localhost:8181/oauth/token -d grant_type=password -d client_id=foo1 -d scope=read -d username=user -d password=pass

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

curl -X POST 'http://localhost:8181/oauth/check_token' -d 'token=0dd31869-5c41-44af-9635-dda99066fb57'

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

curl -v -H "Authorization: Bearer 2e0d006e-bdb9-4def-be27-1c13c2383ab3" "http://localhost:8080/members"


