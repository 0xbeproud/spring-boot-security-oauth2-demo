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


# 3. /members

curl -v -H "Authorization: Bearer ca88d592-741a-4072-8a05-2656e47686ac" "http://localhost:8080/members"




### The most important fields from the oauth_client_details we should focus on are:

- client_id – to store the id of newly registered clients
- client_secret – to store the password of clients
- access_token_validity – which indicates if client is still valid
- authorities – to indicate what roles are permitted with particular client
- scope – allowed actions, for example writing statuses on Facebook etc.
- authorized_grant_types, which provides information how users can login to the particular client (in our example case it’s a form login with password)



Access Token Validity: 해당 클라이언트로 발급될 액세스 토큰 유효 시간 (기본값: 12시간)
(직접 기술:accessTokenValiditySeconds(...), DB: access_token_validity 칼럼 )

Refresh Token Validity: 해당 클라이언트로 발급될 리프 러시 토큰 유효 시간 (기본값: 30일)
(직접 기술:refreshTokenValiditySeconds(...), DB: refresh_token_validity 칼럼)

authorities: 클라이언트의 권한 부여 ( 클라이언트 인증방식 때 해당 권한을 사용)
(직접 기술:authorities("...", "...",...) DB: authorities 칼럼, 쉼표로 구분)

scope: 해당 클라이언트로 발급될 액세스 토큰의 권한 범위, 예를 들면 이 클라이언트로 API를 접근하면 특정 API에 대해 접근이 가능 여부 제어를 물을 때 사용된다. scope에 대한 이야기는 앞으로 좀 더 자세히 다룰 예정이다.
(직접 기술:. scopes("...", "...",...) DB: scope 칼럼, 쉼표로 구분)



### RSA

ssh-keygen -t rsa -b 4096 -C "auth-key" -f auth-key

### server.jks 만들기 

https://github.com/yookeun/springboot-jwt-example

```
key 만들기
keytool -genkeypair -alias jwt -keyalg RSA -dname "CN=Api Server,OU=Unit,O=Organization,L=City,S=State,C=US" -keypass password -keystore jwt.jks -storepass password
keytool -list -rfc --keystore jwt.jks | openssl x509 -inform pem -pubkey

-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqVO5vnTQCiR5cYrsLjZh
XIPPty+8A6R9gD/7naYSHj7i/sctTI57qIOO+kysgeyU7DKmImdvi+LMAqRl/KPD
LLvk6H12ChJYaX42qfPaU8OuyvKf5cat7JG3yoQfFVo3+1nuLrN5Z/wi42feCmSk
WBH1he49rCTQuth8Rxuwo6j9fm7ZTzMtk9KU/z0qAY9gfi/Mr6MJMolMyviwxOiV
7kGa7GcWHi85gycZs9TfgZvZCz7iB7SkN12BPjel2d670AoEO2gQZLThoMrQSPge
8ITuea/c2asgKJF70Ur8iEh2grD6N6iJ0eSxQYRcRAZZad95FHnVN939+Vbi6Eg2
AwIDAQAB
-----END PUBLIC KEY-----
-----BEGIN CERTIFICATE-----
MIIDbTCCAlWgAwIBAgIEfR9F0zANBgkqhkiG9w0BAQsFADBnMQswCQYDVQQGEwJV
UzEOMAwGA1UECBMFU3RhdGUxDTALBgNVBAcTBENpdHkxFTATBgNVBAoTDE9yZ2Fu
aXphdGlvbjENMAsGA1UECxMEVW5pdDETMBEGA1UEAxMKQXBpIFNlcnZlcjAeFw0x
ODAyMjgwODI5MzBaFw0xODA1MjkwODI5MzBaMGcxCzAJBgNVBAYTAlVTMQ4wDAYD
VQQIEwVTdGF0ZTENMAsGA1UEBxMEQ2l0eTEVMBMGA1UEChMMT3JnYW5pemF0aW9u
MQ0wCwYDVQQLEwRVbml0MRMwEQYDVQQDEwpBcGkgU2VydmVyMIIBIjANBgkqhkiG
9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqVO5vnTQCiR5cYrsLjZhXIPPty+8A6R9gD/7
naYSHj7i/sctTI57qIOO+kysgeyU7DKmImdvi+LMAqRl/KPDLLvk6H12ChJYaX42
qfPaU8OuyvKf5cat7JG3yoQfFVo3+1nuLrN5Z/wi42feCmSkWBH1he49rCTQuth8
Rxuwo6j9fm7ZTzMtk9KU/z0qAY9gfi/Mr6MJMolMyviwxOiV7kGa7GcWHi85gycZ
s9TfgZvZCz7iB7SkN12BPjel2d670AoEO2gQZLThoMrQSPge8ITuea/c2asgKJF7
0Ur8iEh2grD6N6iJ0eSxQYRcRAZZad95FHnVN939+Vbi6Eg2AwIDAQABoyEwHzAd
BgNVHQ4EFgQUpAP17RsMMXc3voTsrvq1kTwERKUwDQYJKoZIhvcNAQELBQADggEB
AKAfazfsfGCYV7Dm8yoRf4WwxmOmUG1ND7KmAjBEYlWFfJoijDWLhA+Fu4HnC6Yn
kdL0BlwxAEB1oCzG3/Jy4x/SLwiIz9P+YQywrg3uWXWqfdj1PhBb01YsKis21QG8
9y/3rX3kNdoMZJ11JkJdudN0A9u4+tDt58VnCFh2anZu9hYo0OSlxcheOmaF+o4H
1pp4rKg5igfrSVKAEbd7u9aFDUZCzPx6tw0Nvim1QT6G7c6IB4nWGR/lZ/nuzLAt
CjEJVVAl1js8BdPt/fR/P8p1btl8vBHKRP4O2zESmlC0R56CO3DlgG3YSAMsE1Qm
T0UW3tpfZJh1I8XwyTwvsVg=
-----END CERTIFICATE-----


인증키 만들기
keytool -export -keystore jwt.jks -alias jwt -file jwt.cer

인증키 내용 보기
openssl x509 -inform der -in jwt.cer -pubkey -noout

server.jks 내용 보기.
keytool -list -rfc --keystore jwt.jks | openssl x509 -inform pem -pubkey
```