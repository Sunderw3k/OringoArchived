```http
POST https://shiyu.moe/auth2/

{
	"major_version": "1",
	"key": key_from_Key.class,
	"hwid": [hwid],
	"code": solved_code
}
```

solved_code is the computed code from the [[Code Endpoint]].

The response is a jar. A very security through obscurity protected jar.

The python script to decrypt those jars is located in `scripts/decrypt.py`
