```http
POST https://shiyu.moe/code/
user-agent: gbfhydsbuhyfd

{
	"major_version": "1",
	"key": key_from_Key.class,
	"hwid": [hwid]
}
```

It returns a single number between -1000 and 1000

I can't verify anymore, but I think this was cached for a while; it requires a "solution", which is
```kotlin
((response + 35) * 54) rem 1000
```

Then it sends a request to the [[Auth2 Endpoint]]. 