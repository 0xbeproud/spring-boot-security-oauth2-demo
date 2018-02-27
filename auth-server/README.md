# 1. /oauth/token

/oauth/token 엔드포인트는 클라이언트 인증 후 access_token을 발급한다. client_id, client_secret(Basic Auth로 요청), grant_type이 필수 파라메터로 요구된다.

출처: http://jsonobject.tistory.com/363 [지단로보트의 블로그]

발급된 access_token의 만료일시는 연장되지 않는다. 만료되면 재사용이 불가능하다. 만료일시가 다가올 경우 grant_type = refresh_token 파라메터를 요청하면 access_token이 새로 발급된다

출처: http://jsonobject.tistory.com/373?category=641992 [지단로보트의 블로그]


```bash

curl {security.oauth2.client.client-id}:{security.oauth2.client.client-secret}@주소/oauth/token \
    -d grant_type=password \
    -d client_id={security.oauth2.client.client-id} \
    -d scope=read \
    -d username={security.user.name} \
    -d password={security.user.password}

```

```bash
curl -u app1:app1 localhost:8181/oauth/token \
    -d grant_type=password \
    -d client_id=app1 \
    -d scope=read \
    -d username=member1 \
    -d password=member1
```

response
```json
{
  "access_token": "0dd31869-5c41-44af-9635-dda99066fb57",
  "token_type": "bearer",
  "refresh_token": "59928dd8-de18-4a43-b9be-f86e69eab203",
  "expires_in": 39318,
  "scope": "read"
}
```



# 2. /oauth/check_token

/oauth/check_token 엔드포인트는 요청 파라메터의 access_token의 유효 여부와 유효시 해당 클라이언트 정보를 응답한다. 
요청 예는 아래와 같다. 발급된 access_token을 token 파라메터에 첨부한다.

출처: http://jsonobject.tistory.com/363 [지단로보트의 블로그]

```bash
curl -X POST 'http://localhost:8181/oauth/check_token' \
    -d 'token=9c55b01b-7aae-4636-8207-0cacf5835034'
```

response

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

curl -v -H "Authorization: Bearer ca88d592-741a-4072-8a05-2656e47686ac" "http://localhost:8080/members"


